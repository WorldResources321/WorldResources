package com.example.world_resources_app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testQuizButton() {

        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.question)).check(matches(withText("Which country produces the most fossil fuels?")));
        onView(withId(R.id.ans_A)).check(matches(withText("Canada")));
        onView(withId(R.id.ans_B)).check(matches(withText("USA")));
        onView(withId(R.id.ans_C)).check(matches(withText("Russia")));
        onView(withId(R.id.ans_D)).check(matches(withText("Iran")));
        onView(withId(R.id.submit_btn)).check(matches(withText("Submit")));

    }

    @Test
    public void testNoAnswerSubmit() {
        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.question)).check(matches(withText("Which country produces the most fossil fuels?")));
    }

    @Test
    public void testSubmitFirstQuestion() {
        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.question)).check(matches(withText("Which US state is the biggest contributor of fossil fuels?")));
    }

    @Test
    public void testRestart() {

        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.question)).check(matches(withText("Which country produces the most fossil fuels?")));
    }

    @Test
    public void testAllCorrect() {

        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_D)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_C)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_D)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_C)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_D)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withText("Score is 10 out of 10")).check(matches(isDisplayed()));
    }

    @Test
    public void testAllIncorrect() {

        onView(withId(R.id.quiz_button)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_B)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withId(R.id.ans_A)).perform(click());
        onView(withId(R.id.submit_btn)).perform(click());
        onView(withText("Score is 0 out of 10")).check(matches(isDisplayed()));
    }
}
