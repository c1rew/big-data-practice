package org.example.inputformat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 自定义RecordReader处理一个文件，把这个文件直接读成一个KV值
 */
public class WholeFileRecordReader extends RecordReader<Text, BytesWritable> {

    private boolean notRead = true;

    private Text key = new Text();
    private BytesWritable value = new BytesWritable();

    private FSDataInputStream inputStream;

    private FileSplit fs;

    /**
     * 初始化方法，框架开始时调用
     *
     * @param inputSplit
     * @param taskAttemptContext
     * @throws IOException
     * @throws InterruptedException
     */
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        // 转换切片类型到文件切片
        fs = (FileSplit) inputSplit;
        // 通过切片获取文件路径
        Path path = fs.getPath();
        // 通过路径获取文件系统
        FileSystem fileSystem = path.getFileSystem(taskAttemptContext.getConfiguration());

        inputStream = fileSystem.open(path);
    }

    /**
     * 尝试读取下一组KV值
     *
     * @return 读到返回true，读完返回false
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (notRead) {
            // 具体读的过程
            key.set(fs.getPath().toString());

            byte[] buf = new byte[(int) fs.getLength()];
            inputStream.read(buf);
            value.set(buf, 0, buf.length);

            notRead = false;
            return true;
        } else {
            return false;
        }

    }

    /**
     * 获取当前读到的Key
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    /**
     * 获取当前读到的Value
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    /**
     * 返回当前数据读取的进度
     *
     * @return 0-1
     * @throws IOException
     * @throws InterruptedException
     */
    public float getProgress() throws IOException, InterruptedException {
        return notRead ? 0 : 1;
    }

    /**
     * 关闭资源
     *
     * @throws IOException
     */
    public void close() throws IOException {
        IOUtils.closeStream(inputStream);
    }
}
