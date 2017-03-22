package com.wxx.shopping.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.wxx.shopping.R;

/**
 * 作者：Tangren_ on 2017/3/22 11:34.
 * 邮箱：wu_tangren@163.com
 * TODO:自定义TextView气泡效果
 */

public class TextViewBubble extends TextView {

    private int width;
    private int height;

    //字体颜色默认RED
    private int textColor;

    //字体大小
    private float textSize;

    //画笔宽度
    private int strokeWidth = 5;

    //文字画笔
    private Paint tPaint;

    private float mPointX = 0;
    private float mPointY = 0;

    private float mPintTextWidth = 0;
    private float mPaintTextHeight = 0;
    private CharSequence mPintText;
    private float circleX = 0;
    private float circleY = 0;
    //圆画笔
    private Paint circlePain;

    //圆圈的颜色
    private int circleClolr;

    //圆圈的宽度
    private int circleStroWidth = 3;

    //是否是实体圆
    private boolean isFill;

    //偏移量
    private float offsetX = 10;
    private float offsetY = 0;

    //数字最大宽度
    private float maxWidth = 0;

    private static final String TAG = "TextViewBubble";


    public TextViewBubble(Context context) {
        this(context, null);
    }

    public TextViewBubble(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewBubble, 0, R.style.bubbleTextView);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int arr = array.getIndex(i);
            switch (arr) {
                case R.styleable.TextViewBubble_bubbleColor:
                    circleClolr = array.getColor(arr, 0);
                    break;
                case R.styleable.TextViewBubble_bubbleTextColor:
                    textColor = array.getColor(arr, 0);
                    break;
                case R.styleable.TextViewBubble_bubbleTextSize:
                    textSize = array.getDimensionPixelSize(arr, 0);
                    break;
                case R.styleable.TextViewBubble_bubbleFill:
                    isFill = array.getBoolean(arr, false);
                    break;
                case R.styleable.TextViewBubble_bubbleText:
                    mPintText=array.getString(arr);
                    break;
            }
        }

        array.recycle();
        tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tPaint.setColor(textColor);
        tPaint.setStrokeWidth(strokeWidth);
        tPaint.setTextSize(textSize);
        tPaint.setStyle(Paint.Style.FILL);
        tPaint.setAntiAlias(true);

        circlePain = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePain.setColor(circleClolr);
        circlePain.setStrokeWidth(circleStroWidth);
        if (isFill)
            circlePain.setStyle(Paint.Style.FILL);
        else
            circlePain.setStyle(Paint.Style.STROKE);
        circlePain.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        if (this.getText() != null) {
            if (mPintText != null && !mPintText.equals("")) {
                //计算出数字最大宽度即”99+“
                maxWidth = tPaint.measureText("99+");
                //当前宽度
                mPintTextWidth = tPaint.measureText(String.valueOf(mPintText));
                //计算的是数字的x坐标
                //因为已经限定数字的最大宽度是"99+"字符串的宽度
//				mWidth-maxWidth/2就是圆心的位置，
//				如果我们要将数字画在园的中心，就要
//				获取当前数字的宽度的一半
//				mWidth-maxWidth/2-mPointTextWidth/2，
//				然后再减去自己设置的x轴位移量
                mPointX = width - maxWidth / 2 - mPintTextWidth / 2 - offsetX;
                Paint.FontMetrics metrics = tPaint.getFontMetrics();
                //因为数字的外面要包一个圆，所以为了让圆能显示完全，就让数字的高也和宽相等，减去位移量，获取数字的高度
                float textH = maxWidth + offsetY;
                //如果看过http://www.jianshu.com/p/a3d15421a718我这篇文章，应该知道这是获取baseline，
                //获取到这个位置就能将文字在指定位置的y轴中心显示
                mPaintTextHeight = (textH - (metrics.descent - metrics.ascent)) / 2 - metrics.ascent;
                //为了能让圆圈显示完整，所以+1*_density
                mPointY = mPaintTextHeight + 1;
                //圆的x轴
                circleX = width - (maxWidth) / 2 - offsetX;
                //圆的y轴
                circleY = textH / 2 + 1;

            }
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPintText != null && !mPintText.equals("")) {
            canvas.drawCircle(circleX, circleY, maxWidth / 2 + 1, circlePain);
            try {
                if (Integer.parseInt(mPintText.toString()) > 99) {
                    canvas.drawText("99+", mPointX, mPointY, tPaint);
                } else
                    canvas.drawText(mPintText.toString(), mPointX, mPointY, tPaint);
            } catch (Exception e) {
                canvas.drawText("0", mPointX, mPointY, tPaint);
                Log.e(TAG, "onDraw-->>mPintText Not numeric format");
            }
        }


    }


    public void setmPointText(CharSequence mPointText) {
        this.mPintText = mPointText;
        invalidate();
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public boolean getIsFill(boolean value) {
        return isFill;
    }

    public TextViewBubble setFill(boolean value) {
        this.isFill = value;
        return this;
    }
}
