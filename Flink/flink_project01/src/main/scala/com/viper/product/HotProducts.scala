package com.viper.product

import com.viper.utils.Utils
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.{AssignerWithPeriodicWatermarks, KeyedProcessFunction}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

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

    // 窗口统计结果
    case class ProductViewCount(productId: Long,
                                WindowEnd: Long,
                                count: Long)

    def main(args: Array[String]): Unit = {
        // 1. 获取执行环境
        val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

        env.setParallelism(1)
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)


        env.readTextFile("") // 读取数据
            .map(Utils.string2UserBehavior(_)) // 解析数据
            .assignTimestampsAndWatermarks(new EventTimeExtractor) // 指定watermark
            .filter(_.behaviorType == "P") //过滤用户行为数据
            .keyBy(_.productId) // 按商品进行分组
            .timeWindow(Time.hours(1), Time.minutes(5))
            .aggregate(new CountProduct,new WindowResult) // 计算窗口数据
            .keyBy(_.WindowEnd)  // 按照窗口进行分组
            .process()



        // 启动程序
        env.execute("hotProducts")
    }

    class TopHotProduct(topN:Int) extends KeyedProcessFunction[Long,ProductViewCount,String] {
        override def processElement(i: ProductViewCount, context: KeyedProcessFunction[Long, ProductViewCount, String]#Context, collector: Collector[String]): Unit = ???
    }


    class WindowResult extends WindowFunction[Long,ProductViewCount,Long,TimeWindow] {
        override def apply(key: Long,
                           window: TimeWindow,
                           input: Iterable[Long],
                           out: Collector[ProductViewCount]): Unit = {
            out.collect(ProductViewCount(key,window.getEnd,input.iterator.next()))
        }
    }


    /**
     * IN, 输入数据类型
     * ACC，辅助变量数据类型
     * OUT，输出数据类型
     */
    class CountProduct extends AggregateFunction[UserBehavior, Long, Long] {
        override def createAccumulator(): Long = 0L

        override def add(in: UserBehavior, acc: Long): Long = acc + 1

        override def getResult(acc: Long): Long = acc

        override def merge(acc: Long, acc1: Long): Long = acc + acc1
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
