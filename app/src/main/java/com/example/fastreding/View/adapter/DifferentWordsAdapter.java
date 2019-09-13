package com.example.fastreding.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import com.example.fastreding.R;

public class DifferentWordsAdapter extends BaseAdapter {

    private Context mContext;
    private String[] words;

    public DifferentWordsAdapter(Context context, String[] words) {
        this.mContext = context;
        this.words = words;
    }

    @Override
    public int getCount() {
        return words.length;
    }

    @Override
    public Object getItem(int position) {
        return words[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Button btn = new Button(mContext);
        btn.setText(words[position]);
        btn.setTextSize(20f);
        btn.setTextColor(mContext.getResources().getColor(R.color.black));
        return btn;
    }
}
