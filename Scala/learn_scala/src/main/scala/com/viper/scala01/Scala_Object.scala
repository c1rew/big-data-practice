package com.viper.scala01

/**
 * @author c1rew    
 * @create 2020-10-02 14:40
 */
object Scala_Object {
    def main(args: Array[String]): Unit = {
        val user = new User()

        user.username = "Jack"

        println(user.username)
    }
}
// 声明类
class User {
    // 声明属性，_ 代表 使用默认初始化的值，由虚拟机分配
    var username :String = _
    var age:Int = _

    def login(): Boolean ={
        true
    }
}