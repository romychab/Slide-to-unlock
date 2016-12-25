package com.github.romychab.slidetounlock.sliders;

/*
 * Created by rom on 18.12.16.
 */

import com.github.romychab.slidetounlock.ISlidingData;

public class RadialSlider extends RectangleSlider {

    @Override
    public float getPercentage(ISlidingData data, int x, int y) {
        float vertical = mVerticalSlider.getPercentage(data, x, y);
        float horizontal = mHorizontalSlider.getPercentage(data, x, y);
        return (float) Math.sqrt(vertical * vertical + horizontal * horizontal);
    }

}
