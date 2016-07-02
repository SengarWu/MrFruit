package com.xpple.fruits.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/6/30.
 */
public class BaseFragment extends Fragment {
    protected Activity mActivity;

    //保证Fragment即使在onDetach后，仍持有Activity的引用
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }
}
