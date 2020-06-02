package org.example.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
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

    @Test
    public void ls() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));

        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()) {
                System.out.println("file information: ");
                System.out.println(fileStatus.getPath());
                System.out.println(fileStatus.getLen());
            } else {
                System.out.println("direction information: ");
                System.out.println(fileStatus.getPath());
            }
        }
    }

    @Test
    public void listFiles() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);

        while (files.hasNext()) {
            LocatedFileStatus file = files.next();
            System.out.println("----------------------------");
            System.out.println(file.getPath());
            System.out.println("block information: ");
            BlockLocation[] blockLocations = file.getBlockLocations();

            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                System.out.print("block in ");
                for (String host : hosts) {
                    System.out.print(host + " ");
                }
                System.out.println();
            }
        }
    }

    @After
    public void after() throws IOException {
        fs.close();
        System.out.println("after...");
    }
}
