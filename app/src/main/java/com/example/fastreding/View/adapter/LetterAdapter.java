package com.example.fastreding.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fastreding.R;


public class LetterAdapter extends BaseAdapter {

    private Context mContext;
    private String[] letters;

    public LetterAdapter(Context context, String[] letters) {
        this.mContext = context;
        this.letters = letters;
    }


    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        return letters[position];
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
        letterTextView.setText(letters[position]);
        letterTextView.setTextSize(30f);
        letterTextView.setTextColor(mContext.getResources().getColor(R.color.black));
        return letterTextView;
    }
}
