package pyxis.uzuki.live.shopper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import pyxis.uzuki.live.richutilskt.utils.inflate
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.item.ShopperItem

/**
 * Shopper
 * Class: ShopperItemListAdapter
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class ShopperItemListAdapter(val context: Context, val itemList: List<ShopperItem>, val callback: (Int, ShopperItem) -> Unit)
    : RecyclerView.Adapter<ShopperItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopperItemViewHolder =
            ShopperItemViewHolder(context, context.inflate(R.layout.fragment_list_item, parent), callback)

    override fun onBindViewHolder(holder: ShopperItemViewHolder?, position: Int) {
        holder?.bindItem(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}