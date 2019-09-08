package com.example.fastreding.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fastreding.R;


public class LetterAdapter extends BaseAdapter {

    private Context mContext;
    private String[] data;

    public LetterAdapter(Context context, String[] data) {
        this.mContext = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView letterTextView = new TextView(mContext);
        letterTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        letterTextView.setPadding(3, 3, 3, 3);
        letterTextView.setText(data[position]);
        letterTextView.setTextSize(30f);
        letterTextView.setTextColor(mContext.getResources().getColor(R.color.black));
        return letterTextView;
    }
}
