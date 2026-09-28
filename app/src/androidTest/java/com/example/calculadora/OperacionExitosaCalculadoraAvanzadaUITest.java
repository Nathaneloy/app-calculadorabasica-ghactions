package com.example.calculadora;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class OperacionExitosaCalculadoraAvanzadaUITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testEntrarACalculadoraAvanzadaYSumar() {
        onView(withId(R.id.btnAvanzada)).perform(click());

        onView(withId(R.id.btn4)).perform(click());
        onView(withId(R.id.btnMas)).perform(click());
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());
        onView(withId(R.id.txResultado)).check(matches(withText("9")));
    }
}
