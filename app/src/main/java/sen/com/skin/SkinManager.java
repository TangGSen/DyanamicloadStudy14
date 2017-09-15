package sen.com.skin;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/9/14 16:44
 * Des    :
 */

public class SkinManager {
    private static volatile SkinManager mInstance;
    private Context mContext;
    private SkinResouce skinResouce;
    private Map<Activity, List<SkinView>> mSkinViews = new HashMap<>();

    private SkinManager() {

    }

    public static SkinManager getInstance() {
        if (mInstance == null) {
            synchronized (SkinManager.class) {
                if (mInstance == null) {
                    mInstance = new SkinManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public int loadSkin(String path) {
        skinResouce = new SkinResouce(mContext.getApplicationContext(), path);
        for (Map.Entry<Activity, List<SkinView>> entry : mSkinViews.entrySet()) {
            List<SkinView> skinViewList = entry.getValue();
            for (SkinView skinView : skinViewList) {
                skinView.skin();
            }
        }
        return -1;
    }

    public int loadSkinSingle(String path, SkinView skinView) {
        skinResouce = new SkinResouce(mContext.getApplicationContext(), path);

        skinView.skin();
        return -1;
    }

    public List<SkinView> getSkinViewList(Activity activity) {
        return mSkinViews.get(activity);
    }

    /**
     * 添加一个Activity 中的 list
     *
     * @param activity
     * @param skinViewList
     */
    public void register(Activity activity, List<SkinView> skinViewList) {
        mSkinViews.put(activity, skinViewList);
    }

    /**
     * 获取资源管理
     *
     * @return
     */
    public SkinResouce getSkinResouce() {
        return skinResouce;
    }
}
