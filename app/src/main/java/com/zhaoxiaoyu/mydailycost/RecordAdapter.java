package com.zhaoxiaoyu.mydailycost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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
        TextView recordAmountTypeTextView = (TextView) view.findViewById(R.id.record_amount_type_text_view);
        TextView recordPurposeTextView = (TextView) view.findViewById(R.id.record_purpose_text_view);

        String recordDateStr = new SimpleDateFormat("yyyy年MM月dd日").format(record.getDate());
        String recordAmountTypeStr = "";
        if(record.getAmountType() == Record.TYPE_INCOME){
            recordAmountTypeStr = "收入";
        }
        else if(record.getAmountType() == Record.TYPE_OUTCOME){
            recordAmountTypeStr = "支出";
        }

        recordDateTextView.setText(recordDateStr);
        recordAmountTypeTextView.setText(recordAmountTypeStr);
        recordPurposeTextView.setText(record.getPurpose());
        recordAmountTextView.setText(record.getAmount() + "元");

        return view;
    }
}
