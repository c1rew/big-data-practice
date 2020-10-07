package com.viper.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
 * @author c1rew
 * @create 2020-10-07 00:03
 */
object SparkSQL_Transform {
    def main(args: Array[String]): Unit = {
        // 创建sparkconf对象
        val conf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01_Demo")

        // 获取session对象
        val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

        // 导入隐式转换
        import spark.implicits._

        // 创建RDD
        val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "Tom", 22), (2, "Jack", 23), (3, "Candy", 19)))

        // rdd -> DataFrame
        val df: DataFrame = rdd.toDF("id", "name", "age")

        // DataFrame -> DataSet
        val ds: Dataset[User] = df.as[User]

        // DataSet -> DataFrame
        val df1: DataFrame = ds.toDF()

        // DataFrame -> rdd
        val rdd1: RDD[Row] = df1.rdd

        rdd1.foreach(row=>{
            // 通过索引获取数据
            println(row.getInt(0)+"\t"+row.getString(1)+"\t"+row.getInt(2))
        })

        // rdd->DataSet
        val userRdd: RDD[User] = rdd.map {
            case (id, name, age) => {
                User(id, name, age)
            }
        }
        val userDs: Dataset[User] = userRdd.toDS()
        // DataSet->rdd
        val rdd2: RDD[User] = userDs.rdd

        rdd2.foreach(println)

        // 释放资源
        spark.close()
    }
}

case class User(id: Int, name: String, age: Int)