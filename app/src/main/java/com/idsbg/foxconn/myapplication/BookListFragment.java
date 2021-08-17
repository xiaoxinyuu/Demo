package com.idsbg.foxconn.myapplication;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import android.app.ListFragment;

public class BookListFragment extends ListFragment {
    private Callbacks mCallbacks;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //为该ListFragment设置Adapter
        setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(),android.R.layout.simple_list_item_activated_1,android.R.id.text1,BookContent.Book.ITEMS));

    }
    //当该Fragment被添加、显示到Activity时，回调该方法
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //如果Activity没有实现Callbacks接口，抛出异常
        if(!(activity instanceof Callbacks)){
            throw new IllegalStateException("BookListFragment所在的Activity必须实现Callbacks接口");
        }
        //把该Activity当成Callbacks对象
        mCallbacks=(Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks=null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallbacks.onItemSelected(BookContent.Book.ITEMS.get(position).id);
    }

    public interface Callbacks{
        public void onItemSelected(Integer id);

    }
    public void setActivateOnItemClick(boolean activateOnItemClick){
        getListView().setChoiceMode(activateOnItemClick?ListView.CHOICE_MODE_SINGLE:ListView.CHOICE_MODE_NONE);
    }
}
