package org.example.hdfsclient;

import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.Test;

public class HadoopDataType {

    @Test
    public void testText() {
        System.out.println("testText");
        Text text = new Text("hello hadoop!");
        System.out.println(text.getLength());
        System.out.println(text.find("a"));
        System.out.println(text.toString());
    }

    @Test
    public void testArrayWritable() {
        System.out.println("test ArrayWritable");
        ArrayWritable arrayWritable = new ArrayWritable(IntWritable.class);
        IntWritable year = new IntWritable(2020);
        IntWritable month = new IntWritable(06);
        IntWritable date = new IntWritable(02);
        arrayWritable.set(new IntWritable[]{year, month, date});
        System.out.println(String.format("year=%d,month=%d,date=%d",
                ((IntWritable) arrayWritable.get()[0]).get(),
                ((IntWritable) arrayWritable.get()[1]).get(),
                ((IntWritable) arrayWritable.get()[2]).get()));
    }
    
}
