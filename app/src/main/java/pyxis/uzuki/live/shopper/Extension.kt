package pyxis.uzuki.live.shopper

import android.content.Context
import android.os.Build
import android.support.design.widget.Snackbar
import android.view.View
import java.text.NumberFormat
import java.util.*

/**
 * Shopper
 * Class: Extension
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

object Constants {
    @JvmField val STATE_NOT_ADDED = 1
    @JvmField val STATE_ADDED = 2

    @JvmField val CLICK_EDIT = 2
    @JvmField val CLICK_LIST = 1

    @JvmField val DIALOG_DELTE = -1
    @JvmField val DIALOG_EDIT = 1
    @JvmField val DIALOG_BUY = 1
}

fun View.snackBar(msg: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, length)
}

fun View.snackBar(msg: Int, length: Int = Snackbar.LENGTH_SHORT) {
    snackBar(this.resources.getString(msg), length)
}

fun Context.getCurrentLocale() = if (Build.VERSION.SDK_INT >= 24) {
    this.resources.configuration.locales[0]
} else {
    this.resources.configuration.locale
}

fun Float.formatCurrency(context: Context) =
        NumberFormat.getCurrencyInstance(context.getCurrentLocale()).format(this) ?: ""