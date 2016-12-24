package org.chit.slidetounlock;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;
import android.view.View;

public interface IRenderer {

    void renderChanges(ISlidingData slidingData, View child, float percentage, Point transformed);

    int onSlideReset(ISlidingData slidingData, View child);

    int onSlideDone(ISlidingData slidingData, View child);

    int onSlideCancelled(ISlidingData slidingData, View child, float lastPercentage);

}
