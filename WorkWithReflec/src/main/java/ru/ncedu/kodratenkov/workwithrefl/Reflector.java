package ru.ncedu.kodratenkov.workwithrefl;

import java.util.ArrayList;

public interface Reflector {
    public String classPackage();
    public String classModifaers();
    public String className();
    public String classExtends();
    public ArrayList<String> classImplemets();
    public ArrayList<String> classFields();
    public ArrayList<String> classConstruct();
    public ArrayList<String> classMethods();
}
