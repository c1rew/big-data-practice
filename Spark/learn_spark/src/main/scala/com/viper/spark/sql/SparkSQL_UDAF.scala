package com.viper.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @author c1rew
 * @create 2020-10-07 00:03
 */
object SparkSQL_UDAF {
    def main(args: Array[String]): Unit = {

        val config = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_UDAF")

        // 创建session对象
        val spark: SparkSession = SparkSession.builder().config(config).getOrCreate()

        // 导入隐式转换
        import spark.implicits._

        // 从json文件读取数据
        val frame: DataFrame = spark.read.json("input/user.json")

        frame.createOrReplaceTempView("user")

        // 创建自定义聚合函数对象
        val udaf = new MyAgeAvgFunction
        // 注册聚合函数
        spark.udf.register("avgAge", udaf)

        spark.sql("select avgAge(age) from user").show

        // 释放资源
        spark.close()
    }
}

/**
 * 声明用户自定义聚合函数
 * 1. 继承UserDefinedAggregateFunction
 * 2. 实现方法
 */
class MyAgeAvgFunction extends UserDefinedAggregateFunction {
    // 函数输入的数据结构
    override def inputSchema: StructType = {
        new StructType().add("age", LongType)
    }

    // 计算时的数据结构
    override def bufferSchema: StructType = {
        new StructType().add("sum", LongType).add("count", LongType)
    }

    // 返回值数据类型
    override def dataType: DataType = DoubleType
    // 函数是否稳定
    override def deterministic: Boolean = true

    // 计算前的缓冲区初始值
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
        buffer(0) = 0L
        buffer(1) = 0L
    }

    // 更新缓冲区数据
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
        // sum
        buffer(0) = buffer.getLong(0) + input.getLong(0)
        //count
        buffer(1) = buffer.getLong(1) + 1
    }

    // 合并两个缓冲区数据
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
        buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
        buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
    }
    // 计算
    override def evaluate(buffer: Row): Any = {
        buffer.getLong(0).toDouble / buffer.getLong(1)
    }
}