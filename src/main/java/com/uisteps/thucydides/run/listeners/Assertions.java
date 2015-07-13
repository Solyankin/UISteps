/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisteps.thucydides.run.listeners;

import net.thucydides.core.annotations.Step;

/**
 *
 * @author ASolyankin
 */
public class Assertions {
    
    boolean flag;
    
    @Step
    public void throwError(String error) {
        throw new RuntimeException(error);
    }
}
