package org.chit.slidetounlock;

/*
 * Created by rom on 15.12.16.
 */

import android.graphics.Point;

public interface ISlider {

    float getPercentage(ISlidingData data, int x, int y);

    boolean allowStart(ISlidingData data);

    Point getTransformedPosition(float percentage, int x, int y);

}
