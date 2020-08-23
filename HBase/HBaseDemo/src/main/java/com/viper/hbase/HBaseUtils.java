package com.viper.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-08-22 14:05
 * @brief hbase 操作工具类
 */
public class HBaseUtils {

    // 使用线程共享变量
    private static ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();

    private HBaseUtils() {

    }

    public static void makeHBaseConnection() throws IOException {

        Connection conn = connHolder.get();
        if (conn == null) {
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", "bigdata02:2181,bigdata03:2181,bigdata04:2181");
            connHolder.set(ConnectionFactory.createConnection(conf));
        }
    }

    /**
     * 插入数据
     * @param tableName 表明
     * @param rowKey 唯一id
     * @param family 列族
     * @param column 列名
     * @param value 内容
     * @throws IOException
     */
    public static void insertData(String tableName, String rowKey, String family, String column, String value) throws IOException {
        Connection connection = connHolder.get();
        if (connection != null) {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
            table.put(put);
            table.close();
        }


    }

    public static void close() throws IOException {
        Connection connection = connHolder.get();
        if (connection != null) {
            connection.close();
            connHolder.remove(); // close后移除对应变量
        }
    }
}
