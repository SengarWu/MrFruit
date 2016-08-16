package com.xpple.fruits.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.bean.SeedEntity;
import com.xpple.fruits.shop.adapter.SeedAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private View parentView;

    private SeedAdapter adapter;
    private List<SeedEntity> seeds;

    private RecyclerView rv_seed;
    private SwipeRefreshLayout srl_seed;

    private final int REFRESH_DELAY = 1001;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESH_DELAY:
                    initData();
                    setupView();
                    srl_seed.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_seed, container, false);
        initView();
        initData();
        setupView();
        return parentView;
    }

    private void setupView() {
        adapter = new SeedAdapter(seeds);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.openLoadMore(20,true);
        rv_seed.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SeedEntity seed = seeds.get(position);
                Intent intent = new Intent(getActivity(), SeedDetailActivity.class);
                intent.putExtra("seedId",seed.id);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        if (seeds != null)
        {
            seeds.clear();
        }
        seeds = new ArrayList<SeedEntity>();
        for (int i = 0; i < 20; i++) {
            SeedEntity seed = new SeedEntity();
            seed.seed_name = "柠檬种子";
            seed.seed_price = 0.2;
            if (i > 10)
            {
                seed.seed_state = "未解锁";
            }
            else if (i > 5)
            {
                seed.seed_state = "已解锁";
            }
            else
            {
                seed.seed_state = "免费";
            }
            seeds.add(seed);
        }
    }

    private void initView() {
        rv_seed = (RecyclerView) parentView.findViewById(R.id.rv_seed);
        rv_seed.setLayoutManager(new LinearLayoutManager(getActivity()));
        srl_seed = (SwipeRefreshLayout) parentView.findViewById(R.id.srl_seed);
        srl_seed.setColorSchemeResources(R.color.base_color_yellow,R.color.base_color_brown);
        srl_seed.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_DELAY,1000);
    }
}
