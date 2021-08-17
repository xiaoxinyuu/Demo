package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MeshActivity extends Activity {
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this, R.drawable.handsome));
    }

    private class MyView extends View {
        //定义两个常量，这两个常量指定该图片纵向、横向上都被划分为20格
        private final int HEIGHT = 20;
        private final int WIDTH = 20;
        //记录该图片包含的顶点
        private final int COUNT = (HEIGHT + 1) * (WIDTH + 1);
        //定义一个数组，保存Bitmap上的21*21个点的坐标
        private final float[] verts = new float[COUNT * 2];
        //定义一个数组，记录21*21个点经过扭曲后的坐标
        private final float[] orig = new float[COUNT * 2];

        public MyView(Context context, @Nullable int attrs) {
            super(context);
            setFocusable(true);
            //根据指定资源加载图片
            bitmap = BitmapFactory.decodeResource(getResources(), attrs);
            //获取图片的宽度、高度
            float bitmapWidth = bitmap.getWidth();
            float bitmapHeight = bitmap.getHeight();
            int index = 0;
            for (int y = 0; y <= HEIGHT; y++) {
                //y坐标
                float fy = bitmapHeight * y / HEIGHT;
                for (int x = 0; x <= WIDTH; x++) {
                    //x坐标
                    float fx = bitmapWidth * x / WIDTH;
                    //两个数组均匀的保存了21*21个点的x与y坐标
                    orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                    orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                    index += 1;
                }
            }
            //设置背景色
            setBackgroundColor(Color.GRAY);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
        }

        //工具方法，用于触摸事件的位置计算verts数组各元素的值
        private void Wrap(float cx, float cy) {
            for (int i = 0; i < COUNT * 2; i += 2) {
                float dx = cx - orig[i + 0];
                float dy = cy - orig[i + 1];
                float dd = dx * dx + dy * dy;
                //计算每个坐标点与当前点的距离
                float d = (float) Math.sqrt(dd);
                //计算扭曲度
                float pull = 80000 / ((float) dd * d);
                //对verts数组(保存bitmap上21*21个点经过扭曲后的坐标)重新赋值
                if (pull >= 1) {
                    verts[i + 0] = cx;
                    verts[i + 1] = cy;
                } else {
                    //控制各顶点向触摸事件发生偏移
                    verts[i + 0] = orig[i + 0] + dx * pull;
                    verts[i + 1] = orig[i + 1] + dy * pull;
                }
            }
            //重绘
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            Wrap(event.getX(), event.getY());
            return true;
        }
    }
}
























