package com.example.world_resources_app;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.example.world_resources_app.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;


import android.Manifest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.example.world_resources_app.news.NewsManagement;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewsTest {

    @Rule
    public ActivityTestRule<NewsManagement> mActivityRule =
            new ActivityTestRule<>(NewsManagement.class);

    @Rule
    public GrantPermissionRule grantPermissionRule =
            GrantPermissionRule.grant(Manifest.permission.INTERNET);

    @Test
    public void defaultDisplayTest() throws InterruptedException {
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(1, R.id.text_view_news_description)).check(matches(withText(containsString("oil"))));
        Thread.sleep(1000);
    }

    @Test
    public void spinnerCoalSelectionTest() throws InterruptedException {
        onView(withId(R.id.spinner_category)).perform(scrollTo()).perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(1, R.id.text_view_news_description)).check(matches(withText(containsString("coal"))));
    }

    @Test
    public void spinnerGasSelectionTest() throws InterruptedException {
        onView(withId(R.id.spinner_category)).perform(scrollTo()).perform(click());
        onData(anything()).atPosition(3).perform(click());
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(1, R.id.text_view_news_description)).check(matches(withText(containsString("gas"))));
    }

    @Test
    public void checkView() throws InterruptedException {
        onView(withId(R.id.spinner_category)).perform(scrollTo()).perform(click());
        onData(anything()).atPosition(3).perform(click());
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(1, R.id.text_view_news_description)).perform(click());
        onView(withId(R.id.save)).check(matches(isDisplayed()));
    }

  



}
