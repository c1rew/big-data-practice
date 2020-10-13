package com.viper.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession, types}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField}

/**
 * 奈学 SparkSQL（二）案例
 *
 * @author c1rew    
 * @create 2020-10-13 20:56
 */
object SparkSQLDemo02 {
    def main(args: Array[String]): Unit = {
        // 创建SparkSession
        val spark: SparkSession = SparkSession.builder().master("local[*]").appName("SparkSQLDemo01").getOrCreate()

        // 读取数据
        val userRDD: RDD[Array[String]] = spark.sparkContext.textFile("input/user.txt").map(_.split(","))

        val mapRDD: RDD[UserClass] = userRDD.map(x => UserClass(x(0).toInt, x(1), x(2).toInt))

        // 导入隐式转换，通过rdd生成df
        import spark.implicits._
        val userDF: DataFrame = mapRDD.toDF()

        // 创建临时视图
        userDF.createOrReplaceTempView("user")

        // 执行SQL
        spark.sql("select * from user").show()

        // 释放资源
        spark.stop()
    }
}
// 定义case class代表Schema结构
case class UserClass(id: Int, name: String, age: Int)