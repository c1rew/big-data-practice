package com.nx.exer;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
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

    @Test
    public void downloadFile() throws IOException {
        FSDataInputStream inputStream = fs.open(new Path("/a/b/c2/e/notemptyfile.txt"));
        FileOutputStream outputStream = new FileOutputStream("d:\\input\\notemptyfile.txt");

        IOUtils.copy(inputStream, outputStream);

        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @After
    public void after() {
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


