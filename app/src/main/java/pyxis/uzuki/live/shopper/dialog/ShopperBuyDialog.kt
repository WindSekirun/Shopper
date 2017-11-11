package pyxis.uzuki.live.shopper.dialog

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_buy.*
import pyxis.uzuki.live.richutilskt.utils.toast
import pyxis.uzuki.live.shopper.Constants.DIALOG_BUY
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.Constants.STATE_ADDED
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

            if (item.price != 0.0f) {
                editPrice.setText(item.price.toString())
            }

            if (item.count != 0) {
                editCount.setText(item.count.toString())
            }
        }

        btnClose.setOnClickListener { dismiss() }
        btnDone.setOnClickListener {
            val count = editCount.text.toString().trim()
            val price = editPrice.text.toString().trim()

            try {
                item.count = count.toInt()
                item.price = price.toFloat()
                if (item.count != 0)
                    item.price = item.price * item.count
            } catch (e: Exception) {
                context.toast(context.getString(R.string.dialog_buy_not_valid))
            }

            item.state = STATE_ADDED
            callback(DIALOG_BUY, item)
            dismiss()
        }
    }
}