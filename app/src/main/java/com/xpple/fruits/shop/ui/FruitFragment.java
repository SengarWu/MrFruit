package com.xpple.fruits.shop.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.main.model.bean.Fruit;
import com.xpple.fruits.shop.adapter.FruitAdapter;
import com.xpple.fruits.shop.model.bean.FruitResult;
import com.xpple.fruits.shop.presenter.FruitPresenter;
import com.xpple.fruits.shop.presenter.FruitPresenterImpl;
import com.xpple.fruits.shop.view.FruitView;
import com.xpple.fruits.utils.SharedPreferencesHelper;

import java.text.DecimalFormat;
import java.util.List;

public class FruitFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, FruitView, BaseQuickAdapter.RequestLoadMoreListener {

    private View parentView;

    private DecimalFormat df   = new DecimalFormat("######0.0");

    private FruitAdapter adapter;
    private List<Fruit> fruits;

    private RecyclerView rv_shop;
    private ImageButton ib_shop_cart;
    private TextView tv_shop_message;
    private TextView tv_shop_total;
    private Button btn_shop_settle;
    private SwipeRefreshLayout srl_fruit;
    private ProgressBar progressBar;
    FruitPresenter presenter;

    private int userId = 0;
    private int areaId = 0;
    private int index = 1;
    private final int SIZE = 20;
    private Double cost = 0.0;
    private int dataCount = 0;

    private final int REFRESH_DELAY = 1001;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESH_DELAY:
                    initData();
                    srl_fruit.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_fruit, container, false);
        initView();
        presenter = new FruitPresenterImpl(this);
        initData();
        return parentView;
    }

    private void initView() {
        rv_shop = (RecyclerView) parentView.findViewById(R.id.rv_shop);
        rv_shop.setLayoutManager(new LinearLayoutManager(getActivity()));
        ib_shop_cart = (ImageButton) parentView.findViewById(R.id.ib_shop_cart);
        ib_shop_cart.setOnClickListener(this);
        tv_shop_message = (TextView) parentView.findViewById(R.id.tv_shop_message);
        tv_shop_total = (TextView) parentView.findViewById(R.id.tv_shop_total);
        btn_shop_settle = (Button) parentView.findViewById(R.id.btn_shop_settle);
        btn_shop_settle.setOnClickListener(this);
        srl_fruit = (SwipeRefreshLayout) parentView.findViewById(R.id.srl_fruit);
        srl_fruit.setColorSchemeResources(R.color.base_color_yellow,R.color.base_color_brown);
        srl_fruit.setOnRefreshListener(this);
        progressBar = new ProgressBar(getActivity());
    }

    private void setupView() {
        adapter = new FruitAdapter(fruits);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.setOnLoadMoreListener(this);
        adapter.openLoadMore(20);
        rv_shop.setAdapter(adapter);
        rv_shop.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Fruit fruit = (Fruit) baseQuickAdapter.getItem(position);
                switch (view.getId())
                {
                    case R.id.ib_fruit_sub:
                        if (fruit.num < 2)
                        {
                            fruit.setVisible(false);
                        }
                        else
                        {
                            fruit.num--;
                        }
                        break;
                    case R.id.ib_fruit_add:
                        if (fruit.num >= 0)
                        {
                            fruit.visible = true;
                            fruit.num++;
                        }
                        break;
                    default:
                        break;
                }
                adapter.notifyDataSetChanged();
            }
        });

        if (cost == 0)
        {
            tv_shop_message.setVisibility(View.VISIBLE);
            tv_shop_total.setVisibility(View.GONE);
        }
        else
        {
            tv_shop_message.setVisibility(View.GONE);
            tv_shop_total.setVisibility(View.VISIBLE);
        }


    }

    private void initData() {
        //获取本地数据
        SharedPreferencesHelper helper = new SharedPreferencesHelper(getActivity());
        userId = helper.getUserId();
        areaId = helper.getUserAreaId();
        //首次网络请求
        presenter.loadData(userId,areaId,index,SIZE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ib_shop_cart:
                //弹出购物车清单

                break;
            case R.id.btn_shop_settle:
                //结算

                break;
        }
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_DELAY,500);
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
    public void LoadSuccess(FruitResult result) {
        if (index == 0 || index == 1)
        {
            //首次请求
            fruits = result.getFruits();
            cost = result.getCost();
            dataCount = result.getDataCount();
            setupView();
        }
        else
        {
            adapter.addData(result.getFruits());
        }
    }

    @Override
    public void LoadFail(String s) {
        JUtils.Toast("数据加载失败"+s);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_shop.post(new Runnable() {
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
                    presenter.loadData(userId,areaId,index,SIZE);
                }
            }
        });
    }
}
