import com.viper.utils.JDBCUtils;
import com.viper.utils.Student;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author c1rew
 * @date 2020-12-27 15:08
 */

public class PreparedStatementQueryTest {

    @Test
    public void Test() throws Exception {
        studentQuery("select SId id,Sname name, Sbirth birth, Ssex sex from Student where SId = ?", "02");
        System.out.println();
        String sql = "select SId id,Sname name, Sbirth birth, Ssex sex from Student where SId = ? or SId = ?";
        studentQuery(sql, "01", "02");
        System.out.println(query(Student.class, sql, "01", "02"));
    }

    /**
     * 针对学生表的通用查询
     *
     * @param sql
     * @param args
     * @throws Exception
     */
    public void studentQuery(String sql, String... args) throws Exception {
        Connection conn = JDBCUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; ++i) {
            ps.setObject(i + 1, args[i]);
        }

        ResultSet resultSet = ps.executeQuery();

        // 通过ResultSet的元数据获取列数量
        ResultSetMetaData rs = resultSet.getMetaData();
        int columnCount = rs.getColumnCount();

        while (resultSet.next()) {
            Student student = new Student();
            for (int i = 1; i <= columnCount; ++i) {
                // 获取列名
                Object columnValue = resultSet.getObject(i);
                // 获取列值，避免字段名和类属性不同，使用Lable获取
                String columnLabel = rs.getColumnLabel(i);
                // 使用反射，通过Lable明对属性进行赋值
                Field field = student.getClass().getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(student, columnValue);
            }
            System.out.println(student);
        }
        JDBCUtils.closeResource(conn, ps);
    }

    /**
     * 通用查询，指定类，指定sql及参数，返回查询结果集
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> query(Class<T> clazz, String sql, String... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                ps.setObject(i + 1, args[i]);
            }

            ResultSet resultSet = ps.executeQuery();

            // 通过ResultSet的元数据获取列数量
            ResultSetMetaData rs = resultSet.getMetaData();
            int columnCount = rs.getColumnCount();

            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {
                // 通过反射创建对象
                T t = clazz.newInstance();
                for (int i = 1; i <= columnCount; ++i) {
                    // 获取列名
                    Object columnValue = resultSet.getObject(i);
                    // 获取列值，避免字段名和类属性不同，使用Lable获取
                    String columnLabel = rs.getColumnLabel(i);
                    // 使用反射，通过Lable明对属性进行赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return null;
    }
}
