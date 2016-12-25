package com.github.romychab.slidetounlock;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;
import android.view.View;

/**
 * Used to update the sliders
 */
public interface IRenderer {

    /**
     * Update slider e.g. apply transformations, animations etc.
     * @param slidingData - additional slider's data
     * @param child - view inside slider used to indicate the progress
     * @param percentage - current progress (from 0 to 1)
     * @param transformed - x,y pos transformed by ISlider.
     */
    void renderChanges(ISlidingData slidingData, View child, float percentage, Point transformed);

    /**
     * Reset slider to start state.
     * @return animation's duration in ms
     */
    int onSlideReset(ISlidingData slidingData, View child);

    /**
     * Called when user slides completely
     * @return animation's duration in ms
     */
    int onSlideDone(ISlidingData slidingData, View child);

    /**
     * Reset slider to start state when user finishes touches on slider but doesn't complete sliding
     * @return animation's duraion in ms
     */
    int onSlideCancelled(ISlidingData slidingData, View child, float lastPercentage);

    /**
     * Cancel all animations
     */
    void cancel();
}
