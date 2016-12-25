package com.github.romychab.slidetounlock.example.ios;

import android.view.View;

import com.github.romychab.slidetounlock.ISlidingData;
import com.github.romychab.slidetounlock.SlideLayout;
import com.github.romychab.slidetounlock.renderers.TranslateRenderer;


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
