package org.example.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;

public class HDFSDeleteEmptyDirAndFiles {

    private static FileSystem fs = null;

    public static void main(String[] args) {
        initialFileSystem();

        //createTestData();

        delEmptyDirAndFiles(new Path("/a"));

        printTestData(new Path("/a"));

        //delTestData();

        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTestData(Path path) {
        try {
            FileStatus[] listStatus = fs.listStatus(path);
            for (FileStatus status : listStatus) {
                if (status.isDirectory()) {
                    System.out.println(status.getPath());
                    printTestData(status.getPath());
                } else if (status.isFile()) {
                    System.out.println(status.getPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void delEmptyDirAndFiles(Path path) {
        try {
            FileStatus[] listStatus = fs.listStatus(path);
            // 当前目录为空，直接删除返回
            if (listStatus.length == 0) {
                fs.delete(path,true);
                return;
            }
            RemoteIterator<LocatedFileStatus> locatedStatus = fs.listLocatedStatus(path);
            while (locatedStatus.hasNext()) {
                LocatedFileStatus fileStatus = locatedStatus.next();
                Path currentPath = fileStatus.getPath();
                Path parentPath = currentPath.getParent();

                if (fileStatus.isDirectory()) {
                    // 删除空目录
                    if (fs.listStatus(currentPath).length == 0) {
                        fs.delete(currentPath,true);
                    } else {
                        // 递归遍历目录
                        delEmptyDirAndFiles(currentPath);
                    }
                } else if (fileStatus.isFile()){
                    // 删除空文件
                    if (fileStatus.getLen() == 0) {
                        fs.delete(currentPath, true);
                    }
                }
                // 如果目录下的文件或者文件夹被删除，则父目录也变成了空文件，也需要删除
                if (fs.listStatus(parentPath).length == 0) {
                    fs.delete(parentPath, true);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除测试数据
     */
    private static void delTestData() {

        try {
            fs.delete(new Path("/a"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建测试数据
     */
    private static void createTestData() {

        String emptyFile = "D:\\emptyfile.txt";
        String notEmptyFile = "D:\\notemptyfile.txt";
        try {
            fs.mkdirs(new Path("/a/b/c1/d"));

            fs.mkdirs(new Path("/a/b/c2/e"));
            fs.copyFromLocalFile(new Path(emptyFile), new Path("/a/b/c2/e"));
            fs.copyFromLocalFile(new Path(notEmptyFile), new Path("/a/b/c2/e"));

            fs.mkdirs(new Path("/a/b/c3/f"));
            fs.copyFromLocalFile(new Path(emptyFile), new Path("/a/b/c3/f"));

            fs.mkdirs(new Path("/a/b/c4/g"));
            fs.copyFromLocalFile(new Path(notEmptyFile), new Path("/a/b/c4/g"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化hdfs filessytem
     */
    private static void initialFileSystem() {
        Configuration conf = new Configuration();
        try {
            System.setProperty("HADOOP_USER_NAME", "lyon");
            conf.addResource("config/core-site.xml");
            conf.addResource("config/hdfs-site.xml");
            //fs = FileSystem.get(URI.create("hdfs://hadoop277ha/"), conf, "lyon");
            // HA的调试环境需要使用xml配置进行指定
            fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}