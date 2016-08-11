package com.xpple.fruits.cart.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.bean.CartEntity;
import com.xpple.fruits.cart.adapter.CartAdapter;
import com.xpple.fruits.me.ui.AccountActivity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View parentView;
    private RecyclerView rv_cart;
    private CheckBox cb_cart_check;
    private TextView tv_cart_total;
    private Button btn_del;
    private Button btn_settle;
    private SwipeRefreshLayout srl_cart;

    private List<CartEntity> carts ;
    //保存选中的项
    private List<CartEntity> selectCarts = new ArrayList<>();

    private CartAdapter adapter;

    private DecimalFormat df   = new DecimalFormat("######0.00");

    private final int REFRESH_DELAY = 1001;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESH_DELAY:
                    initData();
                    setupView();
                    srl_cart.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_cart, container, false);
        initView();
        initData();
        setupView();
        return parentView;
    }

    private void setupView() {
        adapter = new CartAdapter(carts);
        //设置动画效果
        adapter.openLoadAnimation();
        adapter.openLoadMore(false);
        rv_cart.setAdapter(adapter);
        //回调子控件点击事件
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                //获取点击的项
                CartEntity cart = (CartEntity) baseQuickAdapter.getItem(position);
                switch (view.getId())
                {
                    case R.id.ib_cart_add://数量增加按钮
                        if (cart.number < 99)
                        {
                            cart.number +=1;
                        }
                        cart.sum = cart.fruit_price * cart.number;
                        break;
                    case R.id.ib_cart_sub://数量减少按钮
                        if (cart.number > 1)
                        {
                            cart.number -= 1;
                        }
                        cart.sum = cart.fruit_price * cart.number;
                        break;
                    case R.id.cb_cart_check://选择按钮事件
                        if (cart.ischeck)
                        {
                            cart.ischeck = false;
                        }
                        else
                        {
                            cart.ischeck = true;
                        }
                        //全选状态检查
                        if (allChecked(carts))
                        {
                            cb_cart_check.setChecked(true);
                        }
                        else
                        {
                            cb_cart_check.setChecked(false);
                        }
                        break;
                }
                adapter.notifyDataSetChanged();
                tv_cart_total.setText(getTotal(carts));
            }
        });
    }

    private void initView() {
        srl_cart = (SwipeRefreshLayout) parentView.findViewById(R.id.srl_cart);
        srl_cart.setColorSchemeResources(R.color.base_color_yellow,R.color.base_color_brown);
        srl_cart.setOnRefreshListener(this);
        rv_cart = (RecyclerView) parentView.findViewById(R.id.rv_cart);
        rv_cart.setLayoutManager(new LinearLayoutManager(getActivity()));
        cb_cart_check = (CheckBox) parentView.findViewById(R.id.cb_cart_check);
        cb_cart_check.setOnClickListener(this);
        //初始化全选。
        cb_cart_check.setChecked(true);
        tv_cart_total = (TextView) parentView.findViewById(R.id.tv_cart_total);
        btn_del = (Button) parentView.findViewById(R.id.btn_del);
        btn_del.setOnClickListener(this);
        btn_settle = (Button) parentView.findViewById(R.id.btn_settle);
        btn_settle.setOnClickListener(this);
    }

    /**
     * 全选状态检查
     * @param carts
     * @return
     */
    private boolean allChecked(List<CartEntity> carts) {
        for (int i = 0; i < carts.size(); i++) {
            if (!carts.get(i).ischeck)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算总额
     * @param carts
     */
    private String getTotal(List<CartEntity> carts) {
        Double total = 0.0;
        for (CartEntity cart:carts
             ) {
            if (cart.ischeck)
            {
                total += cart.sum;
            }
        }
        return df.format(total);
    }

    /**
     * 数据加载
     */
    private void initData() {
        carts = new ArrayList<CartEntity>();
        for (int i = 0; i < 10; i++) {
            CartEntity cart = new CartEntity();
            cart.ischeck = true;
            cart.fruit_name = "柠檬";
            cart.fruit_price = 10.00;
            cart.fruit_unit = "斤";
            cart.number = 1;
            cart.sum = cart.fruit_price * cart.number;
            carts.add(cart);
        }
        //初始化合计
        tv_cart_total.setText(getTotal(carts));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cb_cart_check://全选
                if (cb_cart_check.isChecked())
                {
                    //全选
                    for (CartEntity cart:carts
                            ) {
                        cart.ischeck = true;
                    }
                }
                else
                {
                    //全选反向
                    for (CartEntity cart:carts
                            ) {
                        cart.ischeck = false;
                    }
                }
                adapter.notifyDataSetChanged();
                tv_cart_total.setText(getTotal(carts));
                break;
            case R.id.btn_del://选中删除
                //先找出选中的集合
                for (int i = 0; i < carts.size(); i++) {
                    if (carts.get(i).ischeck)
                    {
                        selectCarts.add(carts.get(i));
                    }
                }
                if (selectCarts.size() == 0)
                {
                    Toast.makeText(getActivity(),"请先选择要删除的项哦！",Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确认删除吗？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //删除选中的集合
                        carts.removeAll(selectCarts);
                        adapter.notifyDataSetChanged();
                        tv_cart_total.setText(getTotal(carts));
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.btn_settle://结算
                Intent intent = new Intent(getActivity(), AccountActivity.class);

                //获取选中的集合
                for (int i = 0; i < carts.size(); i++) {
                    if (carts.get(i).ischeck)
                    {

                    }
                }
                //将选中的集合传递给订单提交页面
                intent.putExtra("carts",(Serializable) selectCarts);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(REFRESH_DELAY,1000);
            }
        });
    }
}
