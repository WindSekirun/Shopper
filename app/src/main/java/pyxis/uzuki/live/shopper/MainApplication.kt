package pyxis.uzuki.live.shopper

import android.app.Application
import com.orm.SugarApp
import com.orm.SugarContext

/**
 * Shopper
 * Class: MainApplication
 * Created by pyxis on 11/11/2017.
 *
 * Description:
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SugarContext.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        SugarContext.terminate()
    }
}