# Slide-to-unlock
Slide-to-unlock layout for Android

![test](https://github.com/romychab/Slide-to-unlock/blob/master/screencast.gif)

### Usage:

Layout:

```xml
<com.github.romychab.slidetounlock.SlideLayout
    android:id="@+id/slider1"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:background="@drawable/horizontal_back">

    <FrameLayout
        android:layout_width="60dp"
        android:layout_height="match_parent">

        <View
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="1px"
            android:background="@drawable/button_bg" />
        <ImageView
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:layout_marginRight="6dp"
            android:layout_gravity="center_vertical|right"
            android:src="@drawable/ic_slide"/>

    </FrameLayout>

</com.github.romychab.SlideLayout>
```

Code:

```java
SlideLayout slider = (SliderLayout) findViewById(R.id.slider1);
slider.setRenderer(new ScaleRenderer());
slider.setSlider(new HorizontalSlider());
slider.addSlideListener(new ISlideListener() {
  @Override
    public void onSlideDone(SlideLayout slider, boolean done) {
        if (done) {
            // restore start state
            slider.reset();
        }
    }
});
```

Additional methods:
- setRenderer() - sets the way of the SlideLayout updating (now available: ScaleRenderer and TranslateRenderer)
- setSlider() - sets the slider for SlideLayout. There are HorizontalSlider, VerticalSlider, RadialSlider and RectangleSlider.

See the source code of 'app' module for usage details.
