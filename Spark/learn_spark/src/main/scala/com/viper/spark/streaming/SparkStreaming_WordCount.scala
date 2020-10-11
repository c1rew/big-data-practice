package com.viper.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author c1rew    
 * @create 2020-10-07 19:52
 */
object SparkStreaming_WordCount {
    def main(args: Array[String]): Unit = {
        // 配置对象
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming_WordCount")

        // spark streaming 环境对象
        // 设定采集周期时间为 3 秒
        val streamingContext = new StreamingContext(sparkConf, Seconds(3))

        // 从指定端口读取数据
        val socketLineDStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("bigdata02", 19090)

        // 扁平化，空格切割
        val wordDStream: DStream[String] = socketLineDStream.flatMap(_.split(" "))

        // 每个单词组成元组 word 1
        val mapDStream: DStream[(String, Int)] = wordDStream.map((_, 1))

        // 数据聚合
        val wordSumDStream: DStream[(String, Int)] = mapDStream.reduceByKey(_ + _)

        // 打印结果
        wordSumDStream.print()

        // 启动StreamingContext
        streamingContext.start()

        // 等待数据采集器结束
        streamingContext.awaitTermination()

    }
}
