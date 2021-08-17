package com.idsbg.foxconn.myapplication;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ExpandableListActivityTest extends ExpandableListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            int[] logos = new int[]{
                    R.drawable.shizi,
                    R.drawable.xiaolu,
                    R.drawable.dog
            };
            private String[] armTypes = new String[]{
                    "狮子", "小鹿", "小狗"
            };
            private String[][] arms = new String[][]{
                    {"非洲狮", "东北虎", "亚洲狮"}, {"梅花鹿", "长颈鹿", "驯鹿"}, {"二哈", "萨摩耶", "金毛"}
            };

            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            //此方法决定每个组选项的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableListActivityTest.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(ExpandableListActivityTest.this);
                logo.setImageResource(logos[groupPosition]);
                ll.addView(logo);
                TextView textView = new TextView(ExpandableListActivityTest.this);
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView=new TextView(ExpandableListActivityTest.this);
                textView.setText(getChild(groupPosition,childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        //设置该窗口显示列表
        setListAdapter(adapter);
    }
}
