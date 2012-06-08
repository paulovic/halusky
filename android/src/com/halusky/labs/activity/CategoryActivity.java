package com.halusky.labs.activity;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.halusky.labs.R;

public class CategoryActivity extends SherlockActivity {
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_screen);
        initLayoutResources();
        
        mGridView.setAdapter(new MyTestAdapter(this));
    }
    
    private void initLayoutResources() {
        mGridView = (GridView) findViewById(R.id.questions_grid);
        
    }

    private class MyTestAdapter extends BaseAdapter {

        private ArrayList<String> mData;
        private LayoutInflater mInflater;

        public MyTestAdapter(Context context) {
            super();
            mData = new ArrayList<String>();

            mData.add("Your CAR!");
            mData.add("Girlfriend.");
            mData.add("Documents.");
            mData.add("Tst");
            mData.add("Test test");

            mInflater = LayoutInflater.from(context);
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
                View itemContainer = mInflater.inflate(R.layout.category_grid_item, parent, false);
                convertView = itemContainer;
            }

            /**
             * This bind should be made inside a container View class
             */
            TextView question = (TextView) convertView.findViewById(R.id.question);
            question.setText(mData.get(position));

            return convertView;
        }
        
    }    
}
