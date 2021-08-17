package com.idsbg.foxconn.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把整张画布绘制成白色
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        int viewWidth=this.getWidth();
        //绘制圆形
        canvas.drawCircle(viewWidth/10+10,viewWidth/10+10,viewWidth/10,paint);
        //绘制正方形
        canvas.drawRect(10,viewWidth/5+20,viewWidth/5+10,viewWidth*2/5+20,paint);
        //绘制矩形
        canvas.drawRect(10,viewWidth*2/5+30,viewWidth/5+10,viewWidth/2+30,paint);
        RectF rel=new RectF(10,viewWidth/2+40,10+viewWidth/5,viewWidth*3/5+40);
    }
}
