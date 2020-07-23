package com.nx.mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-07-23 23:22
 */
public class WordReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    private LongWritable outValue = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        for (LongWritable value : values) {
            count += value.get();
        }
        outValue.set(count);
        context.write(key, outValue);
    }
}
