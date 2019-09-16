package com.example.fastreding.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

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
        final TextView txt = new TextView(mContext);
        txt.setText(words[position]);
        txt.setTextSize(20f);
        txt.setTextColor(mContext.getResources().getColor(R.color.black));
        txt.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return txt;
    }
}
