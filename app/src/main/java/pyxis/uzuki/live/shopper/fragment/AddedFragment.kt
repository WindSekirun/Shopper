package pyxis.uzuki.live.shopper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.fragment_added.*
import pyxis.uzuki.live.richutilskt.utils.hideKeyboard
import pyxis.uzuki.live.richutilskt.utils.inflate
import pyxis.uzuki.live.shopper.Constants
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.adapter.ShopperItemListAdapter
import pyxis.uzuki.live.shopper.dialog.ShopperBuyDialog
import pyxis.uzuki.live.shopper.dialog.ShopperEditDialog
import pyxis.uzuki.live.shopper.formatCurrency
import pyxis.uzuki.live.shopper.item.ShopperItem

/**
 * Shopper
 * Class: AddedFragment
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class AddedFragment : Fragment() {

    lateinit var adapter: ShopperItemListAdapter
    val itemList = arrayListOf<ShopperItem>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return activity.inflate(R.layout.fragment_added, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getData()
    }

    private fun initView() {
        adapter = ShopperItemListAdapter(activity, itemList, { code, item ->
            if (code == Constants.CLICK_EDIT) {
                clickEditButton(item)
            } else {
                clickList(item)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

    }

    private fun getData() {
        itemList.clear()
        adapter.notifyDataSetChanged()

        val items = ShopperItem.listAll(ShopperItem::class.java)
        if (items.isEmpty())
            return

        val list = items.filter { it.state == Constants.STATE_ADDED }
        itemList.addAll(list)
        adapter.notifyDataSetChanged()

        var price = 0.0f
        var count = 0

        items.forEach {
            price += it.price
            count += it.count
        }

        txtTotal.text = getString(R.string.total_formatted).format(count, price.formatCurrency(activity))
    }

    private fun clickEditButton(shopperItem: ShopperItem) {
        val dialog = ShopperEditDialog(activity)
        dialog.show(shopperItem, { code, item ->
            activity.hideKeyboard()
            if (code == Constants.DIALOG_DELTE) {
                item.delete()
                getData()
                return@show
            }

            item.save()
            getData()
        })
    }

    private fun clickList(shopperItem: ShopperItem) {
        val dialog = ShopperBuyDialog(activity)
        dialog.show(shopperItem, { _, item ->
            activity.hideKeyboard()
            item.save()
            getData()
        })
    }
}