package com.forwork.com.forwork.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.LoadingPage;
import com.forwork.com.forwork.utils.ViewUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index4Fragment extends Fragment {
    LoadingPage loadingPage;
    @BindView(R.id.tv)
    TextView tv;
    public Index4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View[] successView = {null};
        loadingPage = new LoadingPage(getContext(),R.layout.error_view,R.layout.empty_view) {
            @Override
            public View createSuccessView() {
                 successView[0] = Index4Fragment.this.createSuccessView();
                return successView[0];
            }

            @Override
            public View reload() {
                return null;
            }
        };

        init();
        ButterKnife.bind(this,successView[0]);

        return loadingPage;
    }
    Timer timer;
    private void init() {
        final int[] i = {-1};
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ViewUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("--", "run: "+i[0]  );
                        if (i[0] ==-1){
                            loadingPage.error();
                        }else if (i[0] == 0){
                            loadingPage.loading();
                        }else if (i[0] == 1){
                            loadingPage.complete();
                        }else {
                            tv.setText(i[0]+"");
                        }
                        i[0] = i[0]+1;
                    }
                });

            }
        },0,2000);

    }

    private View createSuccessView() {
        return View.inflate(getContext(),R.layout.fragment_index4,null);
    }


    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();

    }
}
