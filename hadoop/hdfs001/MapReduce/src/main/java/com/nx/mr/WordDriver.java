package com.nx.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-07-23 23:23
 */
public class WordDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        // 命令行参数设置输入路径
        FileInputFormat.setInputPaths(job, args[0]);

        job.setJarByClass(WordDriver.class);

        job.setMapperClass(WordMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setReducerClass(WordReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setPartitionerClass(WordPartitioner.class);
        job.setNumReduceTasks(2);

        // 命令行参数设置输出路径，该路径如存在先删除
        FileSystem fs = FileSystem.get(conf);
        Path outputPath = new Path(args[1]);
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        FileOutputFormat.setOutputPath(job, outputPath);

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
