package com.xpple.fruits.view;

import android.content.Context;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/8/12.
 */
public class ObservableScrollView extends ScrollView {

    public ObservableScrollView(Context context) {
        super(context);
    }

    OnScrollToBottomListener mOnScrollToBottomListener;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l,t,oldl,oldt);
        // 滑动的距离加上本身的高度与子View的高度对比
        if(t + getHeight() >=  getChildAt(0).getMeasuredHeight()){
            // ScrollView滑动到底部
            if(mOnScrollToBottomListener != null) {
                mOnScrollToBottomListener.onScrollToBottom();
            }
        } else {
            if(mOnScrollToBottomListener != null) {
                mOnScrollToBottomListener.onNotScrollToBottom();
            }
        }
    }

    public void setScrollToBottomListener(OnScrollToBottomListener listener) {
        this.mOnScrollToBottomListener = listener;
    }

    public interface OnScrollToBottomListener {
        void onScrollToBottom();
        void onNotScrollToBottom();
    }
}
