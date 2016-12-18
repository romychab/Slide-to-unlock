package org.chit.slidetounlock.sliders;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;

import org.chit.slidetounlock.ISlider;
import org.chit.slidetounlock.ISlidingData;

public class RadialSlider implements ISlider {

    @Override
    public float getPercentage(ISlidingData data, int x, int y) {
        return 0;
    }

    @Override
    public boolean allowStart(ISlidingData data) {
        return false;
    }

    @Override
    public Point getTransformedPosition(ISlidingData data, float percentage, int x, int y) {
        return null;
    }
}
