package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Fragment;

public class Show extends Activity implements BookListFragment.Callbacks{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookshow);
    }

    @Override
    public void onItemSelected(Integer id) {
        //创建Bundle 向fragment传递参数
        Bundle arguments=new Bundle();
        arguments.putInt(BookDetailFragment.ITEM_ID,id);
        Fragment fragment=new BookDetailFragment();
        //向fragment传入参数
        fragment.setArguments(arguments);
        //使用fragment替换book_detail_container容器当前显示的Fragment
        getFragmentManager().beginTransaction().replace(R.id.book_detail_container,fragment).commit();

    }
}
