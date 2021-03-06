package com.weibin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.weibin.adapter.CommonAdapter;
import com.weibin.adapter.CommonViewHolder;
import com.weibin.dylanstepcount.R;
import com.weibin.step.bean.StepData;
import com.weibin.step.utils.DbUtils;

import java.util.List;
import com.orhanobut.logger.Logger;

/**
 * Created by wei.bin on 2017/8/28.
 */
public class HistoryActivity extends AppCompatActivity{
    private LinearLayout layout_titlebar;
    private ImageView iv_left;
    private ImageView iv_right;
    private ListView lv;

    private void assignViews() {
        layout_titlebar = (LinearLayout) findViewById(R.id.layout_titlebar);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_history);
        assignViews();
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData(){
        setEmptyView(lv);
        if (DbUtils.getLiteOrm() == null){
            DbUtils.createDB(this, "Fighting");
        }
        List<StepData> stepDatas = DbUtils.getQueryAll(StepData.class);
        Logger.d("stepDatas = " + stepDatas);
        lv.setAdapter(new CommonAdapter<StepData>(this, stepDatas, R.layout.item) {
            @Override
            protected void convertView(View item, StepData stepData) {
                TextView tv_data = CommonViewHolder.get(item, R.id.tv_data);
                TextView tv_step = CommonViewHolder.get(item, R.id.tv_step);
                tv_data.setText(stepData.getToday());
                tv_step.setText(stepData.getStep() + "步");
            }
        });
    }

    protected <T extends View> T setEmptyView(ListView listView) {
        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyView.setText("暂无数据！");
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup) listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
        return (T) emptyView;
    }
}






























