package com.example.tiptime

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.containsString

@RunWith(AndroidJUnit4::class)
class CalculatorTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip(){
        onView(withId(R.id.plain_text_input))
            .perform(typeText("50.00"))

        onView(withId(R.id.btn_cal)).perform(click())

        onView(withId(R.id.tip))
            .check(matches(withText(containsString("10.00"))))
    }
}