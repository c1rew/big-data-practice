package com.nx.mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author c1rew
 * @create 2020-07-23 23:23
 */
public class WordPartitioner extends Partitioner<Text, LongWritable> {
    @Override
    public int getPartition(Text text, LongWritable longWritable, int i) {
        if (text.toString().length() >= 5) {
            return 0;
        } else {
            return 1;
        }
    }
}
