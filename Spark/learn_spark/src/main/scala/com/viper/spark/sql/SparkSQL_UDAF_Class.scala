package com.viper.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, Row, SparkSession, TypedColumn}

/**
 * @author c1rew
 * @create 2020-10-07 13:28
 */
object SparkSQL_UDAF_Class {
    def main(args: Array[String]): Unit = {

        val config = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_UDAF_Class")

        // 创建session对象
        val spark: SparkSession = SparkSession.builder().config(config).getOrCreate()

        // 导入隐式转换
        import spark.implicits._

        // 创建聚合函数对象
        val udaf = new MyAgeAvgClassFunction

        // 将聚合函数转换为查询列
        val avgCol: TypedColumn[UserBean, Double] = udaf.toColumn.name("avgAge")

        val frame: DataFrame = spark.read.json("input/user.json")

        val userDS: Dataset[UserBean] = frame.as[UserBean]

        userDS.select(avgCol).show()

        // 释放资源
        spark.close()
    }
}

case class UserBean(id: BigInt, name: String, age: BigInt)

case class AvgBuffer(var sum: BigInt, var count: Int)

/**
 * 声明用户自定义聚合函数（强类型）
 * 1. 继承Aggregator，设定泛型
 * 2. 实现方法
 */
class MyAgeAvgClassFunction extends Aggregator[UserBean, AvgBuffer, Double] {
    // 初始化
    override def zero: AvgBuffer = {
        AvgBuffer(0, 0)
    }

    /**
     * 聚合数据
     *
     * @param b
     * @param a
     * @return
     */
    override def reduce(b: AvgBuffer, a: UserBean): AvgBuffer = {
        b.sum = b.sum + a.age
        b.count = b.count + 1
        b
    }

    //合并数据
    override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
        b1.sum = b1.sum + b2.sum
        b1.count = b1.count + b2.count
        b1
    }

    // 完成计算
    override def finish(reduction: AvgBuffer): Double = {
        reduction.sum.toDouble / reduction.count
    }

    override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}