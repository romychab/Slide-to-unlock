package org.chit.slidetounlock;

/*
 * Created by rom on 15.12.16.
 */

public interface ISlider {

    float getPercentage(ISlidingData data);

    boolean allowStart(ISlidingData data);

}
