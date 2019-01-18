package com.forwork.com.forwork.ui.base;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.utils.ViewUtils;

/**
 * Created by Administrator on 2019/1/18.
 */

public abstract class LoadingPage extends FrameLayout {
    public static final int ERROR_PAGE = -1;
    public static final int LODING = 0;
    public static final int SUCCESS = 1;
    private String TAG = "loadingPage";

    @IntDef({
            ERROR_PAGE, LODING, SUCCESS
    })
    public @interface LoadingStatus {
    }

    private int status = LODING;

    private int error_res;
    private int emp_res;
    private int success_res;

    private View error_view;
    private View emp_view;
    private View success_view;

    public LoadingPage(Context context, @LayoutRes int error,@LayoutRes int emp){
        super(context);
        this.error_res = error;
        this.emp_res = emp;
        init();
    }

    private void init() {
        error_view = createErrorView();
        if (error_view!=null){
            addView(error_view,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        success_view = createSuccessView();
        if (success_view!=null){
            addView(success_view,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }


        emp_view = createLODINGView();
        if (emp_view!=null){
            addView(emp_view,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        showPage();
    }

    private View createErrorView(){
        if (error_res !=0){
            error_view = ViewUtils.inflate(error_res);
        }
        return error_view;
    }

    private View createLODINGView(){
        if (emp_res != 0){
            emp_view = ViewUtils.inflate(emp_res);
        }
        return emp_view;
    }

    public void loading(){
        Log.e(TAG, "loading: " );
        status = LODING;
        showPage();
    }

    public void complete(){
        Log.e(TAG, "complete: " );
        status = SUCCESS;
        showPage();
    }

    public void error(){
        Log.e(TAG, "error: " );
        status = ERROR_PAGE;
        showPage();
    }


    private void showPage(){
        if (error_view!=null){
            error_view.setVisibility(status == LODING||status == SUCCESS?GONE:VISIBLE);
        }
        if (emp_view !=null){
            emp_view.setVisibility(status == ERROR_PAGE||status == SUCCESS?GONE:VISIBLE);
        }
        if (success_view !=null){
            success_view.setVisibility(status == ERROR_PAGE||status == LODING?GONE:VISIBLE);
        }

    }

    public abstract View createSuccessView();

    public abstract View reload();

}
