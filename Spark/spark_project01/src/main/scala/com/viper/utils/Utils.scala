package com.viper.utils

import org.apache.commons.lang.StringUtils

object Utils {
    /**
     * 将字符串转换为Int
     *
     * @param s 输入字符串
     * @return 返回Int值
     */
    def parseInt(s: String): Int = {
        try {
            if (StringUtils.isNotEmpty(s)) {
                s.toInt
            } else {
                0
            }
        } catch {
            case _ => 0
        }
    }

    /**
     * 将字符串转换为Double
     *
     * @param s 输入字符串
     * @return 返回Double值
     */
    def parseDouble(s: String): Double = {
        try {
            StringUtils.isNotEmpty(s) match {
                case true => s.toDouble
                case false => 0.0
            }
        } catch {
            case _ => 0.0
        }
    }

    /**
     * 格式化日期
     *
     * @param s 2018-12-12 12:11:10
     * @return 返回日期 20181212
     */
    def fmtDate(s: String): Option[String] = {
        try {
            if (StringUtils.isNotEmpty(s)) {
                val fields = s.split(" ")
                if (fields.length > 1) {
                    Some(fields(0).replace("-", ""))
                } else {
                    None
                }
            } else {
                None
            }
        } catch {
            case _ => None
        }
    }

    /**
     * 格式化时间
     *
     * @param s 2018-12-12 12:11:10
     * @return 返回小时 12
     */
    def fmtHour(s: String): Option[String] = {
        try {
            if (StringUtils.isNotEmpty(s)) {
                val fields = s.split(" ")
                if (fields.length > 1) {
                    Some(fields(1).substring(0, 2))
                } else {
                    None
                }
            } else {
                None
            }
        } catch {
            case _ => None
        }
    }

}
