package sen.com.dyanamicloadstudy14;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sen.com.skin.SkinAttrSupport;
import sen.com.skin.SkinManager;
import sen.com.skin.SkinView;
import sen.com.skin.attrs.SkinAttr;
import sen.com.skin.support.AppCompatViewInflater;

public class BaseSkinActivity extends AppCompatActivity implements LayoutInflaterFactory {
    private AppCompatViewInflater mAppCompatViewInflater;
    private String  path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+
            "Download"+File.separator+"jsen.skin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), this );
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        //拦截view 的创建
        View view = createView(parent, name, context, attrs);
        if(view !=null){
            List<SkinAttr> skinAttrList = SkinAttrSupport.getSkinAttrs(context,attrs);
            SkinView skinView = new SkinView(view,skinAttrList);

            int res = SkinManager.getInstance().loadSkinSingle(path,skinView);
            //统一管理
            managerSkinView(skinView);
        }
        return view;
    }

    private void managerSkinView(SkinView skinView) {
        List<SkinView> skinViewList = SkinManager.getInstance().getSkinViewList(this);
        //第一次是为空的
        if(skinViewList ==null){
            skinViewList = new ArrayList<>();
            SkinManager.getInstance().register(this,skinViewList);
        }
        skinViewList.add(skinView);
    }

    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < 21;
        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new AppCompatViewInflater();
        }

        boolean inheritContext = false;
        if (IS_PRE_LOLLIPOP) {
            inheritContext = (attrs instanceof XmlPullParser)
                    // If we have a XmlPullParser, we can detect where we are in the layout
                    ? ((XmlPullParser) attrs).getDepth() > 1
                    // Otherwise we have to use the old heuristic
                    : shouldInheritContext((ViewParent) parent);
        }

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                IS_PRE_LOLLIPOP, /* Only read android:theme pre-L (L+ handles this anyway) */
                true, /* Read read app:theme as a fallback at all times for legacy reasons */
                VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if enabled */
        );
    }


    /**
     * 来源于系统源码
     *
     * @param parent
     * @return
     */
    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }


}
