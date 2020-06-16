package org.example.groupingcomparator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author c1rew
 * @create 2020-06-16 22:10
 */
public class OrderReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        // 仅输出各个订单的最高金额
        //context.write(key, NullWritable.get());

        // 所有订单，先按订单排序，再按金额排序
//        for (NullWritable value : values) {
//            context.write(key, value);
//        }

        // 输出每个订单的前两名
        Iterator<NullWritable> iterator = values.iterator();

        for (int i = 0; i < 2; i++) {
            if (iterator.hasNext()) {
                context.write(key, iterator.next());
            }
        }
    }
}
