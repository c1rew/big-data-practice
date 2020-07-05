package org.example.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * test file url.txt
 * 将https和http两种不同的url，输出到不同的文件 一个为https.log，一个为http.log
 *
 * @author c1rew
 * @create 2020-07-05 23:10
 */
public class outputDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(outputDriver.class);

        job.setOutputFormatClass(myOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        Path outputPath = new Path(args[1]);
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }

        FileOutputFormat.setOutputPath(job, outputPath);

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
