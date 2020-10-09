package com.viper.scala01

import scala.collection.mutable.ArrayBuffer

/**
 * @author c1rew    
 * @create 2020-10-10 06:27
 */
object Scala_Array {
    def main(args: Array[String]): Unit = {
        /**
         * Java 数组：
         * int[] ints = new int[3]{1,2,3}
         * ints[0]=1
         * int i = ints[0]
         *
         * Scala中的数组，使用Array对象实现，使用中括号声明数组类型
         * Array[String]
         * Scala可以使用简单的方式创建数组
         * Array可以通过apply方式创建数组
         */
        val ints: Array[Int] = Array(1, 2, 3, 4)
        // 访问数组：使用小括号，增加索引的方式访问
        println(ints(0))

        // 增加元素，不会对原数组造成影响，而是产生新的数组
        val ints1: Array[Int] = ints :+ 5
        val ints2: Array[Int] = 5 +: (ints)
        println(ints1.mkString(","))
        println(ints2.mkString(","))

        // 修改数据
        ints.update(1, 5)

        // 可变数组
        val arrayBuffer: ArrayBuffer[Int] = ArrayBuffer(5, 6, 7, 8)
        arrayBuffer.foreach(println)
        // 增加数据
        arrayBuffer.insert(1, 9)
        val buffer: arrayBuffer.type = arrayBuffer += (9)
        println(arrayBuffer == buffer)
    }
}
