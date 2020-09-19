import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author c1rew
 * @date 2020-09-18 13:12
 */

public class JavaWordCount {
    public static void main(String[] args) {
        // 配置文件对象
        SparkConf conf = new SparkConf().setAppName("MySparkWordCount");

        // 创建一个SparkContext对象
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 输入文件
        JavaRDD<String> rdd1 = sc.textFile(args[0]);

        /** 切分
         * FlatMapFunction 匿名类
         * 两个参数，第一个是输入类型，第二个是输出类型
         *
         * call方法，输入一行数据，返回一个迭代器
         * 迭代器中放了一行中所有切割的单词
         */
        JavaRDD<String> rdd2 = rdd1.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                String[] words = s.split(" ");
                return Arrays.asList(words).iterator();
            }
        });

        /** 计数
         * 每个单词每出现一次就计数为1
         * 在scala中调用的是map方法,而在java中调用的则是mapToPair方法，
         * mapToPair方法表示将一个map变成一个元组；
         *
         * 匿名函数PairFunction的泛型有3个：
         *  (1)第一个参数表示输入，这里输入的是单词；
         *  (2)第二个和第三个参数是返回的元组中的两个元素的数据类型，这里是单词和数字 1；
         *
         * 在java中没有Tuple类型的数据结构，所以它就搞了一个Tuple2类来模拟Scala中的Tuple；
         */
        JavaPairRDD<String, Integer> rdd3 = rdd2.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });

        /** 累加
         * 这里用groupByKey也可以，但是效率比reduceByKey低
         * 因为reduceByKey会做局部聚合，类似mapreduce的combine过程
         *
         * Function2匿名类，
         * 三个参数，两个是输入的类型，最后一个是返回值类型
         */
        JavaPairRDD<String, Integer> rdd4 = rdd3.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        /**
         * 排序
         *
         * java只提供了sortByKey，只能按照key进行排序，
         * 将key和value进行交换，在根据key进行排序后再交换回来;
         */
        JavaPairRDD<Integer, String> swapedPair = rdd4.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<String, Integer> tp) throws Exception {
                return new Tuple2<Integer, String>(tp._2, tp._1);
            }
        });

        // 根据Key排序，按照count大小排序
        JavaPairRDD<Integer, String> sortedPair = swapedPair.sortByKey(false);

        JavaPairRDD<String, Integer> finalResult = sortedPair.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<Integer, String> tp) throws Exception {
                return tp.swap();
            }
        });

        // 触发计算
        List<Tuple2<String, Integer>> result = finalResult.collect();

        // 打印
        for (Tuple2<String, Integer> r : result) {
            System.out.println(r._1 + "\t" + r._2);
        }

        // 回收资源
        sc.close();
    }
}
