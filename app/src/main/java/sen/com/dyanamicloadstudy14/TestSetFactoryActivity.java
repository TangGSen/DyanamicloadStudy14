package sen.com.dyanamicloadstudy14;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class TestSetFactoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //拦截view 的创建
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                if (name.equals("TextView")){
                    ImageView imageView = new ImageView(context,attrs);
                    for (int i=0;i<attrs.getAttributeCount();i++){
                       Log.e("sensen", attrs.getAttributeName(i)+"****"+attrs.getAttributeValue(i));
                    }
                    return imageView;
                }
                return null;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_set_factory);
    }
}
