package sen.com.skin.attrs;

import android.view.View;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/9/14 16:46
 * Des    : 一个view 需要更换的属性（单个或多个）
 */

public class SkinAttr {
    private String skinName;
    private SkinType skinType;

    public SkinAttr(String skinName, SkinType skinType) {
        this.skinName = skinName;
        this.skinType = skinType;
    }

    public void skin(View view) {
        skinType.skin(view,skinName);
    }
}
