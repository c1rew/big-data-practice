package com.viper.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField}
import org.apache.spark.sql.{Row, SparkSession, types}

/**
 * 奈学 SparkSQL（二）案例
 *
 * @author c1rew    
 * @create 2020-10-13 07:00
 */
object SparkSQLDemo01 {
    def main(args: Array[String]): Unit = {
        // 创建SparkSession
        val spark: SparkSession = SparkSession.builder().master("local[*]").appName("SparkSQLDemo01").getOrCreate()

        // 读取数据
        val userRDD: RDD[Array[String]] = spark.sparkContext.textFile("input/user.txt").map(_.split(","))

        // 通过StructType 创建Schema
        val schema = types.StructType(
            List(
                StructField("id", IntegerType, true),
                StructField("name", StringType, true),
                StructField("age", IntegerType, true)
            )
        )
        // 将RDD映射到RowRDD行的数据上
        val rowRDD = userRDD.map(user => Row(user(0).toInt, user(1), user(2).toInt))

        // 生成DataFrame
        val userDF = spark.createDataFrame(rowRDD, schema)

        // 创建临时视图
        userDF.createOrReplaceTempView("user")

        // 执行SQL
        spark.sql("select * from user").show()

        // 释放资源
        spark.stop()
    }
}
