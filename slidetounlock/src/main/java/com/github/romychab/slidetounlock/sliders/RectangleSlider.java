package com.github.romychab.slidetounlock.sliders;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;

import com.github.romychab.slidetounlock.ISlider;
import com.github.romychab.slidetounlock.ISlidingData;

public class RectangleSlider implements ISlider {

    protected HorizontalSlider mHorizontalSlider = new HorizontalSlider(Direction.BOTH);
    protected VerticalSlider mVerticalSlider = new VerticalSlider(Direction.BOTH);
    protected Point mPoint = new Point();

    @Override
    public float getPercentage(ISlidingData data, int x, int y) {
        float vertical = mVerticalSlider.getPercentage(data, x, y);
        float horizontal = mHorizontalSlider.getPercentage(data, x, y);
        return Math.max(vertical, horizontal);
    }

    @Override
    public boolean allowStart(ISlidingData data) {
        return data.getChildStartRect().contains(data.getStartX(), data.getStartY());
    }

    @Override
    public Point getTransformedPosition(ISlidingData data, float percentage, int x, int y) {
        // no transforms
        mPoint.x = x;
        mPoint.y = y;
        return mPoint;
    }
}
