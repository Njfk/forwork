package com.forwork.com.forwork.ui.dialog.popwindow;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.forwork.com.forwork.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/11/19.
 */

public class ScreenPop extends PopupWindow {
    LayoutInflater inflater;
    Activity activity;
    TagFlowLayout poptag;
    Button confirm;
    Button cancel;
    private String TAG= "ScreenPop";

    public ScreenPop(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        initView();
    }

    public void initView() {
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.scale_anim);
//        setOutsideTouchable(true);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                lighton();
            }
        });


        View inflate = inflater.inflate(R.layout.pop_screen, null);
        poptag = inflate.findViewById(R.id.poptag);
        confirm = inflate.findViewById(R.id.item_screen_pop_confirm);
        cancel=inflate.findViewById(R.id.item_screen_pop_cancle);

        List<String> mVals = new ArrayList<>();
        mVals.add("皮革");
        mVals.add("人造皮革");
        mVals.add("毛绒");
        mVals.add("毛绒");
        mVals.add("皮革");
        mVals.add("人造皮革");
        mVals.add("毛绒");
        mVals.add("毛绒");
        mVals.add("皮革");
        mVals.add("人造皮革");
        mVals.add("毛绒");
        mVals.add("毛绒");
        TagAdapter tagAdapter = new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) inflater.inflate(R.layout.tag,
                        poptag, false);
                tv.setText(s);
                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
            }
        };
        poptag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                return false;
            }
        });
        poptag.setAdapter(tagAdapter);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Integer> selectedList = poptag.getSelectedList();
                Iterator<Integer> iterator = selectedList.iterator();
                while (iterator.hasNext()){
                    Log.e(TAG, "initView: "+iterator.next() );
                }
                if (isShowing()){
                    dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()){
                    dismiss();
                }
            }
        });

        setContentView(inflate);
    }

    private void lighton() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1.0f;
        activity.getWindow().setAttributes(lp);
    }

    private void lightoff() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.3f;
        activity.getWindow().setAttributes(lp);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
//        lightoff();
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
//        lightoff();
        super.showAtLocation(parent, gravity, x, y);
    }
}
