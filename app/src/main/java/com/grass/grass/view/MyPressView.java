package com.grass.grass.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import com.grass.grass.R;


/**
 * @author huchao
 * @time 2017/7/13 16:26
 * @explain: 自定义view, 根据属性完成点击背景的变化
 */
@SuppressWarnings("WrongConstant")
public class MyPressView extends android.support.v7.widget.AppCompatTextView {
    /**
     * 控件的样式
     */
    private GradientDrawable mGradientDrawable;
    /**
     * 背景色，int类型
     */
    private int mBackGroundColor = 0;
    /**
     * 按下后的背景色，int类型
     */
    private int mBackGroundColorSelected = 0;
    /**
     * 圆角半径
     */
    private float mRadius = 0;
    /**
     * 是否设置圆角
     */
    private Boolean mFillet = false;
    /**
     * 内容是否默认居中
     */
    private Boolean mConentCenter = true;


    public MyPressView(Context context) {
        this(context, null);
    }

    public MyPressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initArrt(context, attrs);

        init();
    }

    private void initArrt(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyPressView);
        mBackGroundColor = ta.getColor(R.styleable.MyPressView_mPressBackGoroundColor, Color.TRANSPARENT);
        Drawable background = getBackground();
        if (background != null && background instanceof ColorDrawable) {
            mBackGroundColor = ((ColorDrawable) background).getColor();
        }
        mBackGroundColorSelected = ta.getColor(R.styleable.MyPressView_mPressBackGoroundColorSelected, Color.TRANSPARENT);
        mRadius = ta.getDimension(R.styleable.MyPressView_mPressRadius, 0);
        if (mRadius != 0) {
            mFillet = true;
        } else {
            mFillet = false;
        }
        mConentCenter = ta.getBoolean(R.styleable.MyPressView_mPressContentCenter, true);
        ta.recycle();
    }


    private void init() {
        //设置背景颜色
        setBackgroundColor(mBackGroundColor);
        //若未设置默认文字居中
        if (mConentCenter) {
            setGravity(Gravity.CENTER);
        }
        //判断是否是圆角
        setViewRadius();
    }

    private void setViewRadius() {
        if (mFillet) {
            if (mGradientDrawable == null) {
                mGradientDrawable = new GradientDrawable();
            }
            //GradientDrawable.RECTANGLE
            mGradientDrawable.setColor(mBackGroundColor);
            mGradientDrawable.setCornerRadius(mRadius);
            setBackgroundDrawable(mGradientDrawable);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isClickable()) {
            setColor(event.getAction());
        }
        return super.onTouchEvent(event);

    }


    //改变样式
    private void setColor(int state) {
        if (state == MotionEvent.ACTION_DOWN) {
            //按下
            pointDown();
        }
        if (state == MotionEvent.ACTION_UP) {
            //抬起
            pointUp();
        }
    }

    private void pointDown() {
        if (mBackGroundColorSelected != 0) { //先判断是否设置了按下后的背景色int型
            if (mFillet) {
                if (mGradientDrawable == null) {
                    mGradientDrawable = new GradientDrawable();
                }
                mGradientDrawable.setColor(mBackGroundColorSelected);
            } else {
                setBackgroundColor(mBackGroundColorSelected);
            }
        } else {
            //设置为透明色
            setBackgroundColor(Color.TRANSPARENT);
        }

    }

    private void pointUp() {
        if (mBackGroundColor == 0) {//如果没有设置背景色，默认改为透明
            if (mFillet) {
                if (mGradientDrawable == null) {
                    mGradientDrawable = new GradientDrawable();
                }
                mGradientDrawable.setColor(Color.TRANSPARENT);
            } else {
                setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            if (mFillet) {
                if (mGradientDrawable == null) {
                    mGradientDrawable = new GradientDrawable();
                }
                mGradientDrawable.setColor(mBackGroundColor);
            } else {
                setBackgroundColor(mBackGroundColor);
            }
        }
    }
}