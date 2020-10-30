package com.viper;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author c1rew
 * @date 2020-10-30 14:15
 */

public class StreamWordCountJava5 {
    public static void main(String[] args) throws Exception {
        // 1. 初始化web ui环境入口
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        // 设置并行度
        env.setParallelism(2);

        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        String hostname = parameterTool.get("hostname");
        int port = parameterTool.getInt("port");

        // 2. 数据输入
        DataStreamSource<String> dataStream = env.socketTextStream(hostname, port);

        // 3. 数据处理
        SingleOutputStreamOperator<WordCount> result = dataStream.flatMap(new WordParser()).keyBy("word").sum("count");

        // 4. 输出
        result.print();
        // 5. 启动
        env.execute("StreamWordCountJava5");
    }

    // 独立出单词处理
    public static class WordParser implements FlatMapFunction<String, WordCount> {
        @Override
        public void flatMap(String s, Collector<WordCount> collector) throws Exception {
            String[] fields = s.split(" ");
            for (String word : fields) {
                collector.collect(new WordCount(word, 1));
            }
        }
    }

    public static class WordCount {
        private String word;
        private Integer count;

        @Override
        public String toString() {
            return word + '\t' + count;
        }

        public WordCount() {
        }

        public WordCount(String word, Integer count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
