package com.viper.scala01

/**
 * @author c1rew    
 * @create 2020-10-18 17:57
 */
object Scala_WordCount1 {
    def main(args: Array[String]): Unit = {
        val lineList = List(("hello scala world", 4), ("hello world", 3), ("hello hadoop", 2), ("hello hbase", 1))

        /**
         * 1. key 扁平化
         * 2. 根据key分组
         * 3. 分组内的list，count计算总和sum
         * 4. 根据count排序
         * 5. 提取前3个
         */

        val flatMapList: List[(String, Int)] = lineList.flatMap(line => {
            val words: Array[String] = line._1.split(" ")
            words.map(x => (x, line._2))
        })

        val groupList: Map[String, List[(String, Int)]] = flatMapList.groupBy(x => x._1)

        val mapList: Map[String, Int] = groupList.map(t => {
            (t._1, t._2.map(tt => tt._2).sum)
        })

        val sortList: List[(String, Int)] = mapList.toList.sortWith((left, right) => {
            left._2 > right._2
        })

        val resultList: List[(String, Int)] = sortList.take(3)

        println(resultList)
    }
}
