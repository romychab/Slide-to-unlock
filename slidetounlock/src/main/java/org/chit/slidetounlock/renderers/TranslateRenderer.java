package org.chit.slidetounlock.renderers;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;
import android.view.View;

import org.chit.slidetounlock.IRenderer;
import org.chit.slidetounlock.ISlidingData;

public class TranslateRenderer implements IRenderer {

    public static final int DEF_DURATION = 300;

    @Override
    public void renderChanges(ISlidingData slidingData, View child, float percentage, Point transformed) {
        int translateX = transformed.x - slidingData.getStartX();
        int translateY = transformed.y - slidingData.getStartY();
        child.setTranslationX(translateX);
        child.setTranslationY(translateY);
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
    public int onSlideCancelled(ISlidingData slidingData, View child) {
        child.animate()
                .translationX(0)
                .translationY(0)
                .setDuration(DEF_DURATION)
                .start();
        return DEF_DURATION;
    }
}
