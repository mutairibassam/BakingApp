package com.example.android.bakingapp;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewMainActivityTest {

    // Rule provide functional testing for a specific single activity
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    // @test where and what we testing in
    @Test
    public void test_isActivityInView() {
        // 1- find the view
        // 2- perform action on the view
        // 3- check if the view does what you expected
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void test_MainActivity_Visibility() {
        onView(withId(R.id.recycler_view_id)).check(matches(isDisplayed()));
    }
}
