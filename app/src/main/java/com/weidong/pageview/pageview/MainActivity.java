package com.weidong.pageview.pageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PageTurnView mPageCurlView = (PageTurnView) findViewById(R.id.ptv);

        Bitmap bitmap = null;
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a1);
        bitmaps.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a2);
        bitmaps.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a3);
        bitmaps.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a4);
        bitmaps.add(bitmap);
        mPageCurlView.setBitmaps(bitmaps);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
