package sen.com.dyanamicloadstudy14;

import android.app.Application;

import sen.com.skin.SkinManager;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/9/15 10:03
 * Des    :
 */

public class SApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
