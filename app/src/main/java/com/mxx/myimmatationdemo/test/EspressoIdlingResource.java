package com.mxx.myimmatationdemo.test;

import android.support.test.espresso.IdlingResource;

import me.yokeyword.fragmentation.SupportActivity;

public class EspressoIdlingResource extends SupportActivity {
    private static final String RESOURCE = "GLOBAL";

    private static SimpleCountingIdlingResource mCountingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }
}
