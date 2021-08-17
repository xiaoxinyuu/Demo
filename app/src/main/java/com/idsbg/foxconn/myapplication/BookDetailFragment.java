package com.idsbg.foxconn.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import android.app.Fragment;

public class BookDetailFragment extends Fragment {
    public static final String ITEM_ID = "item_id";
    //保存该fragment显示的book对象
    BookContent.Book book;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果启动该Fragment包含了ITEM_ID参数
        if (getArguments().containsKey(ITEM_ID)) {
            book = BookContent.Book.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_detail, container, false);
        if (book != null) {
            ((TextView) rootView.findViewById(R.id.book_title)).setText(book.title);
            ((TextView) rootView.findViewById(R.id.book_desc)).setText(book.desc);
        }
        return rootView;
    }
}
