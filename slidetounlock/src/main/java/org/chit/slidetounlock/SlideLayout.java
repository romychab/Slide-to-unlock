package org.chit.slidetounlock;

/*
 * Created by rom on 14.12.16.
 */

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.LinkedHashSet;
import java.util.Set;

public class SlideLayout
        extends FrameLayout
        implements
            View.OnTouchListener,
            ISlidingData {

    private float mThreshold;

    private Set<ISlideChangeListener> mChangeListeners = new LinkedHashSet<>();
    private Set<ISlideListener> mSlideListeners = new LinkedHashSet<>();

    private boolean mStarted;

    private int mStartX;
    private int mStartY;

    private ISlider mSlider;

    private Dimen mParentStartDimen = new Dimen();
    private Rect mChildStartRect = new Rect();

    @IdRes
    private int mChildId;
    private View mChild;

    // --- init

    public SlideLayout(Context context) {
        super(context);
        constructInit();
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructInit();
    }

    public SlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructInit();
    }

    private void constructInit() {

    }

    private void init() {

    }

    // --- lifecycle

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnTouchListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setOnTouchListener(null);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
        // TODO: 14.12.16
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        // TODO: 14.12.16
    }

    // --- getters/setters

    public void setChildId(@IdRes int id) {
        mChildId = id;
        mChild = null;
    }

    public void setThreshold(float threshold) {
        mThreshold = threshold;
    }

    public View getChild() {
        if (null == mChild) {
            mChild = findViewById(mChildId);
        }
        return mChild;
    }

    public void setSlider(ISlider slider) {
        mSlider = slider;
    }

    // --- listeners

    public void addSlideListener(ISlideListener listener) {
        mSlideListeners.add(listener);
    }

    public void removeSlideListener(ISlideListener listener) {
        mSlideListeners.remove(listener);
    }

    public void addSlideChangeListener(ISlideChangeListener listener) {
        mChangeListeners.add(listener);
    }

    public void removeSlideChangeListener(ISlideChangeListener listener) {
        mChangeListeners.remove(listener);
    }

    // --- ISlidingData impl.

    @Override
    public int getStartX() {
        return mStartX;
    }

    @Override
    public int getStartY() {
        return mStartY;
    }

    @Override
    public Rect getChildStartRect() {
        return mChildStartRect;
    }

    @Override
    public Dimen getParentDimen() {
        return mParentStartDimen;
    }


    // --- OnTouchListener impl.

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mStarted) {
                    return false;
                }
                mStarted = canStart(motionEvent);
                return mStarted;
            case MotionEvent.ACTION_MOVE:
                handleActionMove();
                return true;
            case MotionEvent.ACTION_UP:
                handleFinishing(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                handleFinishing(false);
                break;
        }
        return false;
    }

    private boolean canStart(MotionEvent motionEvent) {

        mStartX = (int) motionEvent.getX();
        mStartY = (int) motionEvent.getY();

        mParentStartDimen.width = getWidth();
        mParentStartDimen.height = getHeight();

        mChildStartRect.left = getChild().getLeft();
        mChildStartRect.right = getChild().getRight();
        mChildStartRect.top = getChild().getTop();
        mChildStartRect.bottom = getChild().getBottom();

        if (!mSlider.allowStart(this)) {
            return false;
        }

        return true;
    }

    private void handleActionMove() {
        if (!mStarted) {
            return;
        }


    }

    private void handleFinishing(boolean done) {

    }
}
