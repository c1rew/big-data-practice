import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author c1rew    
 * @create 2020-09-29 23:36
 */
object Spark01_RDD {
    def main(args: Array[String]): Unit = {
        // SparkConf配置文件
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark01_RDD")
        // 创建SparkContext对象
        val sc: SparkContext = new SparkContext(conf)

        // 创建RDD
        // 1. 从内存中创建 makeRDD，底层实现就是parallelize
        val listRDD = sc.makeRDD(List(1, 2, 3, 4))
        listRDD.collect().foreach(println)
        // 2. 从内存中创建  parallelize
        val arrayRDD = sc.parallelize(Array(3, 5, 7, 9))
        arrayRDD.collect().foreach(println)

        // 3. 从外部存储中创建
        // 默认情况下，可以读取项目路径，也可以读取其他路径，如hdfs
        // 默认从文件中读取的数据都是字符串类型
        // 读取文件时，传递的分区参数为最小分区数，但是不一定是这个分区数，取决于hadoop读取文件时的分片规则
        //val fileRDD = sc.textFile("in")
        //fileRDD.saveAsTextFile("output")
    }
}
