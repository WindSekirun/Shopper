package pyxis.uzuki.live.shopper.dialog

import android.app.Dialog
import android.content.Context
import android.view.View

import pyxis.uzuki.live.pyxinjector.PyxInjector
import pyxis.uzuki.live.shopper.R

/**
 * Shopper
 * Class: BaseDialog
 * Created by pyxis on 11/11/2017.
 *
 *
 * Description:
 */
open class BaseDialog : Dialog {
    protected var injector = PyxInjector()

    constructor(context: Context) : super(context, R.style.AppThemeDialog) {}

    constructor(context: Context, themeResId: Int) : super(context, R.style.AppThemeDialog) {}

    override fun onContentChanged() {
        super.onContentChanged()
        injector.execute(context, this, findViewById<View>(android.R.id.content))
    }

}