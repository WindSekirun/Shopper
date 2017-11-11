package pyxis.uzuki.live.shopper

import android.app.Application

import io.realm.Realm

class ShopperApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}