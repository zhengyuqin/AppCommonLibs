package net.luna.common.view.scrollview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import net.luna.common.view.button.CustomView;


public class ScrollView extends android.widget.ScrollView {


	/*
     * This class avoid problems in scrollviews with elements in library
	 * Use it if you want use a ScrollView in your App
	 */


    private int downX;
    private int downY;
    private int mTouchSlop;

    public ScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
//        return super.computeScrollDeltaToGetChildRectOnScreen(rect);
        return 0;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//		if(!onInterceptTouchEvent(ev)){
        for (int i = 0; i < ((ViewGroup) getChildAt(0)).getChildCount(); i++) {
            try {
                CustomView child = (CustomView) ((ViewGroup) getChildAt(0)).getChildAt(i);
                if (child.isLastTouch) {
                    child.onTouchEvent(ev);
                    return true;
                }
            } catch (ClassCastException e) {
            }
        }
//	    }
        return super.onTouchEvent(ev);
    }


}
