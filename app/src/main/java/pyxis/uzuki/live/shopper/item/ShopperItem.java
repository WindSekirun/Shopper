package pyxis.uzuki.live.shopper.item;

import static pyxis.uzuki.live.shopper.Constants.STATE_NOT_ADDED;

/**
 * Shopper
 * Class: ShopperItem
 * Created by pyxis on 11/11/2017.
 * <p>
 * Description:
 */

public class ShopperItem extends BaseItem {
    private String name;

    private int count;
    private float price;
    private int state = STATE_NOT_ADDED;

    public ShopperItem() {

    }

    public ShopperItem(String name) {
        this.name = name;
        createdAt = System.currentTimeMillis();
    }

    public ShopperItem(String name, int count, float price, int state) {
        this.name = name;
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

}
