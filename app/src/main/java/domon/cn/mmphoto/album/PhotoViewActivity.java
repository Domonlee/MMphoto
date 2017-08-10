package domon.cn.mmphoto.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import domon.cn.mmphoto.R;

/**
 * Created by Domon on 2017/8/10.
 */

public class PhotoViewActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
    }
}
