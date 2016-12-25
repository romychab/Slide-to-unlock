package com.github.romychab.slidetounlock.sliders;

/*
 * Created by rom on 16.12.16.
 */

import android.graphics.Point;

import com.github.romychab.slidetounlock.ISlider;
import com.github.romychab.slidetounlock.ISlidingData;

public class VerticalSlider implements ISlider {

    private Point mPoint = new Point();
    private Direction mDirection;

    public VerticalSlider(Direction direction) {
        mDirection = direction;
    }

    public VerticalSlider() {
        this(Direction.FORWARD);
    }


    @Override
    public float getPercentage(ISlidingData data, int x, int y) {
        int topBound = getTopBound(data);
        int bottomBound = getBottomBound(data);
        float forward = (y - data.getStartY()) / (float)(bottomBound - data.getStartY());
        float inverse = (data.getStartY() - y) / (float)(data.getStartY() - topBound);
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
                y = y > data.getStartY() ? y : data.getStartY();
                break;
            case INVERSE:
                y = y < data.getStartY() ? y : data.getStartY();
                break;
        }
        mPoint.set(data.getStartX(), y);
        return mPoint;
    }

    private int getTopBound(ISlidingData data) {
        return data.getStartY() - data.getChildStartRect().top;
    }

    private int getBottomBound(ISlidingData data) {
        return data.getParentDimen().height - (data.getChildStartRect().bottom - data.getStartY());
    }
}
