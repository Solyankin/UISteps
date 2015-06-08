package com.uisteps.thucydides.run.listeners;

import com.uisteps.core.verify.Verify;
import com.uisteps.thucydides.ThucydidesUtils;
import java.util.Map;
import net.thucydides.core.model.DataTable;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;
import net.thucydides.core.webdriver.WebdriverInstances;

/**
 *
 * @author m.prytkova
 */
public class ThucydidesStepListener implements StepListener {
    
    @Override
    public void testSuiteStarted(Class<?> storyClass) {
    }

    @Override
    public void testSuiteStarted(Story story) {
    }

    @Override
    public void testSuiteFinished() {
        closeAllBrowser();
    }

    @Override
    public void testStarted(String description) {
    }

    @Override
    public void testFinished(TestOutcome result) {
        closeAllBrowser();
    }
    
    @Override
    public void testRetried() {
    }

    @Override
    public void stepStarted(ExecutedStepDescription description) {
    }

    @Override
    public void skippedStepStarted(ExecutedStepDescription description) {
    }

    @Override
    public void stepFailed(StepFailure failure) {
    }

    @Override
    public void lastStepFailed(StepFailure failure) {
    }

    @Override
    public void stepIgnored() {
    }

    @Override
    public void stepPending() {
    }

    @Override
    public void stepPending(String message) {
    }

    @Override
    public void stepFinished() {
    }

    @Override
    public void testFailed(TestOutcome testOutcome, Throwable cause) {
        closeAllBrowser();
    }

    @Override
    public void testIgnored() {
    }

    @Override
    public void testSkipped() {
    }

    @Override
    public void testPending() {
    }

    @Override
    public void notifyScreenChange() {
    }

    @Override
    public void useExamplesFrom(DataTable table) {
    }

    @Override
    public void exampleStarted(Map<String, String> data) {
    }

    @Override
    public void exampleFinished() {
        closeAllBrowser();
    }

    @Override
    public void assumptionViolated(String message) {
    }
    
    protected void closeAllBrowser() {
        WebdriverInstances drivers = ThucydidesUtils.getDrivers();
        drivers.closeAllDrivers();
    }

}
