package org.chit.slidetounlock.example;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.chit.slidetounlock.example.databinding.ActivityMainBinding;
import org.chit.slidetounlock.renderers.TranslateRenderer;
import org.chit.slidetounlock.sliders.Direction;
import org.chit.slidetounlock.sliders.HorizontalSlider;
import org.chit.slidetounlock.sliders.RadialSlider;
import org.chit.slidetounlock.sliders.RectangleSlider;
import org.chit.slidetounlock.sliders.VerticalSlider;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.slideLayout1.setRenderer(new TranslateRenderer());
        mBinding.slideLayout1.setSlider(new HorizontalSlider(Direction.BOTH));

        mBinding.slideLayout2.setRenderer(new TranslateRenderer());
        mBinding.slideLayout2.setSlider(new VerticalSlider(Direction.FORWARD));

        mBinding.slideLayout3.setRenderer(new TranslateRenderer());
        mBinding.slideLayout3.setSlider(new RadialSlider());
    }
}
