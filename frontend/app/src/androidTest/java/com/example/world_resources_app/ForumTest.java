package com.example.world_resources_app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.uiautomator.UiDevice;

import com.example.world_resources_app.forum.ForumManagement;
import com.example.world_resources_app.news.NewsManagement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ForumTest {


    @Rule
    public ActivityTestRule<ForumManagement> mActivityRule =
            new ActivityTestRule<>(ForumManagement.class);

    @Rule
    public GrantPermissionRule grantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.INTERNET"
            );

    @Test
    public void displayTest() {
        onView(withId(R.id.make_post_button)).check(matches(isDisplayed()));
        onView(withId(R.id.text1)).check(matches(isDisplayed()));
    }




}
