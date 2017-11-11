package pyxis.uzuki.live.shopper.dialog

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_edit.*
import kotlinx.android.synthetic.main.fragment_add.*
import pyxis.uzuki.live.richutilskt.utils.isEmpty
import pyxis.uzuki.live.richutilskt.utils.toast
import pyxis.uzuki.live.shopper.Constants
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.item.ShopperItem

/**
 * Shopper
 * Class: ShopperEditDialog
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class ShopperEditDialog(context: Context) : BaseDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_edit)
    }

    fun show(item: ShopperItem, callback: (Int, ShopperItem) -> Unit) {
        super.show()

        item.let {
            editName.setText(item.name)
            editName.setSelection(editName.text.length)
        }

        btnClose.setOnClickListener { dismiss() }

        btnDelete.setOnClickListener {
            callback(Constants.DIALOG_DELTE, item)
            dismiss()
        }

        btnDone.setOnClickListener {
            val text = editName.text.toString()
            if (text.isEmpty()) {
                context.toast(R.string.enter_item_name)
                return@setOnClickListener
            }

            item.name = text

            callback(Constants.DIALOG_EDIT, item)
            dismiss()
        }
    }
}