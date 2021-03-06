package org.example.groupingcomparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-06-16 22:10
 */
public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    private OrderBean orderBean = new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 拆分文本数据，获得每个字段的值；封装orderBean；写入上下文；
        String[] fields = value.toString().split("\t");
        orderBean.setOrderId(fields[0]);
        orderBean.setProductId(fields[1]);
        orderBean.setPrice(Double.parseDouble(fields[2]));
        context.write(orderBean, NullWritable.get());
    }
}
