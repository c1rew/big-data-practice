package org.example.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class HDFSClient {

    @Test
    public void put() throws IOException, InterruptedException {
        // 获取一个hdfs文件系统对象
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://node101:8020"),new Configuration(),"lyon");

        // 用这个对象操作文件系统
        fileSystem.copyFromLocalFile(new Path("/Users/c1rew/work/hadoop/wctest.txt"), new Path("/"));
        fileSystem.close();
    }
}
