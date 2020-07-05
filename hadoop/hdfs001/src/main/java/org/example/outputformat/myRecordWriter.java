package org.example.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Options;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-07-05 23:10
 */
public class myRecordWriter extends RecordWriter<LongWritable, Text> {

    private FSDataOutputStream httpsFos;
    private FSDataOutputStream httpFos;

    public void init(TaskAttemptContext taskAttemptContext) throws IOException {
        String outdir = taskAttemptContext.getConfiguration().get(FileOutputFormat.OUTDIR);
        FileSystem fs = FileSystem.get(taskAttemptContext.getConfiguration());
        httpsFos = fs.create(new Path(outdir + "/https.log"));
        httpFos = fs.create(new Path(outdir + "/http.log"));
    }

    @Override
    public void write(LongWritable longWritable, Text text) throws IOException, InterruptedException {
        String value = text.toString() + "\n";
        if (value.startsWith("https")) {
            httpsFos.write(value.getBytes());
        } else {
            httpFos.write(value.getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(httpsFos);
        IOUtils.closeStream(httpFos);
    }
}
