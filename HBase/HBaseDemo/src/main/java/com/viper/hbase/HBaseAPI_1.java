package com.viper.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-08-21 22:51
 */
public class HBaseAPI_1 {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.addResource("conf/hbase-site.xml");
        conf.addResource("conf/core-site.xml");
        conf.addResource("conf/hdfs-site.xml");
        conf.set("hbase.zookeeper.quorum", "bigdata02:2181,bigdata03:2181,bigdata04:2181");
        System.out.println(conf.get("hbase.zookeeper.quorum"));
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();

        // 获取命名空间，没有的话就创建
        try {
            admin.getNamespaceDescriptor("test");
        } catch (NamespaceNotFoundException e) {
            NamespaceDescriptor nsDesc = NamespaceDescriptor.create("test").build();
            admin.createNamespace(nsDesc);
            System.out.println(nsDesc + " 命名空间创建成功。");
        }

        // 有对应的命名空间，获取相应的表对象，没有就创建
        TableName tableName = TableName.valueOf("test:student");
        boolean b = admin.tableExists(tableName);
        if (b) {
            // 表存在，查询数据
            System.out.println(tableName + "表已存在。");
            // 获取表对象
            Table table = conn.getTable(tableName);
            String rowKey = "1001";

            // 查询
            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(get);
            boolean empty = result.isEmpty();
            if (empty) {
                // 插入一条数据
                Put put = new Put(Bytes.toBytes(rowKey));
                String family = "info";
                String column = "name";
                String val = "Jack";
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(val));
                table.put(put);
                System.out.println("插入数据成功。");
            } else {
                // 遍历表数据
                for (Cell cell : result.rawCells()) {
                    System.out.println(Bytes.toString(CellUtil.cloneRow(cell)));
                    System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
                    System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
                    System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
        } else {
            // 创建表描述对象
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            // 添加列族info
            tableDesc.addFamily(new HColumnDescriptor("info"));
            // 创建表
            admin.createTable(tableDesc);
            System.out.println(tableName + " 表创建成功。");
        }
    }
}
