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


        // scala 采用自动推断来简化函数声明
        // 1. 如果函数声明时，明确无返回值Unit，即使函数中有return也不起作用
        def test4(): Unit = {
            return "test4"
        }

        test4()

        // 2. 如果函数体的最后一行代码进行返回，return关键字可以省略
        def test5(): String = {
            "test5"
        }

        println(test5())

        // 3. 如果可根据函数最后一行代码推断类型，函数返回值类型可以省略
        def test6() = {
            "test6"
        }

        println(test6())

        // 如果函数体只有一行代码，大括号可以省略
        // TODO 声明函数必须要增加def
        // 函数没有返回值，等号可以省略，省略后，编译器将不会将函数最后一行作为返回值
        // 如果函数没有参数列表，可以省略小括号，调用时一定不能使用小括号
        // 如果函数没有参数列表，且没有省略小括号，调用时可加可不加


        // 匿名函数
        () -> {
            println("hello lambda")
        } // 声明即调用
        // () => {println("hi...")}  // 声明不调用，需要显示调用
        val f: () => Unit = () => {
            println("hi...")
        }

        // 可变参数
        def test7(name: String*): Unit = {
            println(name)
        }

        test7("Tom", "Jack", "Jerry")
        test7()

        // 默认参数
        def test8(name: String, age: Int = 10): Unit = {
            println(s"${name} - ${age}")
        }

        test8("Candy")

        def test9(age: Int = 10, name: String): Unit = {
            println(s"${name} - ${age}")
        }
        // 参数匹配规则从前到后，从左到右
        // 错误
        //test9("money")
        test9(20, "Betty")
        // 带名参数
        test9(name = "Jack")

        /**
         * 函数式编程
         * 1. 函数可以赋值给变量
         * 2. 函数可以作为函数的参数
         * 3. 函数可以作为函数的返回值
         */
        def fun(): Unit = {
            println("function")
        }

        def f0() = {
            // 返回函数
            // 函数体重返回函数，会有问题，需要增加特殊符号：下划线
            fun _
        }

        f0()() // 调用

        // TODO 函数柯里化
        def f3(i: Int)(j: Int): Int = {
            i * j
        }

        // TODO 闭包
        // 一个函数在实现逻辑时，将外部变量引入到函数的内容，改变了变量的生命周期，称之为闭包
        def f1(i: Int) = {
            def f2(j: Int): Int = {
                i * j
            }

            f2 _
        }

        // TODO 将函数作为参数传递给另外一个函数，需要采用特殊的声明方式
        def f4(f: () => Int): Int = {
            f() + 10
        }

        def f5(): Int = {
            5
        }

        println(f4(f5))

        // 有参数的函数传递
        def f7(f: (Int) => Unit): Unit = {
            f(10)
        }

        def f8(i: Int): Unit = {
            println(i)
        }

        f7(f8)
        // 使用匿名函数代替
        f7((i: Int) => {
            println(i)
        })
        f7((i) => {
            println(i)
        })
        f7((i) => println(i))
        f7(println(_))
        f7(println)

        // 两个参数的函数传递
        def f9(f: (Int, Int) => Int): Int = {
            f(10, 10)
        }

        def f10(i: Int, j: Int): Int = {
            i + j
        }

        println(f9(f10))
        println(f9((x: Int, y: Int) => {
            x + y
        }))
        println(f9((x, y) => {
            x + y
        }))
        println(f9((x, y) => x + y))
        println(f9((x, y) => x + y))
        println(f9(_+_))

    }
}
