package org.chit.slidetounlock.example;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.chit.slidetounlock.ISlideChangeListener;
import org.chit.slidetounlock.ISlideListener;
import org.chit.slidetounlock.SlideLayout;
import org.chit.slidetounlock.example.databinding.ActivityMainBinding;
import org.chit.slidetounlock.renderers.ScaleRenderer;
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

        setupSlider1();
        setupSlider2();
        setupSlider3();
        setupSlider4();
    }

    private void setupSlider1() {
        mBinding.slide1.setRenderer(new ScaleRenderer());
        mBinding.slide1.setSlider(new HorizontalSlider());
        mBinding.slide1.addSlideListener(new ISlideListener() {
            @Override
            public void onSlideDone(SlideLayout slider, boolean done) {
                if (done) {
                    slider.reset();
                }
            }
        });
    }

    private void setupSlider2() {
        mBinding.slide2.setRenderer(new ScaleRenderer());
        mBinding.slide2.setSlider(new HorizontalSlider());
        mBinding.slide2.setChildId(R.id.slide_child);
        mBinding.slide2.setThreshold(0.9f);
        mBinding.slide2.addSlideChangeListener(new ISlideChangeListener() {
            @Override
            public void onSlideStart(SlideLayout slider) { ; }

            @Override
            public void onSlideChanged(SlideLayout slider, float percentage) {
                mBinding.txtText.setAlpha(1 - percentage);
            }

            @Override
            public void onSlideFinished(SlideLayout slider, boolean done) {
                if (done) {
                    slider.reset();
                }
            }
        });
    }

    private void setupSlider3() {
        mBinding.slide3.setRenderer(new TranslateRenderer());
        mBinding.slide3.setSlider(new VerticalSlider(Direction.INVERSE));
        mBinding.slide3.setChildId(R.id.slide_child_3);
        mBinding.slide3.addSlideChangeListener(new ISlideChangeListener() {
            @Override
            public void onSlideStart(SlideLayout slider) { ; }

            @Override
            public void onSlideChanged(SlideLayout slider, float percentage) {
                Log.d("TAG", "p = " + percentage);
                mBinding.upArrow.setAlpha(1 - percentage);
                mBinding.upArrow.setScaleX(1 - percentage);
                mBinding.upArrow.setScaleY(1 - percentage);
                mBinding.upArrow.setTranslationY(-slider.getHeight() * percentage / 3);
            }

            @Override
            public void onSlideFinished(SlideLayout slider, boolean done) {
                if (done) {
                    slider.reset();
                }
            }
        });
    }

    private void setupSlider4() {
        mBinding.slide4.setRenderer(new TranslateRenderer());
        mBinding.slide4.setSlider(new RadialSlider());
        mBinding.slide4.setChildId(R.id.slide_child_4);
        mBinding.slide4.addSlideListener(new ISlideListener() {
            @Override
            public void onSlideDone(SlideLayout slider, boolean done) {
                if (done) {
                    slider.reset();
                }
            }
        });
    }
}
