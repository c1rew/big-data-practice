package com.viper

import org.apache.flink.api.scala._

/**
 * @author c1rew    
 * @create 2020-10-25 14:03
 */
object FlinkWordCount {
    def main(args: Array[String]): Unit = {
        val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

        val wordDataSet: DataSet[String] = env.readTextFile("input/word.txt")

        val result: AggregateDataSet[(String, Int)] = wordDataSet.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1)

        result.print()

        //        env.execute()
    }
}
