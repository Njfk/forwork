package com.forwork.com.forwork.ui.fragment.department;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.adapter.IndexPageAdapter;
import com.forwork.com.forwork.ui.fragment.department.adapter.Index3SeesionAdapter;
import com.forwork.com.forwork.ui.fragment.department.depart.ListSelectionFragment;
import com.forwork.com.forwork.ui.fragment.department.depart.bean.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index3Depart1Fragment extends Fragment {
    @BindView(R.id.index3Depart1Viewpager)
    ViewPager index3Depart1Viewpager;
    @BindView(R.id.index3Depart1Seesion)
    RecyclerView index3Depart1Seesion;

    Index3SeesionAdapter index3SeesionAdapter;
    IndexPageAdapter indexPageAdapter;
    private String TAG="index3Depart1";

    public Index3Depart1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index3_depart1, container, false);
        ButterKnife.bind(this,inflate);
        init();
        return inflate;
    }

    private void init() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ListSelectionFragment());
        fragmentList.add(new ListSelectionFragment());
        fragmentList.add(new ListSelectionFragment());

        indexPageAdapter = new IndexPageAdapter(getChildFragmentManager(),fragmentList);
        index3Depart1Viewpager.setAdapter(indexPageAdapter);

        //Session
        Random random = new Random();
        List<Session> sessions = new ArrayList<>();
        for (int i = 0;i<10;i++){
            Session session = new Session();
            session.setIcon(R.drawable.icon_face);
            session.setNick_name("昵称"+i);
            session.setContent(" @屎壳郎  "+"这里就直接看下Glide加载时的代码，注意：\n" +
                    " @renshijie  此时的ivAvator可以使用普通的ImageView，不必再引入CircleImageView第三方框架。（当然你依然可以写成CircleImageView）\n" +
                    "asBitmap() 这句不能少，否则下面的方法会报错。这里就直接看下Glide加载时的代码，注意：\n" +
                    "此时的ivAvator可以使用普通的ImageView，不必再引入CircleImageView第三方框架。（当然你依然可以写成CircleImageView）\n" +
                    "asBitmap() 这句不能少，否则下面的方法会报错。这里就直接看下Glide加载时的代码，注意：\n" +
                    "此时的ivAvator可以使用普通的ImageView，不必再引入CircleImageView第三方框架。（当然你依然可以写成CircleImageView）\n" +
                    "asBitmap() 这句不能少，否则下面的方法会报错。这里就直接看下Glide加载时的代码，注意：\n" +
                    "此时的ivAvator可以使用普通的ImageView，不必再引入CircleImageView第三方框架。（当然你依然可以写成CircleImageView）\n" +
                    "asBitmap() 这句不能少，否则下面的方法会报错。这里就直接看下Glide加载时的代码，注意：\n" +
                    "此时的ivAvator可以使用普通的ImageView，不必再引入CircleImageView第三方框架。（当然你依然可以写成CircleImageView）\n" +
                    "asBitmap() 这句不能少，否则下面的方法会报错。");
            int i1 = random.nextInt(2) + 1;
            session.setType(i1);
            sessions.add(session);
        }

        index3SeesionAdapter = new Index3SeesionAdapter(sessions,getActivity());
        index3Depart1Seesion.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        index3Depart1Seesion.setAdapter(index3SeesionAdapter);


    }

}
