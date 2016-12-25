package com.github.romychab.slidetounlock.sliders;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;

import com.github.romychab.slidetounlock.ISlider;
import com.github.romychab.slidetounlock.ISlidingData;

public class HorizontalSlider implements ISlider {

    private Point mPoint = new Point();
    private Direction mDirection;

    public HorizontalSlider(Direction direction) {
        mDirection = direction;
    }

    public HorizontalSlider() {
        this(Direction.FORWARD);
    }


    @Override
    public float getPercentage(ISlidingData data, int x, int y) {
        int leftBound = getLeftBound(data);
        int rightBound = getRightBound(data);
        float forward = (x - data.getStartX()) / (float)(rightBound - data.getStartX());
        float inverse = (data.getStartX() - x) / (float)(data.getStartX() - leftBound);
        switch (mDirection) {
            case FORWARD:
                return forward;
            case INVERSE:
                return inverse;
            default:
                return forward > 0 ? forward : inverse;
        }
    }

    @Override
    public boolean allowStart(ISlidingData data) {
        return data.getChildStartRect().contains(data.getStartX(), data.getStartY());
    }

    @Override
    public Point getTransformedPosition(ISlidingData data, float percentage, int x, int y) {
        switch (mDirection) {
            case FORWARD:
                x = x > data.getStartX() ? x : data.getStartX();
                break;
            case INVERSE:
                x = x < data.getStartX() ? x : data.getStartX();
                break;
        }
        mPoint.set(x, data.getStartY());
        return mPoint;
    }

    private int getLeftBound(ISlidingData data) {
        return data.getStartX() - data.getChildStartRect().left;
    }

    private int getRightBound(ISlidingData data) {
        return data.getParentDimen().width - (data.getChildStartRect().right - data.getStartX());
    }

}
