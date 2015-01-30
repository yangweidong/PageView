package com.weidong.pageview.pageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weidong on 2015/1/27.
 */
public class PageTurnView extends View {
    private List<Bitmap> mBitmaps;
    private Paint mTextPaint;
    private int mViewWidth, mViewHeight;// 控件宽高
    private float mCurPointX;// 指尖触碰屏幕时点X的坐标值
    private static final float AUTO_AREA_LEFT = 1 / 2F, AUTO_AREA_RIGHT = 1 / 2F;// 控件左右侧自动滑动区域占比
    private float mClipX;// 裁剪右端点坐标
    private float mAutoAreaLeft, mAutoAreaRight;// 控件左侧和右侧自动吸附的区域
    public PageTurnView(Context context) {
        super(context);
    }

    public PageTurnView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageTurnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawBitmaps(canvas);
    }


    private void drawBitmaps(Canvas canvas){
        for (int i =  0; i <mBitmaps.size(); i++) {
            canvas.save();
            if(i== mBitmaps.size() -1){
                canvas.clipRect(0,0,mClipX,mViewHeight);
            }
            canvas.drawBitmap(mBitmaps.get(i),0,0,null);
            canvas.restore();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch ( event.getAction() & MotionEvent.ACTION_MASK){

            case MotionEvent.ACTION_DOWN:
                mCurPointX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mClipX = event.getX();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                judgeSlideAuto();
                break;

        }
        return true;
    }

    private void intiBitmaps() {
        List<Bitmap> temp = new ArrayList<Bitmap>();
        for (int i = mBitmaps.size() - 1; i >= 0; i--) {
            Bitmap bitmap = Bitmap.createScaledBitmap(mBitmaps.get(i), mViewWidth, mViewHeight, true);
            temp.add(bitmap);
        }
        mBitmaps = temp;
    }

    /**
     * 判断自动缩回哪里
     */
   private void judgeSlideAuto(){
       /**
        *
        */
       if(mClipX < mAutoAreaLeft){
           while (mClipX > 0){
               mClipX--;
               invalidate();
           }
       }

       if(mClipX > mAutoAreaRight){
           while (mClipX < mViewWidth){
               mClipX++;
               invalidate();
           }
       }

   }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewHeight = h;
        mViewWidth = w;
        mClipX = w;

        // 计算控件左侧和右侧自动吸附的区域
        mAutoAreaLeft = mViewWidth * AUTO_AREA_LEFT;
        mAutoAreaRight = mViewWidth * AUTO_AREA_RIGHT;


        intiBitmaps();
    }

    /**
     * 设置位图数据
     *
     * @param mBitmaps 位图列表集合
     */
    public synchronized void setBitmaps(List<Bitmap> mBitmaps) {
        /**
         * 如果数据为空则抛出异常
         */
        if (null == mBitmaps || mBitmaps.size() == 0) {
            throw new IllegalArgumentException("没有数据");
        }
        /**
         * 如果数据长度小于2则也抛异常
         */
        if (mBitmaps.size() < 2) {
            throw new IllegalArgumentException("数据太少了");
        }
        this.mBitmaps = mBitmaps;
        invalidate();
    }
}
