package org.example.outputformat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-07-05 23:09
 */
public class myOutputFormat extends FileOutputFormat<LongWritable, Text> {
    @Override
    public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        myRecordWriter recordWriter = new myRecordWriter();
        recordWriter.init(taskAttemptContext);
        return recordWriter;
    }
}
