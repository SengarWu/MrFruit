package com.xpple.fruits.cart.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;

public class CartFragment extends BaseFragment {

    private View parentView;

    private SwipeRefreshLayout mRefreshLayout;
    private ListView lv_cart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_cart, container, false);
        initView();
        return parentView;
    }

    private void initView() {
        /*mRefreshLayout = (SwipeRefreshLayout) parentView.findViewById(R.id.refresh_layout);
        lv_cart = (ListView) parentView.findViewById(R.id.lv_cart);*/

    }

    public static CartFragment newInstance() {
        
        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
