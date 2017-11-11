package pyxis.uzuki.live.shopper.item;

import com.orm.SugarRecord;

import static pyxis.uzuki.live.shopper.Constants.STATE_NOT_ADDED;

/**
 * Shopper
 * Class: ShopperItem
 * Created by pyxis on 11/11/2017.
 * <p>
 * Description:
 */

public class ShopperItem extends SugarRecord {
     private String name;
     private long createdAt;
     private int count;
     private float price;
     private int state = STATE_NOT_ADDED;

    public ShopperItem() {

    }

    public ShopperItem(String name) {
        this.name = name;
        createdAt = System.currentTimeMillis();
    }

    public ShopperItem(String name, long createdAt, int count, float price, int state) {
        this.name = name;
        this.createdAt = createdAt;
        this.count = count;
        this.price = price;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopperItem that = (ShopperItem) o;

        if (createdAt != that.createdAt) return false;
        if (count != that.count) return false;
        if (Float.compare(that.price, price) != 0) return false;
        if (state != that.state) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (createdAt ^ (createdAt >>> 32));
        result = 31 * result + count;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + state;
        return result;
    }


    @Override
    public String toString() {
        return "ShopperItem{" +
                "name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", count=" + count +
                ", price=" + price +
                ", state=" + state +
                '}';
    }
}
