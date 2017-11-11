package pyxis.uzuki.live.shopper.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import pyxis.uzuki.live.pyxinjector.PyxInjector;
import pyxis.uzuki.live.shopper.R;

/**
 * Shopper
 * Class: BaseDialog
 * Created by pyxis on 11/11/2017.
 * <p>
 * Description:
 */
public class BaseDialog extends Dialog {
    protected PyxInjector injector = new PyxInjector();

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.AppThemeDialog);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.AppThemeDialog);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        injector.execute(getContext(), this, findViewById(android.R.id.content));
    }

}