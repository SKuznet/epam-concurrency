package com.epam.concurrency.hw2;

import com.sun.deploy.util.ReflectionUtil;

import java.lang.reflect.Field;

public class General {

    public static void main(String[] args) {
        Test test = new Test();
        try {
            Class<? extends Test> testClass = test.getClass();
            Field textField = testClass.getDeclaredField("text");
//            System.out.println(textField);
            System.out.println(textField.isAccessible());
            textField.setAccessible(true);
            System.out.println(textField.isAccessible());
            textField.setInt(test, 3);
            Thread.sleep(100);
            System.out.println(new Test());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
