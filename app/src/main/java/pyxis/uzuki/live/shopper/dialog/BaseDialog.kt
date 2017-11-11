package pyxis.uzuki.live.shopper.dialog

import android.app.Dialog
import android.content.Context
import android.view.View

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
    constructor(context: Context) : super(context, R.style.AppThemeDialog)

    constructor(context: Context, themeResId: Int) : super(context, R.style.AppThemeDialog)

}