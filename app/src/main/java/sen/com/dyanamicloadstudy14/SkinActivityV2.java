package sen.com.dyanamicloadstudy14;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

import sen.com.skin.SkinManager;

public class SkinActivityV2 extends BaseSkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_v2_skin);
    }

    public void changeSkinPlug(View view){
        String  path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+
                "Download"+File.separator+"jsen.skin";
        int res = SkinManager.getInstance().loadSkin(path);
    }
    public void changeSkinDefult(View view){

    }
    public void jumpOther(View view){
        Intent intent = new Intent(this,SkinActivityV3.class);
        startActivity(intent);

    }
}
