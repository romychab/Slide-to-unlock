package com.github.romychab.slidetounlock;

/*
 * Created by rom on 15.12.16.
 */

import android.graphics.Point;

public interface ISlider {

    /**
     * Get current progress of sliding
     * @param x - x coordinate of user touch event
     * @param y - y coordinate of user touch event
     * @return - value in range [0; 1]
     */
    float getPercentage(ISlidingData data, int x, int y);

    /**
     * Check can user start the sliding or not
     */
    boolean allowStart(ISlidingData data);

    /**
     * Create transformed touch point of user that will be delivered to IRenderer
     */
    Point getTransformedPosition(ISlidingData data, float percentage, int x, int y);

}
