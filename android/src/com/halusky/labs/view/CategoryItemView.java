package com.halusky.labs.view;

import com.halusky.labs.R;
import com.halusky.labs.models.Category;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryItemView extends LinearLayout {
    private TextView mTitle;
    private TextView mItemCount;
    private int mStripColor;
    private View mStrip;

    public CategoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
        mTitle = (TextView) findViewById(R.id.title);
        mItemCount = (TextView) findViewById(R.id.itemCount);
        mStrip = findViewById(R.id.colorStrip);
    }

    /**
     * Bind the views with the given data.
     */
    public void bind(Category category) {
        mTitle.setText(category.titleRes);
        mStrip.setBackgroundResource(category.colorRes);
    }
}
