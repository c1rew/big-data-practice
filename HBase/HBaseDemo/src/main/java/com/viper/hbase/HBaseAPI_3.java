package com.viper.hbase;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-08-23 15:17
 */
public class HBaseAPI_3 {
    public static void main(String[] args) throws IOException {
        // 创建连接
        HBaseUtils.makeHBaseConnection();

        HBaseUtils.insertData("test:student","1002","info", "name", "Tom");

        // 关闭连接
        HBaseUtils.close();
    }
}
