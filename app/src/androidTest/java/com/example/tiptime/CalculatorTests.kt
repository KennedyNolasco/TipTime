package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_textedit))
            .perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("Valor da gorjeta: R\$ 10,00"))))
    }
    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.eighteen_percent)).perform(click())
        onView(withId(R.id.cost_of_service_textedit))
            .perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("Valor da gorjeta: R\$ 9,00"))))
    }
    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.fifteen_percent)).perform(click())
        onView(withId(R.id.cost_of_service_textedit))
            .perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("Valor da gorjeta: R\$ 8,00"))))
    }
    @Test
    fun calculate_round_up() {
        onView(withId(R.id.fifteen_percent)).perform(click())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.cost_of_service_textedit))
            .perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("Valor da gorjeta: R\$ 7,50"))))
    }
}