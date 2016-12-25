package org.chit.slidetounlock.example.ios;

import android.view.View;

import org.chit.slidetounlock.ISlidingData;
import org.chit.slidetounlock.SlideLayout;
import org.chit.slidetounlock.renderers.TranslateRenderer;


public class IosRenderer extends TranslateRenderer {

    private SlideLayout mSlideLayout;

    public IosRenderer(SlideLayout slideLayout) {
        mSlideLayout = slideLayout;
    }

    @Override
    public int onSlideReset(ISlidingData slidingData, View child) {
        mSlideLayout.setAlpha(1);
        return super.onSlideReset(slidingData, child);
    }

    @Override
    public int onSlideDone(ISlidingData slidingData, View child) {
        int duration = super.onSlideDone(slidingData, child);
        mSlideLayout.animate()
                .alpha(0)
                .setDuration(duration)
                .start();
        return duration;
    }
}
