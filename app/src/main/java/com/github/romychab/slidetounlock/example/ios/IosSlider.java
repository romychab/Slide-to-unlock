package com.github.romychab.slidetounlock.example.ios;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.github.romychab.slidetounlock.SlideLayout;
import com.github.romychab.slidetounlock.example.R;
import com.github.romychab.slidetounlock.sliders.Direction;
import com.github.romychab.slidetounlock.sliders.HorizontalSlider;

public class IosSlider extends SlideLayout {

    private Paint mTextPaint;

    private int mPaddingRight;
    private Rect mBounds = new Rect();

    private String mTextToBeDrawn;

    LinearGradient[] mShaders;
    int mIndex = 0;

    boolean mActive = false;

    public IosSlider(Context context) {
        super(context);
        init();
    }

    public IosSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IosSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.ios_slider, this);
        setBackgroundResource(R.drawable.ios_back);

        setSlider(new HorizontalSlider(Direction.FORWARD));
        setRenderer(new IosRenderer(this));
        setAllowEventsAfterFinishing(true);
        setChildId(R.id.ios_child);

        mTextToBeDrawn = getContext().getString(R.string.ios_text);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.ios_text_size));

        mPaddingRight = getResources().getDimensionPixelSize(R.dimen.ios_padding_right);


        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                initShaders();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
        postDelayed(mRunnable, 100);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
        removeCallbacks(mRunnable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mTextPaint.getTextBounds(mTextToBeDrawn, 0, mTextToBeDrawn.length(), mBounds);
        int startX = getWidth() - mBounds.width() - mPaddingRight;
        int startY = (int) (getHeight() - mTextPaint.descent() - mTextPaint.ascent()) / 2;
        if (mShaders.length > 0) {
            mTextPaint.setShader(mShaders[mIndex]);
        }
        canvas.drawText(mTextToBeDrawn, startX, startY, mTextPaint);
    }

    private void initShaders() {
        final int COUNT = 100;
        mShaders = new LinearGradient[COUNT];
        float stepX = getWidth() * 3 / COUNT;
        int[] colors = new int[] { Color.GRAY, Color.WHITE, Color.GRAY};
        for (int i = 0; i < COUNT; i++) {
            mShaders[i] = new LinearGradient(stepX * i, 0, stepX * (i + 5), 0, colors, null, Shader.TileMode.CLAMP);
        }
    }


    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mIndex++;
            if (mIndex >= mShaders.length) {
                mIndex = 0;
            }
            invalidate();
            if (mActive) {
                postDelayed(mRunnable, 50);
            }
        }
    };
}
