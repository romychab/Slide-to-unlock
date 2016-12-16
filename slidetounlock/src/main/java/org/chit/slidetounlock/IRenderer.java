package org.chit.slidetounlock;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;
import android.view.View;

public interface IRenderer {

    void renderChanges(ISlidingData slidingData, View child, Point transformed);

    void onReset(ISlidingData slidingData, View child);

    void onDone(ISlidingData slidingData, View child);

}
