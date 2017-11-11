package pyxis.uzuki.live.shopper

import android.support.design.widget.Snackbar
import android.view.View
import pyxis.uzuki.live.shopper.item.ShopperItem

/**
 * Shopper
 * Class: Extension
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

fun View.snackBar(msg: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, length)
}

fun View.snackBar(msg: Int, length: Int = Snackbar.LENGTH_SHORT) {
    snackBar(this.resources.getString(msg), length)
}

fun getShopper(itemName: String): ShopperItem {
    return ShopperItem().apply {
        name = itemName
        createdAt = System.currentTimeMillis()
        state = Constants.STATE_NOT_ADDED
    }
}