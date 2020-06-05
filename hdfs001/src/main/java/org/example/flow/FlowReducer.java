package org.example.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    private FlowBean sumFlow = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long up = 0;
        long down = 0;
        for (FlowBean value : values) {
            up += value.getUpFlow();
            down += value.getDownFlow();
        }
        sumFlow.set(up, down);
        context.write(key, sumFlow);
    }
}
