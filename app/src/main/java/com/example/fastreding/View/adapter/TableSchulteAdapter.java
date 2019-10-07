package com.example.fastreding.View.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fastreding.R;

import java.util.List;

public class TableSchulteAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> numbers;
    private int widthDisplay;

    public TableSchulteAdapter(Context context, List<String> numbers, int widthDisplay) {
        this.mContext = context;
        this.numbers = numbers;
        this.widthDisplay = widthDisplay;
    }

    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int position) {
        return numbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView txt = new TextView(mContext);
        txt.setText(numbers.get(position));
        txt.setTextSize(20f);
        txt.setTextColor(mContext.getResources().getColor(R.color.black));
        txt.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txt.setHeight(widthDisplay / 5 - 25);
        txt.setGravity(Gravity.CENTER);
        return txt;
    }
}

