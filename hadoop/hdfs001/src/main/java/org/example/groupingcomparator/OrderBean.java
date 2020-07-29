package org.example.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author c1rew
 * @create 2020-06-15 23:52
 */
public class OrderBean implements WritableComparable<OrderBean> {

    private String orderId;
    private String productId;
    private Double price;

    @Override
    public String toString() {
        return orderId + '\t' + productId + '\t' + price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 排序规则
     * @param o
     * @return
     */
    @Override
    public int compareTo(OrderBean o) {
        int compare = this.orderId.compareTo(o.orderId);

        // 订单相同，判断价格
        if (compare == 0) {
            // 默认升序，乘 -1 出来是降序
            compare = this.price.compareTo(o.price) * -1;
        }
        return compare;
    }

    /**
     * 序列化对象
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(productId);
        dataOutput.writeDouble(price);
    }

    /**
     * 反序列化对象
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderId = dataInput.readUTF();
        this.productId = dataInput.readUTF();
        this.price = dataInput.readDouble();
    }
}
