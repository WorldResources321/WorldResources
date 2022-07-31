package com.example.world_resources_app;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.uiautomator.UiDevice;

import com.example.world_resources_app.news.NewsManagement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ForumTest {


    @Rule
    public ActivityTestRule<NewsManagement> mActivityRule =
            new ActivityTestRule<>(NewsManagement.class);

    @Rule
    public GrantPermissionRule grantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.INTERNET"
            );

    @Before
    public void before() throws Exception {
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void displayTest() {

        //ViewInteraction coalView = onView()

    }

    /*
    @Test
    public void testNoAnswerSubmit() {
        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.question)).check(matches(withText("Which country produces the most fossil fuels?")));
    }
    */


}
