package com.xpple.fruits.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.bean.DiscoverEntity;
import com.xpple.fruits.main.adapter.DiscoverAdapter;

import java.util.ArrayList;
import java.util.List;

public class DiscoverActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private SwipeRefreshLayout srl_discover;
    private RecyclerView rv_discover;

    private List<DiscoverEntity> discovers;
    private DiscoverAdapter adapter;

    private final int REFRESS_DELAY = 1001;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESS_DELAY:
                    initData();
                    setupView();
                    srl_discover.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        initView();
        initData();
        setupView();
    }

    private void setupView() {
        adapter = new DiscoverAdapter(discovers);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.openLoadMore(20,true);
        rv_discover.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DiscoverEntity discover = discovers.get(position);
                Intent intent = new Intent(DiscoverActivity.this,DiscoverDetailActivity.class);
                intent.putExtra("link",discover.link);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        if (discovers != null)
        {
            discovers.clear();
        }
        discovers = new ArrayList<DiscoverEntity>();
        for (int i = 0; i < 10; i++) {
            DiscoverEntity discover = new DiscoverEntity();
            discover.title = "【甜哦】攀枝花吉禄芒果5斤 新鲜水果紫芒果 24元 ";
            discover.link = "http://mp.weixin.qq.com/s?__biz=MzIzNjQwNTI1OA==&mid=2247483707&idx=1&sn=59cab13b1d00290912c86e9ac47f93f1&scene=0#wechat_redirect";
            discovers.add(discover);
        }
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = $(R.id.tv_title);
        tv_title.setText("发现");
        srl_discover = $(R.id.srl_discover);
        srl_discover.setColorSchemeResources(R.color.base_color_yellow,R.color.base_color_brown);
        srl_discover.setOnRefreshListener(this);
        rv_discover = $(R.id.rv_discover);
        rv_discover.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESS_DELAY,1000);
    }
}
