package com.github.romychab.slidetounlock;

/*
 * Created by rom on 15.12.16.
 */

import android.graphics.Rect;

public interface ISlidingData {

    /**
     * Get touch down X pos
     */
    int getStartX();

    /**
     * Get touch down Y pos
     */
    int getStartY();

    /**
     * Get child view's rectangle when sliding was started
     */
    Rect getChildStartRect();

    /**
     * Get SlideLayout size
     */
    Dimen getParentDimen();

    /**
     * Notify SlideLayout's listeners that the progress was changed
     */
    void publishOnSlideChanged(float percentage);
}
