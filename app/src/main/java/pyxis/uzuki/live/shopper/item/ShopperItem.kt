package pyxis.uzuki.live.shopper.item

import io.realm.RealmObject

/**
 * Shopper
 * Class: ShopperItem
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

open class ShopperItem : RealmObject() {
    var name: String = ""
    var createdAt: Long = 0L
    var count: Int = 0
    var price: Float = 0.0f
    var state = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShopperItem

        if (name != other.name) return false
        if (createdAt != other.createdAt) return false
        if (count != other.count) return false
        if (price != other.price) return false
        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + count
        result = 31 * result + price.hashCode()
        result = 31 * result + state
        return result
    }

    override fun toString(): String {
        return "ShopperItem(name='$name', createdAt=$createdAt, count=$count, price=$price, state=$state)"
    }

}