package sen.com.skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sen.com.skin.attrs.SkinAttr;
import sen.com.skin.attrs.SkinType;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/9/14 16:48
 * Des    : 皮肤属性解析的支持类
 */

public class SkinAttrSupport {
    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {
        List<SkinAttr> attrList = new ArrayList<>();

        int size = attrs.getAttributeCount();
        for (int i = 0; i < size; i++) {
            //获取名称
            String name = attrs.getAttributeName(i);
            String value = attrs.getAttributeValue(i);

            Log.e("sensen", "SkinAttrSupport->name: " + name + "   value: " + value);
            SkinType skinType = getSkinType(name);
            if (skinType != null) {
                //资源名称，目前只获取了@int 的数据，需要处理
                String resName = getResName(context,value);
                Log.e("sensen", "SkinAttrSupport->resName: " + resName );
                if (TextUtils.isEmpty(resName))
                    continue;

                SkinAttr skinAttr = new SkinAttr(resName, skinType);
                attrList.add(skinAttr);
            }

        }
        return attrList;
    }

    private static String getResName(Context context, String attrValue) {
         if(attrValue.startsWith("@")){
             attrValue = attrValue.substring(1);
            return context.getResources().getResourceEntryName(Integer.parseInt(attrValue));
         }
        return null;
    }

    private static SkinType getSkinType(String name) {
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if (skinType.getResName().equals(name)) {
                return skinType;
            }
        }
        return null;
    }
}
