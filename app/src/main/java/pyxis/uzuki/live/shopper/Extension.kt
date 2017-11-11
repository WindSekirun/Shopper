package pyxis.uzuki.live.shopper

import android.content.Context
import android.os.Build
import android.support.design.widget.Snackbar
import android.view.View
import java.text.NumberFormat

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

fun Context.getCurrentLocale() = if (Build.VERSION.SDK_INT >= 24) {
    this.resources.configuration.locales[0]
} else {
    this.resources.configuration.locale
}

fun Float.formatCurrency(context: Context) =
        NumberFormat.getCurrencyInstance(context.getCurrentLocale()).format(this)