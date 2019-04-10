package com.igorvd.chuckfacts.utils.matcher;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Source: https://stackoverflow.com/a/47548625
 */
public class FontSizeMatcher extends TypeSafeMatcher<View> {

    private final float expectedSize;

    public FontSizeMatcher(float expectedSize) {
        super(View.class);
        this.expectedSize = expectedSize;
    }

    @Override
    protected boolean matchesSafely(View target) {
        if (!(target instanceof TextView)) {
            return false;
        }
        TextView targetEditText = (TextView) target;
        return targetEditText.getTextSize() == expectedSize;
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("with fontSize: ");
        description.appendValue(expectedSize);
    }
}