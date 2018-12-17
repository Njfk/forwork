package com.forwork.com.forwork.ui.fragment.department;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.forwork.com.forwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzi.com.suzi_game.IImoocAIDL;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index2Depart2Fragment extends Fragment {
    @BindView(R.id.num1)
    EditText num1;
    @BindView(R.id.num2)
    EditText num2;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.bind)
    Button bind;

    IImoocAIDL stub;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            stub = IImoocAIDL.Stub.asInterface(service);
            if (stub == null) {
                Log.e("conn", "onServiceConnected: null");
            } else {
                Log.e("conn", "!!!!!!onServiceConnected: null");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            stub = null;
        }
    };

    public Index2Depart2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index2_depart2, container, false);
        ButterKnife.bind(this, inflate);
        init();
        return inflate;
    }

    private void init() {
      bind.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mbindService();
          }
      });
        num1.setText("1");
        num2.setText("1");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num11 = Integer.parseInt(num1.getText().toString());
                int num22 = Integer.parseInt(num2.getText().toString());

                try {
                    int res = stub.add(num11, num22);
                    text.setText(num11 + "+" + num22 + "=" + res);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void mbindService() {

        Intent intent = new Intent();
        //绑定服务端的service
        intent.setAction("suzi.com.suzi_game.service.IRomoteService");
        //新版本（5.0后）必须显式intent启动 绑定服务
//        intent.setComponent(new ComponentName("suzi.com.suzi_game.service", "suzi.com.suzi_game.service.IRemoteService"));
        //绑定的时候服务端自动创建
        getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        Log.e("---", "mbindService: " );

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unbindService(conn);
    }
}
