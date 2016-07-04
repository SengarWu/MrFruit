package com.xpple.fruits.cart.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;

public class CartFragment extends BaseFragment {

    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_cart, container, false);
        return parentView;
    }

    public static CartFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
