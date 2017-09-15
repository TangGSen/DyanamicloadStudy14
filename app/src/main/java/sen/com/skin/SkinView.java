package sen.com.skin;

import android.view.View;

import java.util.List;

import sen.com.skin.attrs.SkinAttr;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/9/14 16:49
 * Des    :
 */

public class SkinView {
    private View view;
    private List<SkinAttr> mAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrList) {
        this.view = view;
        this.mAttrs = skinAttrList;
    }

    public void skin() {
        if (view != null) {
            for (SkinAttr attr : mAttrs) {
                attr.skin(view);
            }
        }
    }
}
