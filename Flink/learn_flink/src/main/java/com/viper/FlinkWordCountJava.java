package com.viper;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author c1rew
 * @date 2020-10-28 16:48
 */

public class FlinkWordCountJava {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataStreamSource = env.readTextFile("input/word.txt");

        dataStreamSource.
    }
}
