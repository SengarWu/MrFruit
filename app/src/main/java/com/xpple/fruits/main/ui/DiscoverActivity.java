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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.main.adapter.DiscoverAdapter;
import com.xpple.fruits.main.model.bean.Article;
import com.xpple.fruits.main.model.bean.ArticleResult;
import com.xpple.fruits.main.presenter.DiscoverPresenter;
import com.xpple.fruits.main.presenter.DiscoverPresenterImpl;
import com.xpple.fruits.main.view.DiscoverView;

import java.util.List;

public class DiscoverActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, DiscoverView, BaseQuickAdapter.RequestLoadMoreListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private SwipeRefreshLayout srl_discover;
    private RecyclerView rv_discover;

    private List<Article> articles;
    private DiscoverAdapter adapter;

    private DiscoverPresenter presenter;
    private ProgressBar progressBar;

    private int index = 1;
    private final int SIZE = 20;
    private int dataCount = 0;

    private final int REFRESS_DELAY = 1001;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESS_DELAY:
                    presenter.getList(index,SIZE);
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
        presenter = new DiscoverPresenterImpl(this);
        presenter.getList(index,SIZE);
    }

    private void setupView() {
        adapter = new DiscoverAdapter(articles);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.setOnLoadMoreListener(this);
        adapter.openLoadMore(20);
        rv_discover.setAdapter(adapter);
        rv_discover.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Article article = (Article) baseQuickAdapter.getData().get(position);
                Intent intent = new Intent(DiscoverActivity.this, DiscoverDetailActivity.class);
                intent.putExtra("link", article.getLink());
                startActivity(intent);
            }
        });
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
        progressBar = new ProgressBar(this);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESS_DELAY,1000);
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
    public void getListSuccess(ArticleResult result) {
        if(index == 0 || index == 1)
        {
            //首次请求
            articles = result.getArticles();
            dataCount = result.getDataCount();
            setupView();
        }
        else
        {
            adapter.addData(result.getArticles());
        }
    }

    @Override
    public void getListFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_discover.post(new Runnable() {
            @Override
            public void run() {
                if (adapter.getData().size() >= dataCount)
                {
                    //加载完毕
                    adapter.loadComplete();
                }
                else
                {
                    index++;
                    presenter.getList(index,SIZE);
                }
            }
        });
    }
}
