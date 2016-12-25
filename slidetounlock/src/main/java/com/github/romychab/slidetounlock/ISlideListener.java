package com.github.romychab.slidetounlock;

/*
 * Created by rom on 15.12.16.
 */

public interface ISlideListener {

    /**
     * Called when user have done or interrupted the sliding
     * @param done - true if the sliding is done
     */
    void onSlideDone(SlideLayout slider, boolean done);

}
