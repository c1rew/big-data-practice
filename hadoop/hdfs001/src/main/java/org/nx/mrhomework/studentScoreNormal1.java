package org.nx.mrhomework;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 1、每一个course的最高分，最低分，平均分
 * 返回结果格式：
 * course	max=95	min=22	avg=55
 * 例子：
 * computer	max=99	min=48	avg=75
 */

public class studentScoreNormal1 {
    private static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
        private Text course = new Text();

        private IntWritable score = new IntWritable();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            // test file scorelist.txt
            String[] fields = value.toString().split(",");
            course.set(fields[0]);
            score.set(Integer.parseInt(fields[2]));
            context.write(course, score);

        }
    }

    private static class Reduce extends Reducer<Text, IntWritable, Text, Text> {
        private int sum = 0;
        private int count = 0;
        private int max = 0;
        private int min = 0;

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            // 每科都需要初始化一次
            max = 0;   // 初始化为小值
            min = 100; // 初始化为大值

            for (IntWritable value : values) {
                int score = value.get();

                if (max < score) {
                    max = score;
                }
                if (min > score) {
                    min = score;
                }
                sum += score;
                count++;
            }
            int average = sum / count;
            Text value = new Text("max=" + max + "\t" + "min=" + min + "\t" + "avg=" + average);
            context.write(key, value);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance();

        job.setJarByClass(studentScoreNormal2.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        Path outputPath = new Path(args[1]);
        FileSystem fs = FileSystem.get(conf);
        // 输出文件夹如果存在就删除
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        FileOutputFormat.setOutputPath(job, outputPath);

        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);
    }
}
