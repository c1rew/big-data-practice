import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}

/**
 * @author c1rew    
 * @create 2020-10-01 10:16
 */
object Spark_Oper {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("Spark02_Oper")

        val sc = new SparkContext(conf)
        val listRDD = sc.makeRDD(1 to 10)

        // 所有RDD算子的计算功能全部由Executor执行
        val mapRDD = listRDD.map(_ * 2)
        mapRDD.collect().foreach(println)

        // mapPartitions可以对承恩RDD中所有分区进行遍历
        // mapPartitions效率优于map算子，减少了发送到执行器执行交互次数
        // mapPartitions可能出现内存溢出（OOM）
        val mapPartitionsRDD = listRDD.mapPartitions(datas => {
            datas.map(data => data * 2)
        })
        mapPartitionsRDD.collect().foreach(println)

        // mapPartitionsWithIndex
        val numRDD = sc.makeRDD(1 to 10, 2)
        val tupleRDD = numRDD.mapPartitionsWithIndex {
            case (num, datas) => {
                datas.map(("分区：" + num, _))
            }
        }
        tupleRDD.collect().foreach(println)

        // glom
        val listRDD1 = sc.makeRDD(1 to 16, 5)
        // 将一个分区的数据放到一个数组中
        val glomRDD = listRDD1.glom()
        glomRDD.collect().foreach({
            array => println(array.mkString(","))
        })
    }

}
