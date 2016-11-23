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
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.shop.adapter.SeedAdapter;
import com.xpple.fruits.shop.model.bean.SeedResult;
import com.xpple.fruits.shop.model.bean.Seeds;
import com.xpple.fruits.shop.presenter.SeedPresenter;
import com.xpple.fruits.shop.presenter.SeedPresenterImpl;
import com.xpple.fruits.shop.view.SeedView;
import com.xpple.fruits.utils.SharedPreferencesHelper;

import java.util.List;

public class SeedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,SeedView, BaseQuickAdapter.RequestLoadMoreListener {

    private View parentView;

    private SeedAdapter adapter;
    private List<Seeds> seeds;

    private RecyclerView rv_seed;
    private SwipeRefreshLayout srl_seed;
    private ProgressBar progressBar;

    private SeedPresenter presenter;

    private int userId = 0;
    private int areaId = 0;
    private int index = 1;
    private final int SIZE = 20;
    private int dataCount = 0;

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
        presenter = new SeedPresenterImpl(this);
        initData();
        return parentView;
    }

    private void setupView() {
        adapter = new SeedAdapter(seeds);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.setOnLoadMoreListener(this);
        adapter.openLoadMore(20);
        rv_seed.setAdapter(adapter);
        rv_seed.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Seeds seed = seeds.get(position);
                Intent intent = new Intent(getActivity(), SeedDetailActivity.class);
                intent.putExtra("seedId",seed.Id);
                startActivity(intent);
            }
        });
    }

    private void initData() {

        //获取本地数据
        SharedPreferencesHelper helper = new SharedPreferencesHelper(getActivity());
        userId = helper.getUserId();
        areaId = helper.getUserAreaId();
        //首次网络请求
        presenter.loadData(userId,areaId,index,SIZE);

        if (seeds != null)
        {
            seeds.clear();
        }
    }

    private void initView() {
        rv_seed = (RecyclerView) parentView.findViewById(R.id.rv_seed);
        rv_seed.setLayoutManager(new LinearLayoutManager(getActivity()));
        srl_seed = (SwipeRefreshLayout) parentView.findViewById(R.id.srl_seed);
        srl_seed.setColorSchemeResources(R.color.base_color_yellow,R.color.base_color_brown);
        srl_seed.setOnRefreshListener(this);
        progressBar = new ProgressBar(getActivity());
    }


    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_DELAY,1000);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void LoadSuccess(SeedResult result) {
        if (seeds != null)
        {
            seeds.clear();
        }
        seeds = result.getSeeds();
        dataCount = result.getDataCount();
        setupView();
    }

    @Override
    public void LoadFail(String s) {

    }

    @Override
    public void onLoadMoreRequested() {
        rv_seed.post(new Runnable() {
            @Override
            public void run() {
                if (adapter.getData().size() >= dataCount)
                {
                    adapter.loadComplete();
                }
                else
                {
                    index++;
                    presenter.loadData(userId,areaId,index,SIZE);
                    adapter.addData(seeds);
                }
            }
        });
    }
}
