package org.nx.mrhomework;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 2、求该成绩表当中出现了相同分数的分数，还有次数，以及该分数的人数
 * 返回结果的格式：
 * 科目	分数	次数	该分数的人
 * 例子：
 * computer	85	3	huangzitao,liujialing,huangxiaoming
 */
class studentScore implements Writable {
    private String course;
    private String name;
    private int score;


    public void set(String course, String name, int score) {
        this.course = course;
        this.name = name;
        this.score = score;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //Unable to initialize MapOutputCollector org.apache.hadoop.mapred.MapTask$MapOutputBuffer
    // 自定义类，要继承Writable相关的类，并实现以下两个接口，否则会报上述错误。

    /**
     * 序列化
     *
     * @param dataOutput 框架提供的数据出口
     * @throws IOException
     */
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(course);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(score);
    }

    /**
     * 反序列化
     *
     * @param dataInput 框架提供的数据来源
     * @throws IOException
     */
    public void readFields(DataInput dataInput) throws IOException {
        course = dataInput.readUTF();
        name = dataInput.readUTF();
        score = dataInput.readInt();
    }
}

public class studentScoreNormal2 {
    private static class Map extends Mapper<LongWritable, Text, Text, studentScore> {
        private Text course = new Text();

        private studentScore stu;

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            // test file scorelist.txt
            String[] fields = value.toString().split(",");
            course.set(fields[0]);
            stu = new studentScore();
            stu.set(fields[0], fields[1], Integer.parseInt(fields[2]));
            context.write(course, stu);

        }
    }

    private static class Reduce extends Reducer<Text, studentScore, Text, Text> {

        private HashMap<Integer, ArrayList<String>> scoreMap;

        @Override
        protected void reduce(Text key, Iterable<studentScore> values, Context context) throws IOException, InterruptedException {
            // 每一科，初始化一个map
            scoreMap = new HashMap<Integer, ArrayList<String>>();

            for (studentScore stu : values) {
                Integer score = stu.getScore();
                String name = stu.getName();
                // 如果key中已包含该分数，则将名字添加到value的数组中
                if (scoreMap.containsKey(score)) {
                    scoreMap.get(score).add(name);
                } else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(name);
                    scoreMap.put(score, list);
                }

            }
            scoreMap.forEach((k, v) -> {
                if (v.size() > 1) {
                    Text value = new Text(k + "\t" + v.size() + "\t" + StringUtils.join(",", v));
                    try {
                        context.write(key, value);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance();

        job.setJarByClass(studentScoreNormal2.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(studentScore.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        Path outputPath = new Path(args[1]);
        FileSystem fs = FileSystem.get(conf);
        // 输出文件夹如果存在就删除
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        FileOutputFormat.setOutputPath(job, outputPath);

        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);
    }
}
