package pyxis.uzuki.live.shopper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_added.*
import pyxis.uzuki.live.richutilskt.utils.hideKeyboard
import pyxis.uzuki.live.richutilskt.utils.inflate
import pyxis.uzuki.live.shopper.*
import pyxis.uzuki.live.shopper.Constants.CLICK_EDIT
import pyxis.uzuki.live.shopper.Constants.DIALOG_DELTE
import pyxis.uzuki.live.shopper.Constants.STATE_ADDED
import pyxis.uzuki.live.shopper.adapter.ShopperItemListAdapter
import pyxis.uzuki.live.shopper.dialog.ShopperBuyDialog
import pyxis.uzuki.live.shopper.dialog.ShopperEditDialog
import pyxis.uzuki.live.shopper.item.ShopperItem

/**
 * Shopper
 * Class: AddedFragment
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class AddedFragment : Fragment() {
    private lateinit var adapter: ShopperItemListAdapter
    private val itemList = arrayListOf<ShopperItem>()

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
            if (code == CLICK_EDIT) {
                clickEditButton(item)
            } else {
                clickList(item)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        txtDone.setOnClickListener {
            itemList.forEach {
                it.delete()
            }

            getData()
        }
    }

    private fun getData() {
        itemList.clear()
        adapter.notifyDataSetChanged()

        val items = ShopperItem.listAll(ShopperItem::class.java)
        val list = items.filter { it.state == STATE_ADDED }
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
            if (code == DIALOG_DELTE) {
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

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            getData()
        }
        super.setUserVisibleHint(isVisibleToUser)
    }
}