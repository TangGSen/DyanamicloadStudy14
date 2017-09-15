package sen.com.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/9/14 16:44
 * Des    :
 */

public class SkinResouce {

    private Resources mPlugResources;
    private  String resPagekName;

    public SkinResouce(Context context, String resPath) {
        Resources originRes = context.getResources();
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addResPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addResPath.invoke(assetManager, resPath);

            mPlugResources = new Resources(assetManager, originRes.getDisplayMetrics(),
                    originRes.getConfiguration());
            resPagekName = context.getPackageManager().getPackageArchiveInfo(resPath,
                    PackageManager.GET_ACTIVITIES).applicationInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Drawable getDrawableByName(String name) {
        try {
            int resId = mPlugResources.getIdentifier(name, "drawable", resPagekName);
            Drawable drawable = mPlugResources.getDrawable(resId);
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ColorStateList getColorByName(String name) {
        try {
            int resId = mPlugResources.getIdentifier(name, "color", resPagekName);
            Log.e("sensen","resId->"+resId+"  name"+resPagekName);
            ColorStateList color = mPlugResources.getColorStateList(resId);
            return color;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
