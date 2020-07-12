package com.nx.exer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-07-12 22:49
 */
public class HdfsApi {

    private FileSystem fs;

    @Before
    public void before() {
        Configuration configuration = new Configuration();
        try {
            System.setProperty("HADOOP_USER_NAME", "lyon");
            configuration.addResource("config/core-site.xml");
            configuration.addResource("config/hdfs-site.xml");
            fs = FileSystem.get(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {

    }

    public void downloadFile() {

    }
}


