package com.idsbg.foxconn.myapplication.Vlayout;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.idsbg.foxconn.myapplication.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class VLayoutActivity extends Activity {
    RecyclerView recyclerView;
    MyRunnable myRunnable = new MyRunnable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vlayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置三列
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setItemCount(9);
        final List<LayoutHelper> helpers = new LinkedList<LayoutHelper>();
        helpers.add(gridLayoutHelper);
        layoutManager.setLayoutHelpers(helpers);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(1, 1, 1, 1);
            }
        });
        //设置适配器
        recyclerView.setAdapter(new VirtualLayoutAdapter(layoutManager) {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MainViewHolder(new TextView(VLayoutActivity.this));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
                holder.itemView.setLayoutParams(layoutParams);
                ((TextView) holder.itemView).setGravity(Gravity.CENTER);
                if (position == 4) {
                    holder.itemView.setBackgroundColor(Color.WHITE);
                    ((TextView) holder.itemView).setText("开始");
                } else {
                    holder.itemView.setBackgroundColor(R.drawable.my_color);
                    ((TextView) holder.itemView).setText(Integer.toString(position));
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (position == 4) {
                            myRunnable.run();
                        } else {
                            Toast.makeText(VLayoutActivity.this, "选中了" + position, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                List<LayoutHelper> helpers1 = getLayoutHelpers();
                if (helpers1 == null) {
                    return 0;
                }
                int count = 0;
                for (int i = 0; i < helpers1.size(); i++) {
                    count += helpers1.get(i).getItemCount();
                }
                return count;
            }
        });
    }

    class MyRunnable implements Runnable {
        int[] array_id = new int[]{0, 1, 2, 5, 8, 7, 6, 3};
        int startTime;
        int num = 0;
        int stopTime;

        @Override
        public void run() {
            stopTime=new Random().nextInt(1000 * 5) + 2000;
            handler.sendEmptyMessage(0);
            if (startTime >= stopTime) {
                handler.removeCallbacks(myRunnable);
                if (array_id[num] == 5) {
                    Toast.makeText(VLayoutActivity.this, array_id[num] + "中奖了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VLayoutActivity.this, array_id[num] + "未中奖", Toast.LENGTH_SHORT).show();
                }
                startTime = 0;
                return;
            }
            startTime += 100;
            handler.postDelayed(myRunnable, 100);
        }

        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Change(array_id[num]);
                num++;
                if (num >= 8) {
                    num = 0;
                }
            }
        };

        private void Change(int num) {
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                if (i == num) {
                    recyclerView.getChildAt(i).setSelected(true);
                } else if (i == 4) {
                    continue;
                } else {
                    recyclerView.getChildAt(i).setSelected(false);
                }
            }
        }
    }

}
