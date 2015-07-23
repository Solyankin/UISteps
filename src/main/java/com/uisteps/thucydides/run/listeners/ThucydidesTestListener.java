/*
 * Copyright 2015 A.Solyankin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uisteps.thucydides.run.listeners;

import com.uisteps.thucydides.ThucydidesUtils;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Stack;
import net.thucydides.core.model.DataTable;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.model.TestStep;
import net.thucydides.core.steps.BaseStepListener;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;
import net.thucydides.core.webdriver.WebdriverInstances;
import com.uisteps.thucydides.run.ThucydidesTest;

/**
 *
 * @author A.Solyankin
 * @param <T>
 */
public class ThucydidesTestListener <T extends ThucydidesTest> implements StepListener{
    
    private T test;
    
    public T getTest() {
        return test;
    }

    public void setTest(T test) {
        this.test = test;
    }
    
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
        executeVerification();
    }

    @Override
    public void skippedStepStarted(ExecutedStepDescription description) {
    }

    public Stack<TestStep> getStepStack() {
        String fieldName = "currentStepStack";

        try {
            Field field = BaseStepListener.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (Stack<TestStep>) field.get(ThucydidesUtils.getBaseStepListener());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException ex) {
            throw new RuntimeException("Cannot get field by name " + fieldName + " in class " + WebdriverInstances.class + "!\nCause: " + ex);
        }
    }
    
    boolean testFailed;
    
    @Override
    public void stepFailed(StepFailure failure) {
        testFailed = true;
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
        executeVerification();
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

    protected void executeVerification() {
        if (!test.verify().complited()) {
            test.verify().result();
        }
    }

}
