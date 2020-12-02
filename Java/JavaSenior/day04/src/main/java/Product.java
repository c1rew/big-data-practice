/**
 * @author c1rew
 * @create 2020-12-02 20:36
 */
public class Product implements Comparable<Product> {
    private String name;
    private double price;

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * 指明排序方式，按照商品价格从低到高
     *
     * @param o
     * @return
     */
    public int compareTo(Product o) {
        if (o instanceof Product) {
            Product product = (Product) o;

            // 方法一
            if (this.price > product.price) {
                return 1;
            } else if (this.price < product.price) {
                return -1;
            } else {
                return 0;
            }
            // 方法二
            //Double.compare(this.price, product.price);
        }
        throw new RuntimeException("传入类型不一致");
    }
}
