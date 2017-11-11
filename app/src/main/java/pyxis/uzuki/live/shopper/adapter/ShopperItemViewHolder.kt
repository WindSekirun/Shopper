package pyxis.uzuki.live.shopper.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_list_item.view.*
import pyxis.uzuki.live.shopper.Constants.CLICK_EDIT
import pyxis.uzuki.live.shopper.Constants.CLICK_LIST
import pyxis.uzuki.live.shopper.item.ShopperItem

/**
 * Shopper
 * Class: ShopperItemViewHolder
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class ShopperItemViewHolder(itemView: View, val callback: (Int, ShopperItem) -> Unit) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(item: ShopperItem) = with(item) {
        itemView.txtName.text = name

        if (item.count != 0 && item.price != 0.0f) {
            itemView.txtCount.text = "${item.count}개\n${item.price}원"
            itemView.txtCount.visibility = View.VISIBLE
        } else {
            itemView.txtCount.visibility = View.GONE
        }

        itemView.btnEdit.setOnClickListener { callback(CLICK_EDIT, item) }

        itemView.setOnClickListener { callback(CLICK_LIST, item) }
    }

}