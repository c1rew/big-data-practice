package com.viper.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author c1rew
 * @create 2020-10-06 22:33
 */
object SparkSQL01_Demo {
    def main(args: Array[String]): Unit = {
        // 创建配置对象
        val conf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01_Demo")

        // 获取session对象
        val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

        // 导入隐式转换
        import spark.implicits._

        // 从json文件读取数据
        val frame: DataFrame = spark.read.json("input/user.json")

        // 直接输出
        frame.show()

        // 使用sql语句查询
        frame.createOrReplaceTempView("user")
        val dataFrame: DataFrame = spark.sql("select id,name from user")
        dataFrame.show()

        // 释放资源
        spark.close()
    }
}
