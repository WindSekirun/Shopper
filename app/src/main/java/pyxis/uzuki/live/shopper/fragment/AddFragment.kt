package pyxis.uzuki.live.shopper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.fragment_add.*
import pyxis.uzuki.live.richutilskt.utils.hideKeyboard
import pyxis.uzuki.live.richutilskt.utils.inflate
import pyxis.uzuki.live.richutilskt.utils.isEmpty
import pyxis.uzuki.live.shopper.Constants.CLICK_EDIT
import pyxis.uzuki.live.shopper.Constants.DIALOG_DELTE
import pyxis.uzuki.live.shopper.Constants.STATE_NOT_ADDED
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.adapter.ShopperItemListAdapter
import pyxis.uzuki.live.shopper.dialog.ShopperBuyDialog
import pyxis.uzuki.live.shopper.dialog.ShopperEditDialog
import pyxis.uzuki.live.shopper.item.ShopperItem
import pyxis.uzuki.live.shopper.snackBar

/**
 * Shopper
 * Class: AddFragment
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class AddFragment : Fragment() {
    private lateinit var adapter: ShopperItemListAdapter
    private val itemList = arrayListOf<ShopperItem>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return activity.inflate(R.layout.fragment_add, container)
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

        editItem.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addShopperItem(view)
                true
            } else {
                false
            }
        }
    }

    private fun getData() {
        itemList.clear()
        adapter.notifyDataSetChanged()

        val items = ShopperItem.listAll(ShopperItem::class.java)
        val list = items.filter { it.state == STATE_NOT_ADDED }
        itemList.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun addShopperItem(view: View) {
        activity.hideKeyboard()
        val text = editItem.text.toString()
        if (text.isEmpty() || text.trim().isEmpty()) {
            view.snackBar(R.string.enter_item_name)
            return
        }


        val item = ShopperItem(text)
        item.save()

        editItem.setText("")
        getData()
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
}