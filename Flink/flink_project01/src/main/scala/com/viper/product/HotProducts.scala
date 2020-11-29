package com.viper.product

import com.viper.utils.Utils
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author c1rew    
 * @create 2020-11-29 20:56
 */
object HotProducts {

    // 用户行为对象
    case class UserBehavior(
                               userId: Long, // 用户id
                               productId: Long, // 商品id
                               categoryId: Long, // 商品品类id
                               behaviorType: String, // 行为类型
                               timeStamp: Long, // 时间
                               sessionId: String // 会话id
                           )

    def main(args: Array[String]): Unit = {
        // 1. 获取执行环境
        val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

        env.setParallelism(1)
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)


        env.readTextFile("")
            .map(Utils.string2UserBehavior(_))
            .assignTimestampsAndWatermarks(new EventTimeExtractor)
            .filter(_.behaviorType == "P")
            .keyBy(_.productId)
            .timeWindow(Time.hours(1),Time.minutes(5))
            .aggregate()



        // 启动程序
        env.execute("hotProducts")
    }

    /**
     * 指定时间字段
     * 指定延迟时间
     */
    class EventTimeExtractor extends AssignerWithPeriodicWatermarks[UserBehavior] {
        override def getCurrentWatermark: Watermark = ???

        override def extractTimestamp(t: UserBehavior, l: Long): Long = ???
    }

}
