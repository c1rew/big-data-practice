import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author c1rew
 * @date 2020-12-27 8:55
 */

public class ConnectTest {

    /**
     * url:http://localhost:8080/gmall/keyboard.jpg
     * jdbc:mysql:协议
     * 192.168.56.152:ip地址
     * 3306：默认mysql的端口号
     * test:test数据库
     */
    public String url = "jdbc:mysql://192.168.56.152:3306/test";

    /**
     * 方式一
     *
     * @throws Exception 异常抛出，实际开发需要try catch
     */
    @Test
    public void connectTest1() throws Exception {
        // 获取Driver实现类对象
        Driver driver = new com.mysql.jdbc.Driver();

        // 将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "000000");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
        conn.close();
    }

    /**
     * 方式二：使用反射，避免直接调用第三方接口
     *
     * @throws Exception 异常抛出，实际开发需要try catch
     */
    @Test
    public void connectTest2() throws Exception {
        @SuppressWarnings("rawtypes")
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String url = "jdbc:mysql://192.168.56.152:3306/test";

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "000000");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
        conn.close();
    }

    /**
     * 方式三：使用DriverManager代替 Driver
     *
     * @throws Exception 异常抛出，实际开发需要try catch
     */
    @Test
    public void connectTest3() throws Exception {
        @SuppressWarnings("rawtypes")
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String user = "root";
        String password = "000000";

        DriverManager.registerDriver(driver);

        Connection conn = DriverManager.getConnection(url, user, password);

        System.out.println(conn);
        conn.close();
    }

    /**
     * 方式四
     *
     * @throws Exception 异常抛出，实际开发需要try catch
     */
    @Test
    public void connectTest4() throws Exception {
        // 1.提供连接的基本信息：
        String user = "root";
        String password = "000000";

        // 2.加载Driver
        // 这一句也可以省略，但是不建议
        Class.forName("com.mysql.jdbc.Driver");
        // 相较于方式三，可以省略如下的操作：
//		Driver driver = (Driver) clazz.newInstance();
//		// 注册驱动
//		DriverManager.registerDriver(driver);
        //为什么可以省略上述操作呢？
		/*
		 * 在mysql的Driver实现类中，声明了如下的操作：
		 * static {
				try {
					java.sql.DriverManager.registerDriver(new Driver());
				} catch (SQLException E) {
					throw new RuntimeException("Can't register driver!");
				}
			}
		 */

        // 3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
        conn.close();
    }

    /**
     * 方式五：基础信息封装在配置文件中
     * 两大好处
     * 1. 代码与数据分离，解耦
     * 2. 配置修改无需重新打包
     */
    @Test
    public void connectTest5() throws Exception {
        InputStream inputStream = ConnectTest.class.getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(inputStream);

        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        String driverClass = prop.getProperty("driverClass");

        Class.forName(driverClass);

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
        conn.close();
    }
}
