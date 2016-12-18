package org.chit.slidetounlock.renderers;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;
import android.view.View;

import org.chit.slidetounlock.IRenderer;
import org.chit.slidetounlock.ISlidingData;

public class TranslateRenderer implements IRenderer {

    @Override
    public void renderChanges(ISlidingData slidingData, View child, Point transformed) {
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
    public void onSlideDone(ISlidingData slidingData, View child) {
        child.animate()
                .alpha(0)
                .start();
    }

    @Override
    public void onSlideCancelled(ISlidingData slidingData, View child) {
        child.animate()
                .translationX(0)
                .translationY(0)
                .start();
    }
}
