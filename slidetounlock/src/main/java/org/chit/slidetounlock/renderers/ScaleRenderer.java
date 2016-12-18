package org.chit.slidetounlock.renderers;

/*
 * Created by rom on 16.12.16.
 */

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.view.View;

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

        child.setLeft(left);
        child.setTop(top);
        child.setRight(right);
        child.setBottom(bottom);
    }

    @Override
    public void onSlideReset(ISlidingData slidingData, View child) {
        child.setTranslationX(0);
        child.setTranslationY(0);
        child.setAlpha(1);
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
    public int onSlideCancelled(final ISlidingData slidingData, final View child) {
        ValueAnimator animator = new ValueAnimator();
        animator.setFloatValues(0, 1);
        final int rangeLeft = child.getLeft() - slidingData.getChildStartRect().left;
        final int rangeBottom = child.getBottom() - slidingData.getChildStartRect().bottom;
        final int rangeRight = child.getRight() - slidingData.getChildStartRect().right;
        final int rangeTop = child.getTop() - slidingData.getChildStartRect().top;
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = 1 - valueAnimator.getAnimatedFraction();
                child.setLeft(slidingData.getChildStartRect().left + (int)(rangeLeft * fraction));
                child.setRight(slidingData.getChildStartRect().right + (int)(rangeRight * fraction));
                child.setTop(slidingData.getChildStartRect().top + (int)(rangeTop * fraction));
                child.setBottom(slidingData.getChildStartRect().bottom + (int)(rangeBottom * fraction));
            }
        });
        animator.setDuration(DEF_DURATION);
        animator.start();
        return DEF_DURATION;
    }
}
