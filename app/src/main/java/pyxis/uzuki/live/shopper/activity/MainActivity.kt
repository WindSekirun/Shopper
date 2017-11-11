package pyxis.uzuki.live.shopper.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pyxis.uzuki.live.shopper.R
import pyxis.uzuki.live.shopper.fragment.AddFragment
import pyxis.uzuki.live.shopper.fragment.AddedFragment


/**
 * Shopper
 * Class: MainActivity
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AddFragment(), getString(R.string.main_tab_1))
        adapter.addFragment(AddedFragment(), getString(R.string.main_tab_2))
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = arrayListOf<Fragment>()
        private val mFragmentTitleList = arrayListOf<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList.get(position)
        }
    }
}
