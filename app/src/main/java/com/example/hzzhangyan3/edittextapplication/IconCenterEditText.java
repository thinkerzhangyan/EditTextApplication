package com.example.hzzhangyan3.edittextapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by zhangyan on 2016/7/26.
 */
public class IconCenterEditText extends EditText implements TextWatcher, View.OnFocusChangeListener,View.OnKeyListener {

    private static final String TAG = IconCenterEditText.class.getSimpleName();
    /**
     * 是否是默认图标再左边的样式
     */
    private boolean isLeft = false;
    /**
     * 是否点击EnterKey
     */
    private boolean pressEnterKey = false;
    /**
     * IconCenterEditText监听
     */
    private OnEditTextListener listener;

    /**
     * 默认的IconCenterEditText监听
     */
    private OnEditTextListener deFaultListener = new OnEditTextListener() {
        @Override
        public void onEnterKeyAction(View view) {

        }

        @Override
        public void onHasFocusAction(View view) {

        }

        @Override
        public void onLostFocusAction(View view) {

        }
    };


    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;

    public void setOnEditTextListener(OnEditTextListener listener) {
        this.listener = listener;
    }

    public IconCenterEditText(Context context) {
        this(context, null);
        init();
    }

    public IconCenterEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        init();
    }

    public IconCenterEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        //getCompoundDrawables()方法,该方法返回包含控件左,上,右,下四个位置的Drawable的数组
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.mipmap.icon_transfer_delete);
            Log.d(TAG,"=========================================");
        }

        //对drawable的边界进行处理
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());

        //初始化默认清除图标不可见
        setClearIconVisible(false);

        //设置输入框焦点变化情况的监听
        setOnFocusChangeListener(this);
        //设置输入框KeyEnter键按下的监听
        setOnKeyListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);

    }




    @Override
    protected void onDraw(Canvas canvas) {

        if (isLeft) {

            // 如果是默认样式，则直接绘制
            super.onDraw(canvas);

        } else {

            //如果不是默认样式，需要将图标绘制在中间
            Drawable[] drawables = getCompoundDrawables();
            Drawable drawableLeft = drawables[0];
            translate(drawableLeft, canvas);
            super.onDraw(canvas);

        }

    }

    public void translate(Drawable drawable, Canvas canvas) {
        if (drawable != null) {
            float textWidth = getPaint().measureText(getHint().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = drawable.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            if (drawable == getCompoundDrawables()[0]) {
                //平移画布
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
            }
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        // 恢复EditText默认的样式
        if (TextUtils.isEmpty(getText().toString())) {

            isLeft = hasFocus;

            if(listener==null)
                listener = deFaultListener;

            if(hasFocus){
                listener.onHasFocusAction(v);
            }else{
                listener.onLostFocusAction(v);
            }
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        pressEnterKey = (keyCode == KeyEvent.KEYCODE_ENTER);
        if (pressEnterKey) {
            /*隐藏软键盘*/
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }

            if(listener==null)
                listener = deFaultListener;

            if (event.getAction() == KeyEvent.ACTION_UP) {
                listener.onEnterKeyAction(v);
            }
        }
        return false;
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑。
     *
     * getWidth():得到控件的宽度
     * event.getX():抬起时的坐标(改坐标是相对于控件本身而言的)
     * getTotalPaddingRight():clean的图标左边缘至控件右边缘的距离
     * getPaddingRight():clean的图标右边缘至控件右边缘的距离
     *
     * 于是:
     * getWidth() - getTotalPaddingRight()表示:
     * 控件左边到clean的图标左边缘的区域
     * getWidth() - getPaddingRight()表示:
     * 控件左边到clean的图标右边缘的区域
     * 所以这两者之间的区域刚好是clean的图标的区域
     *
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mClearDrawable != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        setClearIconVisible(s.length()>0);
        isLeft=true;
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }



    public interface OnEditTextListener {
        void onEnterKeyAction(View view);
        void onHasFocusAction(View view);
        void onLostFocusAction(View view);
    }

}
