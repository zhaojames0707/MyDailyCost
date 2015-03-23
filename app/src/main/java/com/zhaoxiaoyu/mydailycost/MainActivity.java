package com.zhaoxiaoyu.mydailycost;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaoxiaoyu on 15/3/19.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private EditText moneyAmount;
    private EditText moneyPurpose;
    private EditText moneyRemark;
    private Button addOutcome;
    private Button addIncome;
    private ListView recordsListView;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addOutcome = (Button) findViewById(R.id.add_outcome);
        addIncome = (Button) findViewById(R.id.add_income);
        moneyAmount = (EditText) findViewById(R.id.money_amount);
        moneyPurpose = (EditText) findViewById(R.id.money_purpose);
        moneyRemark = (EditText) findViewById(R.id.money_remark);
        recordsListView = (ListView) findViewById(R.id.records_list_view);
        addOutcome.setOnClickListener(this);
        addIncome.setOnClickListener(this);

        MoneyDAO moneyDAO = new MoneyDAO(this);
        List<Record> recordList = moneyDAO.getAllRecords();
        Record record = new Record();
        RecordAdapter recordAdapter = new RecordAdapter(this, R.layout.record_item, recordList);
        recordsListView.setAdapter(recordAdapter);
    }

    @Override
    public void onClick(View v) {
        Record record = null;
        MoneyDAO moneyDAO = null;
        switch (v.getId()){
            case R.id.add_outcome:
                record = analyzeInputRecord(Record.TYPE_OUTCOME);
                moneyDAO = new MoneyDAO(MainActivity.this);
                moneyDAO.addOneRecord(record);
                break;
            case R.id.add_income:
                record = analyzeInputRecord(Record.TYPE_INCOME);
                moneyDAO = new MoneyDAO(MainActivity.this);
                moneyDAO.addOneRecord(record);
                break;
            default:
                break;
        }
    }

    /**
     * 分析输入的账目信息
     * @param amountType income/outcome
     * @return 账目记录的对象
     */
    private Record analyzeInputRecord(int amountType){
        Record record = new Record();
        record.setAmount(Double.parseDouble(moneyAmount.getText().toString()));
        record.setPurpose(moneyPurpose.getText().toString());
        record.setRemark(moneyRemark.getText().toString());
        record.setAmount(amountType);
        record.setDate(new Date());
        return record;
    }
}
