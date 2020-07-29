package org.example.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author c1rew
 * @create 2020-06-16 22:10
 *
 * 实现分组固定步骤
 * 1. 继承WritableComparator
 * 2. 调用父类构造器
 * 3. 指定分组规则，重写compare方法
 */
public class OrderComparator extends WritableComparator {

    protected OrderComparator() {
        // 参数一，分组使用的javaBean，
        super(OrderBean.class, true);
    }

    /**
     *
     * @param a WritableComparable是接口，理解为orderBean
     * @param b WritableComparable是接口，理解为orderBean
     * @return
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        // 1. 强制类型转换
        OrderBean oa = (OrderBean) a;
        OrderBean ob = (OrderBean) b;
        // 2. 指定分组规则，按照订单分组
        return oa.getOrderId().compareTo(ob.getOrderId());
    }
}
