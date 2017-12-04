package pyxis.uzuki.live.shopper.item;

import com.orm.SugarRecord;

/**
 * Shopper
 * Class: BaseItem
 * Created by Pyxis on 2017-12-04.
 * <p>
 * Description:
 */

public class BaseItem extends SugarRecord {
    protected long createdAt;


    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
