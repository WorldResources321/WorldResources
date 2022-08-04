package com.example.world_resources_app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class NonFunctionalRequirementTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

//    @Test
//    public void testReq1() {
//
//        onView(withId(R.id.quiz_button)).perform(click());
//        onView(withId(R.id.ans_B)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_D)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_A)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_A)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_C)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_B)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_D)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_A)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_C)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withId(R.id.ans_D)).perform(click());
//        onView(withId(R.id.submit_btn)).perform(click());
//        onView(withText("Results")).check(matches(isDisplayed()));
//    }

    @Test
    public void testQuizButton() {
        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.activity_quiz)).check(matches(isDisplayed()));
    }
    @Test
    public void testNewsButton() {
        onView(withId(R.id.news_button)).perform(click());
        onView(withId(R.id.fragment_news)).check(matches(isDisplayed()));
    }
    @Test
    public void testForumButton() {
        onView(withId(R.id.forum_button)).perform(click());
        onView(withId(R.id.activity_forum)).check(matches(isDisplayed()));
    }
    @Test
    public void testMapButton() {
        onView(withId(R.id.maps_button)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

}
