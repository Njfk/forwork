package com.forwork.com.forwork.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/11/2.
 */

public abstract class LazyFragment extends Fragment{
    protected boolean isVisiable = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("setUserVisibleHint", "setUserVisibleHint: "+getUserVisibleHint() );
        if (getUserVisibleHint()){
            isVisiable = true;
            onVisiable();
        }else {
            isVisiable = false;
        }
    }


    public void onVisiable(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    public abstract void refresh();
}
