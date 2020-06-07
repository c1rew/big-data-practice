package org.example.inputformat;

import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class WholeFileRecordReader extends RecordReader<Text, ByteWritable> {

    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

    }

    public boolean nextKeyValue() throws IOException, InterruptedException {
        return false;
    }

    public Text getCurrentKey() throws IOException, InterruptedException {
        return null;
    }

    public ByteWritable getCurrentValue() throws IOException, InterruptedException {
        return null;
    }

    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    public void close() throws IOException {

    }
}
