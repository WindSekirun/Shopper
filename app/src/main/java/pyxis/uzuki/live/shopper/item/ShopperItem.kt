package pyxis.uzuki.live.shopper.item

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey



/**
 * Shopper
 * Class: ShopperItem
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

open class ShopperItem : RealmObject() {
    @PrimaryKey
    val id: Int = 0

    var name: String = ""
    var createdAt: Long = 0L
    var count: Int = 0
    var price: Float = 0.0f
    var state = 0
}