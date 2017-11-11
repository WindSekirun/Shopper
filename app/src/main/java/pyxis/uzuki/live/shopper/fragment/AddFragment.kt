package pyxis.uzuki.live.shopper.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_list_item.*
import pyxis.uzuki.live.richutilskt.utils.isEmpty
import pyxis.uzuki.live.shopper.Constants
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.adapter.ShopperItemListAdapter
import pyxis.uzuki.live.shopper.dialog.ShopperBuyDialog
import pyxis.uzuki.live.shopper.dialog.ShopperEditDialog
import pyxis.uzuki.live.shopper.getShopper
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

    lateinit var adapter: ShopperItemListAdapter
    lateinit var fragmentView: View
    val itemList = arrayListOf<ShopperItem>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflate(activity, R.layout.fragment_add, container)
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

        editItem.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addShopperItem(view)
                true
            } else {
                false
            }
        }

        btnEdit.setOnClickListener { addShopperItem(it) }
    }

    private fun getData() {
        val items = ShopperItem().queryAll()
        if (items.isEmpty())
            return

        itemList.clear()
        itemList.addAll(items.filter { it.state == Constants.STATE_NOT_ADDED })
        adapter.notifyDataSetChanged()
    }

    private fun addShopperItem(view: View) {
        val text = editItem.text.toString()
        if (text.isEmpty() && text.trim().isEmpty()) {
            view.snackBar(R.string.enter_item_name)
        }

        getShopper(text).save()
        getData()
    }

    private fun clickEditButton(shopperItem: ShopperItem) {
        val dialog = ShopperEditDialog(activity)
        dialog.show(shopperItem, { code, item ->
            if (code == Constants.DIALOG_DELTE) {
                ShopperItem().delete { it -> it.equalTo("id", shopperItem.id) }
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
            item.save()
            getData()
        })
    }
}