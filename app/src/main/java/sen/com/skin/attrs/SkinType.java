package sen.com.skin.attrs;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sen.com.skin.SkinManager;
import sen.com.skin.SkinResouce;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/9/14 16:51
 * Des    : 皮肤类型
 */

public enum SkinType {
    TEXT_COLOR("textColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResouce resouce = getSkinResc();
            ColorStateList colorStateList =resouce.getColorByName(resName);
            if (colorStateList ==null){
                return;
            }
            TextView textView = (TextView) view;
            textView.setTextColor(colorStateList);
        }
    }, BACKGROUND("background") {
        @Override
        public void skin(View view, String resName) {
//            background maybe color or drawable
            SkinResouce resouce = getSkinResc();
            Drawable drawable =resouce.getDrawableByName(resName);
            if (drawable !=null){
                view.setBackgroundDrawable(drawable);
                return;
            }

            ColorStateList colorStateList =resouce.getColorByName(resName);
            if (colorStateList !=null){
                view.setBackgroundColor(colorStateList.getDefaultColor());
            }


        }
    }, SRC("src") {
        @Override
        public void skin(View view, String resName) {
            SkinResouce resouce = getSkinResc();
            Drawable drawable =resouce.getDrawableByName(resName);
            if (drawable !=null){
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(drawable);
                return;
            }
        }
    };

    SkinResouce getSkinResc(){
        return SkinManager.getInstance().getSkinResouce();
    }
    private String mResName;

    SkinType(String resName) {
        this.mResName = resName;
    }


   

    public abstract void skin(View view, String resName);

    public String getResName() {
        return mResName;
    }
}
