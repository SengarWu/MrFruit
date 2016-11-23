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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.cart.View.CartView;
import com.xpple.fruits.cart.adapter.CartAdapter;
import com.xpple.fruits.cart.model.bean.Cart;
import com.xpple.fruits.cart.model.bean.CartResult;
import com.xpple.fruits.cart.presenter.CartPresenter;
import com.xpple.fruits.cart.presenter.CartPresenterImpl;
import com.xpple.fruits.utils.SharedPreferencesHelper;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, CartView, BaseQuickAdapter.RequestLoadMoreListener {

    private View parentView;
    private RecyclerView rv_cart;
    private CheckBox cb_cart_check;
    private TextView tv_cart_total;
    private Button btn_del;
    private Button btn_settle;
    private SwipeRefreshLayout srl_cart;

    private List<Cart> carts ;
    //保存选中的项
    private List<Cart> selectCarts = new ArrayList<>();
    private List<Integer> selectCartsId = new ArrayList<>();

    private CartAdapter adapter;

    private CartPresenter presenter;
    private ProgressBar progressBar;

    private int userId = 0;
    private int index = 1;
    private final int SIZE = 20;
    private int dataCount = 0;
    private double totalPrice = 0;

    private DecimalFormat df   = new DecimalFormat("######0.0");

    private final int REFRESH_DELAY = 1001;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESH_DELAY:
                    //刷新数据前先做一次网络保存请求
                    if (adapter != null && adapter.getData() != null)
                    {
                        presenter.modifyCart(adapter.getData());
                    }
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
        return parentView;
    }

    private void setupView() {
        adapter = new CartAdapter(carts);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.openLoadMore(20);
        adapter.setOnLoadMoreListener(this);
        rv_cart.setAdapter(adapter);
        //回调子控件点击事件
        rv_cart.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Cart cart = (Cart) baseQuickAdapter.getData().get(position);
                switch (view.getId())
                {
                    case R.id.ib_cart_add://数量增加按钮
                        if (cart.Num < 99)
                        {
                            cart.Num +=1;
                        }
                        cart.sum = cart.Price * cart.Num;
                        break;
                    case R.id.ib_cart_sub://数量减少按钮
                        if (cart.Num > 1)
                        {
                            cart.Num -= 1;
                        }
                        cart.sum = cart.Price * cart.Num;
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
                        if (allChecked(adapter.getData()))
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
                tv_cart_total.setText(getTotal(adapter.getData()));
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
        progressBar = new ProgressBar(getActivity());
    }

    /**
     * 全选状态检查
     * @param carts
     * @return
     */
    private boolean allChecked(List<Cart> carts) {
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
    private String getTotal(List<Cart> carts) {
        Double total = 0.0;
        for (Cart cart:carts
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
        presenter = new CartPresenterImpl(this);
        //获取本地数据
        SharedPreferencesHelper helper = new SharedPreferencesHelper(getActivity());
        userId = helper.getUserId();
        if (userId == 0)
        {
            JUtils.Toast("加载数据失败，用户未登录！");
            return;
        }
        presenter.GetList(userId,index,SIZE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cb_cart_check://全选
                if (cb_cart_check.isChecked())
                {
                    //全选
                    for (Cart cart:adapter.getData()
                            ) {
                        cart.ischeck = true;
                    }
                }
                else
                {
                    //全选反向
                    for (Cart cart:adapter.getData()
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
                        selectCartsId.add(carts.get(i).getId());
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
                        if (adapter != null || userId != 0)
                        {
                            //删除选中的集合
                            if (allChecked(adapter.getData()))
                            {
                                //全选
                                presenter.clearCarts(userId);
                            }
                            else
                            {
                                //非全选
                                presenter.deleteCart(selectCartsId);
                            }
                        }

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
                //获取选中的集合
                if (selectCarts != null)
                {
                    selectCarts.clear();
                }
                for (int i = 0; i < adapter.getData().size(); i++) {
                    if (adapter.getData().get(i).ischeck)
                    {
                        selectCarts.add(adapter.getData().get(i));
                    }
                }
                //将选中的集合传递给订单提交页面
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                intent.putExtra("carts",(Serializable) selectCarts);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //用户离开界面的时候做一次网络数据保存请求
        if (presenter != null && adapter != null && adapter.getData() != null)
        {
            presenter.modifyCart(adapter.getData());
        }
    }

    @Override
    public void onRefresh() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(REFRESH_DELAY,500);
            }
        });
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
    public void GetListSuccess(CartResult result) {
        if (index == 0 || index == 1)
        {
            //首次加载
            carts = result.getCarts();
            dataCount = result.getDataCount();
            setupView();
        }
        else
        {
            adapter.addData(result.getCarts());
        }
    }

    @Override
    public void GetListFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void UpdateCartSuccess(String message) {
        initData();
    }

    @Override
    public void UpdateCartFail(String message) {
        JUtils.Toast("数据更新失败！"+message);
    }

    @Override
    public void DeleteCartSuccess(String message) {
        carts.removeAll(selectCarts);
        adapter.notifyDataSetChanged();
        tv_cart_total.setText(getTotal(carts));
    }

    @Override
    public void DeleteCartFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void ClearCartSuccess(String message) {
        if (adapter != null)
        {
            carts.clear();
        }
        adapter.notifyDataSetChanged();
        tv_cart_total.setText(getTotal(carts));
    }

    @Override
    public void ClearCartFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_cart.post(new Runnable() {
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
                    presenter.GetList(userId,index,SIZE);
                }
            }
        });
    }
}
