/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulator.launcher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Emerio
 */
public class PasswordGenerator {

    public static void main(String args[]) throws NoSuchAlgorithmException {
        int count = 1000;
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (int i =1; i <= count; i++) {
            String password = "password_" + String.valueOf(i);
            
            System.out.println(password.getBytes().toString());
        }
    }
}
