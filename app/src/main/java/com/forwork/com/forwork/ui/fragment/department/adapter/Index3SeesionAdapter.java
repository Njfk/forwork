package com.forwork.com.forwork.ui.fragment.department.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.fragment.department.depart.bean.Session;
import com.forwork.com.forwork.view.SessionContentTextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */

public class Index3SeesionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Session> sessions;
    Context context;
    LayoutInflater inflater;
    private String TAG = "Index3SeesionAdapter";

    public Index3SeesionAdapter(List<Session> sessions, Context context) {
        this.sessions = sessions;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View inflate = inflater.inflate(R.layout.item_session1, parent, false);
                return new SeesionViewHolder(inflate);
            case 2:
                View inflate2 = inflater.inflate(R.layout.item_session_charts, parent, false);
                return new SeesionChartsHodler(inflate2);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof SeesionViewHolder) {
            final SeesionViewHolder seesionViewHolder = (SeesionViewHolder) holder;
            Glide.with(context)
                    .asBitmap()
                    .load(sessions.get(position).getIcon())
                    .apply(new RequestOptions().centerInside())
                    .into(new BitmapImageViewTarget(seesionViewHolder.icon) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            seesionViewHolder.icon.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            seesionViewHolder.nick_name.setText(sessions.get(position).getNick_name());
            seesionViewHolder.content.setText(sessions.get(position).getContent());
        }
        if (holder instanceof SeesionChartsHodler) {
            SeesionChartsHodler chartsHodler = (SeesionChartsHodler) holder;
            Description description = new Description();
            description.setText("演示demo");
            chartsHodler.lineChart.setDescription(description);

            List<Entry> entries = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                entries.add(new Entry(i, (float) (Math.random() * 10 + 5)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, "时间分布");

            XAxis xAxis = chartsHodler.lineChart.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float v, AxisBase axisBase) {
                    return "第" + (int) v + "天";
                }
            });

            chartsHodler.lineChart.setData(new LineData(lineDataSet));
        }
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    @Override
    public int getItemViewType(int position) {
        return sessions.get(position).getType();
    }

    public static class SeesionViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView nick_name;
        SessionContentTextView content;

        public SeesionViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_session_icon);
            nick_name = itemView.findViewById(R.id.item_session_name);
            content = itemView.findViewById(R.id.item_session_content);
        }
    }

    public static class SeesionChartsHodler extends RecyclerView.ViewHolder {
        LineChart lineChart;

        public SeesionChartsHodler(View itemView) {
            super(itemView);
            lineChart = itemView.findViewById(R.id.item_session_lineChart);

        }
    }
}
