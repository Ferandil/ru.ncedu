package ru.ncedu.kodratenkov.workwithrefl;

public class MyUtils {
    public static String getSimpleClassName(String fullName){
        if (fullName != null){
            String[] fullPath = fullName.split("\\.");
            return fullPath[fullPath.length - 1];
        }
        return null;
    }

}
