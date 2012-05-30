package com.halusky.labs.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.halusky.labs.R;

public class CategoryActivity extends SherlockActivity {
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_screen);
        initLayoutResources();
    }
    
    private void initLayoutResources() {
        mGridView = (GridView) findViewById(R.id.questions_grid);
        
    }

}
