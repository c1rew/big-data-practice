package com.viper.scala01

/**
 * @author c1rew    
 * @create 2020-10-03 15:51
 */
object Scala_Method {
    def main(args: Array[String]): Unit = {
        val user = new Person()
    }
}

/**
 * TODO Scala的构造方法分类两类，主构造方法 & 辅助构造方法
 * 1. Scala构造对应可以通过辅助构造方法创建，但是必须调用主构造方法
 * 2. Scala是完全面向函数的语言，所以类也是函数
 * 3. 类是函数，可以使用小括号作为函数的参数列表
 * 4. 类所代表的函数就是这个类的构造方法，也是主构造方法
 * 5. 默认情况下，scala也是给类提供无参构造方法，此时小括号可以省略
 * 6. 在煮构造方法中声明的构造方法就是辅助构造方法
 */
class Person(s: String) {
    // 类体 & 构造方法体
    println("主构造方法")
    println(s)

    def this(s1:String, s2:String) {
        this(s1)
        println("辅助构造方法2")
    }


    def this() {
        this("hello","world")
        println("辅助构造方法1")
    }
}