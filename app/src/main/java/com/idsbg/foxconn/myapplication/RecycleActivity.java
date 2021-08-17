package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Arrays;
import java.util.List;


public class RecycleActivity extends AppCompatActivity {

    private static final Integer[] CATS = new Integer[]{
            R.drawable.dog,
            R.drawable.dog1,
            R.drawable.doublep,
            R.drawable.handsome,
            R.drawable.shizi,
            R.drawable.superhero,
            R.drawable.tubiao,
            R.drawable.xiaolu,
            R.drawable.n,
            R.drawable.q,
            R.drawable.w,
            R.drawable.e,
            R.drawable.r,
            R.drawable.t,
            R.drawable.y,
            R.drawable.u,
            R.drawable.x,
            R.drawable.v,
            R.drawable.a,
    };
    private RecyclerView rvone;

    List<Integer> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvone);
        this.rvone = (RecyclerView) findViewById(R.id.rv_one);


        //new LinearLayoutManager()
        //new GridLayoutManager()

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        rvone.setLayoutManager(staggeredGridLayoutManager);

        data = Arrays.asList(CATS);

        rvone.setAdapter(new MyAdapter());

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecycleActivity.this).inflate(R.layout.item_rvone, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.ivIcon.setImageResource(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivIcon;

            public ViewHolder(View itemView) {
                super(itemView);
                //ivIcon = itemView.findViewById(R.id.iv_item_icon);

                ivIcon= (ImageView) itemView.findViewById(R.id.iv_item_icon);
                int width = ((Activity) ivIcon.getContext()).getWindowManager().getDefaultDisplay().getWidth();
                ViewGroup.LayoutParams params = ivIcon.getLayoutParams();
                //设置图片的相对于屏幕的宽高比
                params.width = width/2;
                params.height =  (int) (400 + Math.random() * 400) ;
                ivIcon.setLayoutParams(params);

            }
        }
    }
}

