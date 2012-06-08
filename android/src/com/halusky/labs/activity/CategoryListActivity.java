package com.halusky.labs.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.halusky.labs.R;
import com.halusky.labs.models.Category;
import com.halusky.labs.view.CategoryItemView;

public class CategoryListActivity extends SherlockListActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_screen);

        initLayoutResources();
        
        Category data[] = new Category[] {
                new Category(R.drawable.categ_home, R.string.category_home, R.color.color_mighty_slate, R.id.category_home),
                new Category(R.drawable.categ_people, R.string.category_documents, R.color.color_pacifica, R.id.category_documents),
                new Category(R.drawable.categ_personal, R.string.category_personal, R.color.color_apple_chic, R.id.category_personal),
                new Category(R.drawable.categ_others, R.string.category_health, R.color.color_cherry_pink, R.id.category_health)
        };

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, R.layout.list_color_category_item, data);
        mListView.setAdapter(categoryAdapter);
    }

    private void initLayoutResources() {
        mListView = (ListView) findViewById(android.R.id.list);
        
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Category category = (Category) getListAdapter().getItem(position);
        switch (category.id) {
        case R.id.category_home:
        case R.id.category_documents:
        case R.id.category_personal:
        case R.id.category_health:
            break;
        default:
            break;
        }
    }

    /**
     * List adapter for binding a Category array to a CategoryItemView
     */
    private class CategoryAdapter extends ArrayAdapter<Category> {

        private Context mContext;
        private Category mData[] = null;
        private LayoutInflater mLayoutInflater;
        private int mLayoudId;

        public CategoryAdapter(Context context, int layoutId, Category[] data) {
            super(context, layoutId, data);
            mContext = context;
            mLayoudId = layoutId;
            mData = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CategoryItemView row = (CategoryItemView) convertView;

            if (row == null) {
                row = (CategoryItemView) mLayoutInflater.inflate(mLayoudId, parent, false);
            } 

            row.bind(mData[position]);
            return row;
        }
    }
}
