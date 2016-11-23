package com.xpple.fruits.main.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.main.model.bean.Area;
import com.xpple.fruits.main.model.bean.Main;
import com.xpple.fruits.main.presenter.MainPresenter;
import com.xpple.fruits.main.presenter.MainPresenterImpl;
import com.xpple.fruits.main.view.MainView;
import com.xpple.fruits.orchard.ui.OrchardActivity;
import com.xpple.fruits.shop.ui.SeedDetailActivity;
import com.xpple.fruits.shop.ui.ShoppingActivity;
import com.xpple.fruits.utils.SharedPreferencesHelper;
import com.xpple.fruits.utils.Utils;
import com.xpple.fruits.view.BannerLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment implements View.OnClickListener, MainView {
    private TextView tv_main_area;
    private ImageButton ib_main_select_shop;
    private ImageButton ib_main_fruit_buy;
    private ImageButton ib_main_orchard;
    private ImageButton ib_main_find;
    private TextView tv_main_fruit_name;
    private TextView tv_main_fruit_price;
    private TextView tv_main_fruit_unit;
    private Button btn_main_buy_fruit;
    private TextView tv_main_seed_name;
    private TextView tv_main_seed_price;
    private SimpleDraweeView iv_main_fruit_image;
    private SimpleDraweeView iv_main_seed_image;
    private Button btn_main_buy_seed;
    private LinearLayout ll_main;
    private ImageView iv_refresh;

    private BannerLayout bl_main;
    private AreaPopupWindow areaPopupWindow;

    private View parentView;
    private ProgressDialog progressDialog;

    private MainPresenter presenter;
    SharedPreferencesHelper helper;

    DecimalFormat df = new DecimalFormat("######0.00");

    List<Area> areas;
    List<String> urls;
    List<String> links;

    private int userId = 0;
    private int areaId = 0;
    private Main main;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        progressDialog = new ProgressDialog(getActivity());
        presenter = new MainPresenterImpl(this);
        helper = new SharedPreferencesHelper(getActivity());
        userId = helper.getUserId();
        areaId = helper.getUserAreaId();
        //网络请求数据
        if (areaId == 0)
        {
            //本地无保存的区域
            presenter.loadData(1,userId);
        }
        else
        {
            presenter.loadData(areaId,userId);
        }
        return parentView;
    }

    private void initData(Main main) {
        if (main == null)
        {
            dataError();
            return;
        }
        dataShow();
        this.main = main;
        //区域列表
        areas = main.getAreas();
        //广告部分
        urls = new ArrayList<>();
        links = new ArrayList<>();
        if (main.getAdvertisementses() == null || main.getAdvertisementses().size() == 0)
        {
            //默认广告图片
            urls.add("http://mmbiz.qpic.cn/mmbiz/PY1lZ0Jrn5u0ibyOSHvJzkXJUNQrYZddeju0oOZ0LQpAxAPRJvJJDqbqyvPeuQA95VqlvMtp35df5oZCXict7uBQ/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1");
            urls.add("http://mmbiz.qpic.cn/mmbiz/PY1lZ0Jrn5vTAATVH4tgRTfhob579P4UspavECC5y4LoIl54jqpcRxTF4zx7gCPibOWjc2TC7ObmRZsIhicDGDpg/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1");
            urls.add("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");

            links.add("http://mp.weixin.qq.com/s?__biz=MzI5MjA3Mzc3Ng==&mid=2652588910&idx=2&sn=bf55c8bab682a6291b90fc85f15f4c7e&scene=1&srcid=0817S0cGFMvEcbSyTLgpQXjZ#rd");
            links.add("http://mp.weixin.qq.com/s?__biz=MzI5MjA3Mzc3Ng==&mid=2652588920&idx=1&sn=12648f9ce7c9137f5644e9bc480679d7&scene=1&srcid=0817Bwmd4buuNAboMo5542A4#rd");
            links.add("http://mp.weixin.qq.com/s?__biz=MzI5MjA3Mzc3Ng==&mid=2652588749&idx=1&sn=07bd5a59c22b10e698533960c0a38d1e&scene=1&srcid=0817W0BEYDwZ6taWCU1ml6U8#rd");
        }
        else
        {
            for (int i = 0; i < main.getAdvertisementses().size(); i++) {
                urls.add(main.getAdvertisementses().get(i).getPicture());

                links.add(main.getAdvertisementses().get(i).getLink());
            }
        }
        bl_main.setViewUrls(urls);
        bl_main.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(),DiscoverDetailActivity.class);
                intent.putExtra("link",links.get(position));
                startActivity(intent);
            }
        });

        //活动水果
        if (main.getFruit() != null)
        {
            tv_main_fruit_name.setText(main.getFruit().getName());
            tv_main_fruit_price.setText(df.format(main.getFruit().getPrice()));
            tv_main_fruit_unit.setText(main.getFruit().getUnit());
            SimpleDraweeView draweeView = (SimpleDraweeView) iv_main_fruit_image;
            draweeView.setImageURI(main.getFruit().getPicture());
        }
        //活动果种
        if (main.getSeed() != null)
        {
            tv_main_seed_name.setText(main.getSeed().getName());
            tv_main_seed_price.setText(df.format(main.getSeed().getPrice()));
            SimpleDraweeView draweeView1 = (SimpleDraweeView) iv_main_seed_image;
            draweeView1.setImageURI(main.getSeed().getPicture());
        }
    }

    private void initView() {
        tv_main_area = (TextView) parentView.findViewById(R.id.tv_main_area);
        tv_main_fruit_name = (TextView) parentView.findViewById(R.id.tv_main_fruit_name);
        tv_main_fruit_price = (TextView) parentView.findViewById(R.id.tv_main_fruit_price);
        tv_main_fruit_unit = (TextView) parentView.findViewById(R.id.tv_main_fruit_unit);
        tv_main_seed_name = (TextView) parentView.findViewById(R.id.tv_main_seed_name);
        tv_main_seed_price = (TextView) parentView.findViewById(R.id.tv_main_seed_price);
        ib_main_select_shop= (ImageButton) parentView.findViewById(R.id.ib_main_select_shop);
        ib_main_select_shop.setOnClickListener(this);
        ib_main_fruit_buy= (ImageButton) parentView.findViewById(R.id.ib_main_fruit_buy);
        ib_main_fruit_buy.setOnClickListener(this);
        ib_main_orchard= (ImageButton) parentView.findViewById(R.id.ib_main_orchard);
        ib_main_orchard.setOnClickListener(this);
        ib_main_find= (ImageButton) parentView.findViewById(R.id.ib_main_find);
        ib_main_find.setOnClickListener(this);
        btn_main_buy_fruit= (Button) parentView.findViewById(R.id.btn_main_buy_fruit);
        btn_main_buy_fruit.setOnClickListener(this);
        btn_main_buy_seed= (Button) parentView.findViewById(R.id.btn_main_buy_seed);
        btn_main_buy_seed.setOnClickListener(this);
        iv_main_fruit_image = (SimpleDraweeView) parentView.findViewById(R.id.iv_main_fruit_image);
        iv_main_fruit_image.setOnClickListener(this);
        iv_main_seed_image= (SimpleDraweeView) parentView.findViewById(R.id.iv_main_seed_image);
        iv_main_seed_image.setOnClickListener(this);
        bl_main = (BannerLayout) parentView.findViewById(R.id.bl_main);
        ll_main = (LinearLayout) parentView.findViewById(R.id.ll_main);
        //加载失败刷新
        iv_refresh = (ImageView) parentView.findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Utils.isFastDoubleClick())
        {
            return;
        }
        switch (v.getId()){
            case R.id.ib_main_select_shop://点击标题栏的商铺地点按钮进行水果商店选择
                areaPopupWindow = new AreaPopupWindow(getActivity(), areas, new OnItemChildClickListener() {
                    @Override
                    public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        tv_main_area.setText(areas.get(i).getName().toString());
                        SharedPreferencesHelper helper = new SharedPreferencesHelper(getActivity());
                        helper.setUserAreaId(areas.get(i).getId());
                        helper.setUserAreaName(areas.get(i).getName().toString());
                        presenter.loadData(areas.get(i).getId(),userId);
                        areaPopupWindow.dismiss();
                    }
                });
                // 以下拉方式显示
                areaPopupWindow.showAsDropDown(parentView.findViewById(R.id.rl_main_top));
                break;
            case R.id.ib_main_fruit_buy://点击水果购买跳转到购买水果的页面
                startActivity(new Intent(getActivity(), ShoppingActivity.class));
                break;
            case R.id.ib_main_orchard://点击果园物语跳转到自己的果园页面
                startActivity(new Intent(getActivity(), OrchardActivity.class));
                break;
            case R.id.ib_main_find://点击发现跳转到推文界面
                startActivity(new Intent(getActivity(),DiscoverActivity.class));
                break;
            case R.id.btn_main_buy_fruit://点击特价一栏将特价水果加入购物车
                if (main.getFruit() != null)
                {
                    presenter.addCart(main.getFruit().getId(),userId,1);
                }
                break;
            case R.id.btn_main_buy_seed://点击活动果种一栏
            case R.id.iv_main_seed_image://点击果种图片查看果种详情
                if (main == null || main.getSeed() == null)
                {
                    dataError();
                    return;
                }
                Intent intent = new Intent(getActivity(),SeedDetailActivity.class);
                intent.putExtra("seedId",main.getSeed().getId());
                startActivity(intent);
                break;
            case R.id.iv_refresh://页面刷新
                areaId = helper.getUserAreaId();
                userId = helper.getUserId();
                if (areaId == 0)
                {
                    //本地无保存的区域
                    presenter.loadData(1,userId);
                }
                else
                {
                    presenter.loadData(areaId,userId);
                }
                break;
        }
    }

    private void dataShow()
    {
        //数据加载成功
        ll_main.setVisibility(View.VISIBLE);
        iv_refresh.setVisibility(View.GONE);
    }

    private void dataError() {
        //数据加载失败
        ll_main.setVisibility(View.GONE);
        iv_refresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void LoadSuccess(Main main) {
        initData(main);
    }

    @Override
    public void LoadFail(String s) {
        JUtils.Toast("加载数据失败"+s);
    }

    @Override
    public void addCartSuccess(String s) {
        JUtils.Toast("添加购物车成功");
    }

    @Override
    public void addCartFail(String s) {
        JUtils.Toast("添加购物车失败！"+s);
    }
}
