import java.sql.*;

/**
 * @author c1rew
 * @create 2021-01-08 22:29
 */
public class JDBCTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 获取连接
            String url = "jdbc:mysql://192.168.56.152:3306/test";
            String user = "root";
            String password = "000000";
            conn = DriverManager.getConnection(url, user, password);

            // 获取preparedStatement
            String sql = "select * from tb_user where id=?";
            preparedStatement = conn.prepareStatement(sql);
            // 绑定参数
            preparedStatement.setLong(1, 1l);

            // 执行查询
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_name"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("age"));
                System.out.println(resultSet.getString("sex"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接，释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
