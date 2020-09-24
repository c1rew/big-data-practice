package com.viper.scala01

object Scala_Function {
    def main(args: Array[String]): Unit = {
        // 声明函数
        def test(s: String): Unit = {
            // 函数体
            println(s)
        }
        test("hello")

        // 无参数，无返回值
        def test0(): Unit = {
            println("test0")
        }
        test0()

        // 有参数，无返回值
        // 函数没有重载，同一个作用域不可同名
        def test1(s: String): Unit = {
            println(s)
        }
        test1("test1")

        // 有参数，有返回值
        def test2(s: String): String = {
            return s + " world"
        }
        val str = test2("hello")
        println(str)

        // 无参数，有返回值
        def test3(): String = {
            return "test3"
        }
        val str1 = test3()
        println(str1)
    }
}
