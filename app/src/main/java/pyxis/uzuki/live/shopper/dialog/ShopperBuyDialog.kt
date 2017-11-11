package pyxis.uzuki.live.shopper.dialog

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_buy.*
import pyxis.uzuki.live.richutilskt.utils.toast
import pyxis.uzuki.live.shopper.Constants
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.item.ShopperItem
import java.lang.Exception

/**
 * Shopper
 * Class: ShopperEditDialog
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class ShopperBuyDialog(context: Context) : BaseDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_buy)
    }

    fun show(item: ShopperItem, callback: (Int, ShopperItem) -> Unit) {
        super.show()

        item.let {
            txtName.text = context.getString(R.string.dialog_enter_item_name).format(item.name)
        }

        btnClose.setOnClickListener { dismiss() }
        btnDone.setOnClickListener {
            val count = editCount.text.toString().trim()
            val price = editCount.text.toString().trim()

            try {
                item.count = count.toInt()
                item.price = price.toFloat()
            } catch (e: Exception) {
                context.toast(context.getString(R.string.dialog_buy_not_valid))
            }

            item.state = Constants.STATE_ADDED
            callback(Constants.DIALOG_BUY, item)
            dismiss()
        }
    }
}