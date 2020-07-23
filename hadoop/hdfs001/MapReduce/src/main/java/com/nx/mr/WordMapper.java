package com.nx.mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-07-23 23:22
 */
public class WordMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private Text OutKey = new Text();
    private LongWritable one = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");

        for (String word : words) {
            OutKey.set(word);
            context.write(OutKey, one);
        }
    }
}
