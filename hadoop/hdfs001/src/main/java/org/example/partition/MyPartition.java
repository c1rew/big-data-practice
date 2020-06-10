package org.example.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.example.flow.FlowBean;

public class MyPartition extends Partitioner<Text, FlowBean> {
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String phone = text.toString();

        switch (phone.substring(0, 3)) {
            case "136":
                return 0;
            case "137":
                return 1;
            case "138":
                return 2;
            case "139":
                return 3;
            default:
                return 4;
        }
    }
}
