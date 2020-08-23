package com.viper.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-08-23 15:03
 */
public class HBaseAPI_2 {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "bigdata02:2181,bigdata03:2181,bigdata04:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();

        TableName tableName = TableName.valueOf("test:student");
        Table table = conn.getTable(tableName);
        String rowKey = "1001";

        // 扫描数据
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            for (Cell cell : result.rawCells()) {
                System.out.println(Bytes.toString(CellUtil.cloneRow(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

        // 删除数据
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        System.out.println("删除数据。");

        // 删除表
        if (admin.tableExists(tableName)) {
            // 删除前需要禁用
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("删除表。");
        }
    }
}
