package com.halusky.labs.activity;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.halusky.labs.R;

public class CategoryListActivity extends SherlockListActivity {

    private ListView mListView;
    private MyTestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_screen);

        initLayoutResources();
        
        mAdapter = new MyTestAdapter(this);
        mListView.setAdapter(mAdapter);
    }

    private void initLayoutResources() {
        mListView = (ListView) findViewById(android.R.id.list);
        
    }

    private class MyTestAdapter extends BaseAdapter {

        private ArrayList<String> mData;
        private LayoutInflater mInflater;

        public MyTestAdapter(Context context) {
            super();
            mData = new ArrayList<String>();

            mData.add("Home");
            mData.add("Personal");
            mData.add("People");
            mData.add("Health");
            mData.add("Finance");

            mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                View itemContainer = mInflater.inflate(R.layout.list_category_item, parent, false);
                convertView = itemContainer;
            }
            /**
             * This bind should be made inside a container View class
             */
            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(mData.get(position));

            return convertView;
        }
        
    }
}
