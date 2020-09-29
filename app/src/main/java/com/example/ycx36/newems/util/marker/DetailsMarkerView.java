package com.example.ycx36.newems.util.marker;

import android.content.Context;
import android.widget.TextView;


import com.example.ycx36.newems.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.math.BigDecimal;

public class DetailsMarkerView extends MarkerView {
    private TextView mTvMonth;
    private TextView mTvChart1;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public DetailsMarkerView(Context context) {
        super(context, R.layout.item_chart_des_marker_item_3);
        mTvMonth = findViewById(R.id.tv_chart_month);
        mTvChart1 = findViewById(R.id.tv_chart_1);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        try {
            if (e.getY() == 0) {
                mTvChart1.setText("暂无数据");
            } else {
                mTvChart1.setText(concat(e.getY(), "总电压值："));
            }
            mTvMonth.setText(String.valueOf((int) e.getX() + 1).concat("月"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        super.refreshContent(e, highlight);
    }


    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }


    public String concat(float money, String values) {
        return values + new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "V";
    }
}
