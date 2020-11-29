package com.viper.utils

import com.viper.product.HotProducts.UserBehavior

/**
 * @author c1rew    
 * @create 2020-11-29 20:56
 */
object Utils {
    val userBehaviorLogPath = ""

    def string2UserBehavior(line: String): UserBehavior = {
        val fileds: Array[String] = line.split(",")
        UserBehavior(
            fileds(0).trim.toLong,
            fileds(1).trim.toLong,
            fileds(2).trim.toLong,
            fileds(3).trim,
            fileds(4).trim.toLong,
            fileds(5).trim)
    }
}
