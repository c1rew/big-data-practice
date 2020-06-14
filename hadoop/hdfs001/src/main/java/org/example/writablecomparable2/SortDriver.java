package org.example.writablecomparable2;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.example.writablecomparable.FlowBean;
import org.example.writablecomparable.SortMapper;
import org.example.writablecomparable.SortReducer;

import java.io.IOException;

/**
 * 针对flow的结果，按照总流量进行排序，并对不同开头的号码进行分区
 */
public class SortDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        job.setJarByClass(SortDriver.class);

        job.setMapperClass(SortMapper.class);
        job.setReducerClass(SortReducer.class);

        job.setPartitionerClass(MyPartitioner2.class);
        job.setNumReduceTasks(5);  // 没有设置Reduce任务数量的话，不会分区

        job.setMapOutputKeyClass(FlowBean.class);

        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
