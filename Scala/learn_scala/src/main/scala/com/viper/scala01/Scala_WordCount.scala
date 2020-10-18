package com.viper.scala01

/**
 * word count ，取前三名
 *
 * @author c1rew    
 * @create 2020-10-18 10:49
 */
object Scala_WordCount {
    def main(args: Array[String]): Unit = {
        val wordList: List[String] = List("hello", "world", "hello", "scala", "hello", "spark", "world", "scala", "hbase")

        /**
         *     1. 按照单词分组
         *     2. 数据格式转换，得到每个组对应的size
         *     3. 转换成list，进行排序
         *     4. 获取前三个
         */

        val resultList: List[(String, Int)] = wordList.groupBy(word => word).map(t => {
            (t._1, t._2.size)
        }).toList.sortWith((left, right) => {
            left._2 > right._2
        }).take(3)
        println(resultList.mkString(","))
    }
}
