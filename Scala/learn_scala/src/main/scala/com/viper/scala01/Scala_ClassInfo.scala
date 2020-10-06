package com.viper.scala01

/**
 * @author c1rew    
 * @create 2020-10-04 17:27
 */
object Scala_ClassInfo {
    def main(args: Array[String]): Unit = {
        // UserClass.class
        // object.getClass
        // TODO scala想要获取类的信息，需要采用特殊的方式
        // classOf能直接使用，是因为scala.Predef伴生对象是默认导入到当前开发环境中
        val userClass: Class[UserClass] = classOf[UserClass]

        userClass.getInterfaces
    }
}


class UserClass {

}

// App，会自动导入scala包中的类，可执行
object Scala_ClassInfo1 extends App {
    println("hello...")
}

// 枚举
object Color extends Enumeration {

}