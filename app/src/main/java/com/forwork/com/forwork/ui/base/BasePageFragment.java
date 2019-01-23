package com.forwork.com.forwork.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/1/23.
 */

public abstract class BasePageFragment extends Fragment {
    LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        return loadingPage;
    }

    private void init() {
        final View successPage = createSuccessPage();
        loadingPage = new LoadingPage(getActivity(), getErrorPageLayout(), getEmptyPageLayout()) {
            @Override
            public View createSuccessView() {
                if (successPage != null) {
                    return successPage;
                } else {
                    return null;
                }
            }

            @Override
            public View reload() {
                BasePageFragment.this.reloadNet();
                return null;
            }
        };
        ButterKnife.bind(this, successPage);
    }


    public abstract int getErrorPageLayout();

    public abstract int getEmptyPageLayout();

    public abstract View createSuccessPage();

    public abstract View reloadNet();
}
