package com.forwork.com.forwork.ui.dialog;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.forwork.com.forwork.R;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanDialogFragment extends DialogFragment {
    @BindView(R.id.scan_dialog_fragment)
    FrameLayout scan_dialog_fragment;

    CaptureFragment captureFragment;
    public static ScanDialogFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ScanDialogFragment fragment = new ScanDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = getResources().getDisplayMetrics().widthPixels;
        attributes.height = getResources().getDisplayMetrics().heightPixels;
        attributes.windowAnimations = R.style.dialog_anim_r_to_l;
        getDialog().getWindow().setAttributes(attributes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_scan_dialog, container, false);
        ButterKnife.bind(this,inflate);

        init();
        return inflate;
    }

    private void init() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                bundle.putString(CodeUtils.RESULT_STRING, result);
            }

            @Override
            public void onAnalyzeFailed() {

            }
        });
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        fragmentTransaction.replace(R.id.scan_dialog_fragment,captureFragment).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(Window.FEATURE_NO_TITLE,R.style.scan_dialog_style);
    }
}
