package org.example.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;

public class HDFSDeleteEmptyDirAndFiles {

    private static FileSystem fs = null;

    public static void main(String[] args) {
        initialFileSystem();
    }

    private static void initialFileSystem() {
        Configuration conf = new Configuration();
        try {
            fs = FileSystem.get(URI.create("hdfs://hadoop277ha/"), conf, "lyon");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}