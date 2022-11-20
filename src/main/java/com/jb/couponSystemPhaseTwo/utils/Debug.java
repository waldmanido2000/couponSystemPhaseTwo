package com.jb.couponSystemPhaseTwo.utils;

import java.lang.reflect.Field;

public class Debug {
    private static final boolean SHOW_CALLING_CLASS = false; // show / hide the calling class of each method
    private static final boolean TEST_DEBUG_MODE = false; // show / hide Test method tracking
    private static final boolean JOB_DEBUG_MODE = true; // show / hide daily job method tracking
    private static final boolean DB_MANAGER_DEBUG_MODE = true; //show / hide database setup by DatabaseManager methods


    public static void showDescription(String flagName, String description) {
        try {
            Field field = Debug.class.getDeclaredField(flagName);
            boolean descriptionInvoke = field.getBoolean(field);
            if (descriptionInvoke) {
                String message = description;
                if (SHOW_CALLING_CLASS) {
                    StackTraceElement[] ste = Thread.currentThread().getStackTrace();
                    String callingClass = ste[2].getFileName();
                    assert callingClass != null;
                    callingClass = callingClass.replace(".java", "\t");
                    message = ("--- called by: " + callingClass + message);
                }
                System.out.println(message);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
