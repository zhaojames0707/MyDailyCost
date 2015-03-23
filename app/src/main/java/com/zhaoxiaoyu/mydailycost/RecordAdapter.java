package com.zhaoxiaoyu.mydailycost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhaoxiaoyu on 15/3/22.
 */
public class RecordAdapter extends ArrayAdapter<Record> {
    private int resourceId;
    public RecordAdapter(Context context, int textViewResourceId, List<Record> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Record record = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView recordDateTextView = (TextView) view.findViewById(R.id.record_date_text_view);
        TextView recordAmountTextView = (TextView) view.findViewById(R.id.record_amount_text_view);
        recordDateTextView.setText(record.getDate().toString());
        recordAmountTextView.setText(record.getAmount() + "元");
        return view;
    }
}
