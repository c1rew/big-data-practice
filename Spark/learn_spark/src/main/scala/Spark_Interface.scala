/**
 * @author c1rew    
 * @create 2020-10-04 16:41
 */
object Spark_Interface {
    def main(args: Array[String]): Unit = {
        val mysql = new MySQL()
        mysql.insert()


        // trait的动态混入，给对象动态扩展功能 OCP开发原则
        val mysql2 = new MySQL2 with Operate
        mysql2.insert()
    }
}

trait Operate {
    println("Operator...")

    def insert(): Unit = {
        println("插入数据")
    }
}

trait DB extends Operate {
    println("DB...")

    override def insert(): Unit = {
        print("向数据库")
        super.insert()
    }
}

trait File extends Operate {
    println("File...")

    override def insert(): Unit = {
        print("向文件")
        super.insert()
        //super[Operate].insert()
    }
}

/**
 * scala中没有Interface
 *
 *
 * 1. 特质是可以继承的，使用extends关键字
 * 2. 如果类继承多个特质，采用with连接
 * 3. 如果类同时存在父类和特质，依然采用继承方式，继承父类，连接（混入）特质
 * 4. 特质可以声明抽象方法
 *
 * 1. 多特质混入时，代码执行顺序从左到右，如果有父特质，会优先执行
 * 2. 多特质混入时，方法调用的顺序为从右到左
 * 3. 特质中super关键之不是指代父特质，指代的是上一级特质
 * 4. 如果希望super指向父特质，需要增加特殊操作  super[特质]
 * 5. Java中的接口可以在scala中当成特质使用
 */
class MySQL extends File with DB with Serializable {

}

class MySQL2 {

}

// 特质与异常的混用
trait Operate2 extends Exception {
    def insert(): Unit = {
        println("插入数据")
        this.getMessage
    }
}

trait Operate3 {
    // TODO 约束特质使用范围
    this: Exception =>
    def insert() {
        println("插入数据")
        this.getMessage
    }
}

class MySQL3 extends Exception with Operate3 {

}