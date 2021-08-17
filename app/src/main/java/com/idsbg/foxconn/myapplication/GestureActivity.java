package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class GestureActivity extends Activity implements GestureDetector.OnGestureListener {
    GestureDetector detector;
    Matrix matrix;
    ImageView imageView;
    int width, height;
    //记录当前的缩放比
    float currentScale = 1;
    //初始图片资源
    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture);
        detector = new GestureDetector(this, this);
        imageView = (ImageView) findViewById(R.id.show);
        matrix = new Matrix();
        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tubiao);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.tubiao));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        velocityX=velocityX>4000?4000:velocityX;
        velocityX=velocityX<-4000?-4000:velocityX;
        //根据手势的速度计算缩放比
        currentScale+=currentScale*velocityX/4000.0f;
        //保证currentScale不等于0
        currentScale=currentScale>0.01?currentScale:0.01f;
        matrix.reset();
        //缩放matrix
        matrix.setScale(currentScale,currentScale,160,200);
        BitmapDrawable bitmapDrawable= (BitmapDrawable) imageView.getDrawable();
        //如果图片没有被回收，先回收该图片
        if(!bitmapDrawable.getBitmap().isRecycled()){
            bitmapDrawable.getBitmap().recycle();
        }
        //根据原始位图和Matrix创建新图片
        Bitmap bitmap1=Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        //展示新的位图
        imageView.setImageBitmap(bitmap1);
        return true;
    }
}
