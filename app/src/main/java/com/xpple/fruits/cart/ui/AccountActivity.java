package com.xpple.fruits.cart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.cart.View.AccountView;
import com.xpple.fruits.cart.adapter.AccountAdapter;
import com.xpple.fruits.cart.model.bean.Cart;
import com.xpple.fruits.cart.model.bean.CartResult;
import com.xpple.fruits.cart.presenter.AccountPresenter;
import com.xpple.fruits.cart.presenter.AccountPresenterImpl;
import com.xpple.fruits.me.ui.AddOtherActivity;
import com.xpple.fruits.me.ui.AddressActivity;
import com.xpple.fruits.order.model.bean.FruitExtro;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.order.model.bean.OrderExtro;
import com.xpple.fruits.order.ui.MyOrderActivity;
import com.xpple.fruits.utils.SharedPreferencesHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener, AccountView, RadioGroup.OnCheckedChangeListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private RecyclerView rv_account;
    private TextView tv_account_total_price;
    private Button btn_account_pay;

    private LinearLayout ll_account_people;
    private RadioGroup rg_account_paymentMethod;
    private RadioButton rb_account_zfb;
    private RadioButton rb_account_wechat;
    private RadioButton rb_account_cash;
    private LinearLayout ll_account_other;
    private TextView tv_account_other;
    private TextView tv_account_name;
    private TextView tv_account_phone;
    private TextView tv_account_area;
    private TextView tv_account_address;


    private DecimalFormat df   = new DecimalFormat("######0.00");

    private List<Cart> carts;
    private AccountAdapter adapter;
    private int userId = 0;
    private int index = 1;
    private final int SIZE = 20;
    private int dataCount = 0;
    private String paymentMethod;

    private AccountPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initData();
    }

    private void setupView() {
        adapter = new AccountAdapter(carts);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.setOnLoadMoreListener(this);
        adapter.openLoadMore(20);
        //添加头部视图
        addHeadView();
        rv_account.setAdapter(adapter);
        //计算合计
        tv_account_total_price.setText(getTotal(carts));
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.account_header, (ViewGroup) rv_account.getParent(), false);
        ll_account_people = (LinearLayout) headView.findViewById(R.id.ll_account_people);
        ll_account_people.setOnClickListener(this);
        tv_account_name = (TextView) headView.findViewById(R.id.tv_account_name);
        tv_account_phone = (TextView) headView.findViewById(R.id.tv_account_phone);
        tv_account_area = (TextView) headView.findViewById(R.id.tv_account_area);
        tv_account_address = (TextView) headView.findViewById(R.id.tv_account_address);
        rg_account_paymentMethod = (RadioGroup) headView.findViewById(R.id.rg_account_paymentMethod);
        rg_account_paymentMethod.setOnCheckedChangeListener(this);
        rb_account_zfb = (RadioButton) headView.findViewById(R.id.rb_account_zfb);
        rb_account_wechat = (RadioButton) headView.findViewById(R.id.rb_account_wechat);
        rb_account_cash = (RadioButton) headView.findViewById(R.id.rb_account_cash);
        tv_account_other = (TextView) headView.findViewById(R.id.tv_account_other);
        ll_account_other = (LinearLayout) headView.findViewById(R.id.ll_account_other);
        ll_account_other.setOnClickListener(this);
        adapter.addHeaderView(headView);
    }

    /**
     * 计算合计
     * @param carts
     * @return
     */
    private String getTotal(List<Cart> carts) {
        Double total = 0.0;
        for (Cart cart:carts
             ) {
            if (cart.discount > 0)
            {
                total += cart.discount;
            }
            else
            {
                total += cart.Price;
            }
        }
        return df.format(total);
    }

    private void initData() {
        //读取用户信息

        //水果数据需要判断来源，如果是从购物车界面则从以客户端数据为准，
        // 如果是从水果购买界面，则读取网络购物车数据，
        presenter = new AccountPresenterImpl(this);
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this);
        userId = helper.getUserId();
        if (userId == 0)
        {
            JUtils.Toast("用户未登录");
            return;
        }

        //读取本地数据
        String receiver = helper.getUserReceiver();
        String phone = helper.getUserReceiverPhone();
        String area = helper.getUserReceiverArea();
        String address = helper.getUserReceiverAddress();

        tv_account_name.setText(receiver);
        tv_account_phone.setText(phone);
        tv_account_area.setText(area);
        tv_account_address.setText(address);

        //读取购物车传递的水果数据
        Intent intent = getIntent();
        if (intent != null)
        {
            if (intent.getSerializableExtra("carts") != null)
            {
                carts = (List<Cart>) getIntent().getSerializableExtra("carts");
            }
        }
        else
        {
            //网络请求数据
            presenter.GetCartList(userId,index,SIZE);
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
        tv_title.setText("结算");
        rv_account = $(R.id.rv_account);
        rv_account.setLayoutManager(new LinearLayoutManager(this));
        tv_account_total_price=$(R.id.tv_account_total_price);
        btn_account_pay = $(R.id.btn_account_pay);
        btn_account_pay.setOnClickListener(this);
        progressBar = new ProgressBar(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 0://修改个人信息
                if (resultCode == 0)
                {
                    SharedPreferencesHelper helper = new SharedPreferencesHelper(this);
                    tv_account_name.setText(helper.getUserReceiver());
                    tv_account_phone.setText(helper.getUserReceiverPhone());
                    tv_account_area.setText(helper.getUserReceiverArea());
                    tv_account_address.setText(helper.getUserReceiverAddress());
                }
                break;
            case 1://点击备注
                if (resultCode == 1)
                {
                    tv_account_other.setText(data.getStringExtra("remarks"));
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ll_account_people://修改个人信息
                Intent intent = new Intent(AccountActivity.this,AddressActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.ll_account_other://点击备注
                Intent intent1 = new Intent(AccountActivity.this,AddOtherActivity.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.btn_account_pay://提交订单
                if (TextUtils.isEmpty(tv_account_name.getText().toString())
                        || TextUtils.isEmpty(tv_account_phone.getText().toString())
                        || TextUtils.isEmpty(tv_account_area.getText().toString())
                        || TextUtils.isEmpty(tv_account_address.getText().toString()))
                {
                    JUtils.Toast("请填写完整收货人信息");
                    return;
                }
                Order order = new Order();
                List<FruitExtro> fruitExtros = new ArrayList<FruitExtro>();
                for (int i = 0; i < carts.size(); i++) {
                    fruitExtros.get(i).setName(carts.get(i).getName());
                    fruitExtros.get(i).setNum(carts.get(i).getNum());
                }
                order.UserId = userId;
                order.PaymentMethod = paymentMethod;
                OrderExtro orderExtro = new OrderExtro();
                orderExtro.Receiver = tv_account_name.getText().toString();
                orderExtro.Phone = tv_account_phone.getText().toString();
                orderExtro.Area = tv_account_area.getText().toString();
                orderExtro.Address = tv_account_address.getText().toString();
                orderExtro.Remarks = tv_account_other.getText().toString();
                order.fruits = fruitExtros;
                presenter.submitOrder(order);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        rv_account.post(new Runnable() {
            @Override
            public void run() {
                if (adapter.getData().size() >= dataCount)
                {
                    adapter.loadComplete();
                }
                else
                {
                    index++;
                    presenter.GetCartList(userId,index,SIZE);
                }
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
    public void GetCartListSuccess(CartResult result) {
        if (index == 0 || index == 1)
        {
            //首次请求
            carts = result.getCarts();
            dataCount = result.getDataCount();
            setupView();
        }
        else
        {
            adapter.addData(result.getCarts());
            tv_account_total_price.setText(getTotal(adapter.getData()));
        }
    }

    @Override
    public void GetCartListFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void SubmitOrderSuccess(int orderId) {
        JUtils.Toast("订单提交成功");
        Intent intent = new Intent(AccountActivity.this, MyOrderActivity.class);
        intent.putExtra("orderId",orderId);
        startActivity(intent);
    }

    @Override
    public void SubmitOrderFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.rb_account_zfb:
                //支付宝
                JUtils.Toast("支付宝支付正在接入当中......");
                break;
            case R.id.rb_account_wechat:
                JUtils.Toast("微信支付正在接入当中......");
                break;
            case R.id.rb_account_cash:
                paymentMethod = "货到付款";
                break;
        }
    }
}
