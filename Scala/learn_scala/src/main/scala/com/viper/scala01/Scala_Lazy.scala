package com.viper.scala01

/**
 * @author c1rew    
 * @create 2020-09-26 22:59
 */
object Scala_Lazy {

    /**
     * 注意
     * 1. lazy不能修饰var类型变量
     * 2. 不止在调用函数，如果在声明变量时使用lazy，变量值的分配也会延迟  lazy val i = 10
     */

    def sum(i: Int, j: Int): Int = {
        println("sum...")
        i + j
    }

    def main(args: Array[String]): Unit = {
        lazy val ret = sum(1, 2)
        println("------------")
        println("------------")
        println("------------")
        println("ret: " + ret)
    }
}
