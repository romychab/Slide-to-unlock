package org.chit.slidetounlock.renderers;

/*
 * Created by rom on 16.12.16.
 */

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;

import org.chit.slidetounlock.IRenderer;
import org.chit.slidetounlock.ISlidingData;

public class ScaleRenderer implements IRenderer {

    public static final int DEF_DURATION = 300;

    @Override
    public void renderChanges(ISlidingData slidingData, View child, float percentage, Point transformed) {
        int rightRange = slidingData.getParentDimen().width - slidingData.getChildStartRect().right;
        int right = (int) (slidingData.getChildStartRect().right + percentage * rightRange);

        int leftRange = slidingData.getChildStartRect().left;
        int left = (int) (slidingData.getChildStartRect().left - percentage * leftRange);

        int bottomRange = slidingData.getParentDimen().height - slidingData.getChildStartRect().bottom;
        int bottom = (int) (slidingData.getChildStartRect().bottom + percentage * bottomRange);

        int topRange = slidingData.getChildStartRect().top;
        int top = (int) (slidingData.getChildStartRect().top - percentage * topRange);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        params.leftMargin = left;
        params.topMargin = top;
        params.width = right - left;
        params.height = bottom - top;
        child.setLayoutParams(params);
    }

    @Override
    public int onSlideReset(ISlidingData slidingData, View child) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        params.leftMargin = slidingData.getChildStartRect().left;
        params.topMargin = slidingData.getChildStartRect().top;
        params.width = slidingData.getChildStartRect().right - params.leftMargin;
        params.height = slidingData.getChildStartRect().bottom - params.topMargin;
        child.setAlpha(1);
        child.setLayoutParams(params);
        slidingData.publishOnSlideChanged(0);
        return 0;
    }

    @Override
    public int onSlideDone(ISlidingData slidingData, View child) {
        child.animate()
                .alpha(0)
                .setDuration(DEF_DURATION)
                .start();
        return DEF_DURATION;
    }

    @Override
    public int onSlideCancelled(final ISlidingData slidingData, final View child, final float lastPercentage) {
        ValueAnimator animator = new ValueAnimator();
        animator.setFloatValues(0, 1);
        final int rangeLeft = child.getLeft() - slidingData.getChildStartRect().left;
        final int rangeBottom = child.getBottom() - slidingData.getChildStartRect().bottom;
        final int rangeRight = child.getRight() - slidingData.getChildStartRect().right;
        final int rangeTop = child.getTop() - slidingData.getChildStartRect().top;
        final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = 1 - valueAnimator.getAnimatedFraction();
                params.leftMargin = slidingData.getChildStartRect().left + (int)(rangeLeft * fraction);
                params.width = slidingData.getChildStartRect().right + (int)(rangeRight * fraction) - params.leftMargin;
                params.topMargin = slidingData.getChildStartRect().top + (int)(rangeTop * fraction);
                params.height = slidingData.getChildStartRect().bottom + (int)(rangeBottom * fraction) - params.topMargin;
                child.setLayoutParams(params);
                slidingData.publishOnSlideChanged(fraction * lastPercentage);
            }
        });
        animator.setDuration(DEF_DURATION);
        animator.start();
        return DEF_DURATION;
    }
}
