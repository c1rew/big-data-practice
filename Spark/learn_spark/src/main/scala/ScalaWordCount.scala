import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author c1rew    
 * @create 2020-09-20 20:21
 */
object ScalaWordCount {
    def main(args: Array[String]): Unit = {
        // SparkConf配置文件
        val conf: SparkConf = new SparkConf()
        // 创建SparkContext对象
        val sc: SparkContext = new SparkContext(conf)
        // 指定输入文件
        val lines: RDD[String] = sc.textFile(args(0))
        // 切分
        val words: RDD[String] = lines.flatMap(_.split(" "))
        // 单词计数
        val wordAndOne: RDD[(String, Int)] = words.map((_, 1))

        // 累加
        val result: RDD[(String, Int)] = wordAndOne.reduceByKey(_ + _)
        // 排序
        val finalResult: RDD[(String, Int)] = result.sortBy(_._2, false)

        // 将统计结果保存到文件
        // finalResult.saveAsTextFile(args(1))

        // 统计结果输出到终端
        val resultTuples = finalResult.collect()
        resultTuples.foreach(println);

        // 回收资源
        sc.stop()
    }
}
