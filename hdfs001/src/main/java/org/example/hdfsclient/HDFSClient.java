package org.example.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class HDFSClient {

    private Configuration configuration;

    private FileSystem fs;

    @Before
    public void before() throws IOException, InterruptedException {
        configuration = new Configuration();
        fs = FileSystem.get(URI.create("hdfs://node101:8020"), configuration, "lyon");
        System.out.println("before...");
    }

    @Test
    public void put() throws IOException, InterruptedException {
        // 获取一个hdfs文件系统对象
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://node101:8020"), new Configuration(), "lyon");

        // 用这个对象操作文件系统
        fileSystem.copyFromLocalFile(new Path("/Users/c1rew/work/hadoop/wctest.txt"), new Path("/"));
        fileSystem.close();
    }

    @Test
    public void rename() throws IOException, InterruptedException {

        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://node101:8020"), new Configuration(), "lyon");

        fileSystem.rename(new Path("/wctest.txt"), new Path("/test.txt"));
        fileSystem.close();
    }

    @Test
    public void delete() throws IOException, InterruptedException {
        boolean delete = fs.delete(new Path("/test.txt"), true);
        if (delete) {
            System.out.println("delete success.");
        } else {
            System.out.println("delete failed.");
        }
    }


    @After
    public void after() throws IOException {
        fs.close();
        System.out.println("after...");
    }
}
