package org.chit.slidetounlock;

/*
 * Created by rom on 15.12.16.
 */

import android.graphics.Rect;

public interface ISlidingData {

    int getStartX();

    int getStartY();

    Rect getChildStartRect();

    Dimen getParentDimen();

}
