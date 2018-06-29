package ru.ncedu.kodratenkov.workwithrefl;


import jdk.nashorn.internal.objects.annotations.Constructor;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;

public class ReflectorImpl implements Reflector {

    Class clazz;

    public void setClass(Class clazz){
        this.clazz = clazz;
    }

    public String classPackage() {
        return clazz.getPackage().getName();
    }

    public String classModifaers() {
        return Modifier.toString(clazz.getModifiers());
    }

    public String className() {
        return MyUtils.getSimpleClassName(clazz.getName());
    }

    public String classExtends() {
        String[] fullPath = clazz.getSuperclass().getName().split("\\.");

        return MyUtils.getSimpleClassName(clazz.getSuperclass().getName());
    }

    public ArrayList<String> classImplemets() {
        Class interfaces[] = clazz.getInterfaces();
        ArrayList<String> interfacesNames = new ArrayList<String>();

        for(Class interf : interfaces){
            interfacesNames.add(MyUtils.getSimpleClassName(interf.getName()));
        }

        return interfacesNames;
    }

    public ArrayList<String> classFields() {

        Field classFields[] = clazz.getDeclaredFields();
        ArrayList<String> fields = new ArrayList<String>();

        for(Field field : classFields){
            field.setAccessible(true);
            StringBuilder fieldInString = new StringBuilder();
            Annotation[] annotationsForField = field.getAnnotations();
            for(Annotation annotation : annotationsForField){
                fieldInString.append("@");
                fieldInString.append(annotation.toString());
                fieldInString.append("\\n");
            }
            fieldInString.append(Modifier.toString(field.getModifiers()) + " ");
            if (field.getType().isArray()){
                StringBuffer buf = new StringBuffer(MyUtils.getSimpleClassName(field.getType().toString()));
                buf.deleteCharAt(buf.length() - 1);
                buf.append("[]");
                fieldInString.append(buf.toString() + " ");
            } else{
                fieldInString.append(MyUtils.getSimpleClassName(field.getType().toString()) + " ");
            }

            fieldInString.append(field.getName());
            Object instOfClass = null;
            try {
                instOfClass = clazz.newInstance();
                if(field.get(instOfClass) != null){
                    fieldInString.append(" = " + field.get(instOfClass));
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            fields.add(fieldInString.toString() + ";");
        }
        return fields;
    }

    public ArrayList<String> classMethods() {

        Method classMethods[] = clazz.getDeclaredMethods();
        ArrayList<String> methods = new ArrayList<String>();

        for(Method method : classMethods){
            method.setAccessible(true);
            StringBuilder methodInString = new StringBuilder();
            Annotation[] annotationsForMethod = method.getAnnotations();
            for(Annotation annotation : annotationsForMethod){
                methodInString.append("@");
                methodInString.append(MyUtils.getSimpleClassName(annotation.toString()).replaceFirst("\\(.*\\)", ""));
                methodInString.append("\n");
            }
            methodInString.append(Modifier.toString(method.getModifiers()) + " ");
            methodInString.append(MyUtils.getSimpleClassName(MyUtils.getSimpleClassName(method.getReturnType().getName()) + " "));
            methodInString.append(MyUtils.getSimpleClassName(method.getName().toString()));
            methodInString.append("(");
            Class[] parametrs = method.getParameterTypes();
            if(parametrs.length != 0){
                for(Class par : parametrs){
                    if (par.isArray()){
                        StringBuffer buf = new StringBuffer(MyUtils.getSimpleClassName(par.getName()));
                        buf.deleteCharAt(buf.length() - 1);
                        buf.append("[]");
                        methodInString.append(buf);
                    }else{
                        methodInString.append(MyUtils.getSimpleClassName(par.getName()));
                    }

                    methodInString.append(",");
                }
                methodInString.deleteCharAt(methodInString.length() - 1);
            }
            methodInString.append(") {}");
            methods.add(methodInString.toString());
        }


        return methods;
    }

    public ArrayList<String> classConstruct() {
        java.lang.reflect.Constructor[] classMethods = clazz.getConstructors();
        ArrayList<String> methods = new ArrayList<String>();

        for(Constructor method : classMethods){
            method.setAccessible(true);
            StringBuilder methodInString = new StringBuilder();
            Annotation[] annotationsForMethod = method.getAnnotations();
            for(Annotation annotation : annotationsForMethod){
                methodInString.append("@");
                methodInString.append(MyUtils.getSimpleClassName(annotation.toString()).replace("\\(.*\\)", ""));
                methodInString.append("\n");
            }
            methodInString.append(Modifier.toString(method.getModifiers()) + " ");
            methodInString.append(MyUtils.getSimpleClassName(method.getReturnType().getName()) + " ");
            methodInString.append(MyUtils.getSimpleClassName(method.getName().toString()));
            methodInString.append("(");
            Class[] parametrs = method.getParameterTypes();
            if(parametrs.length != 0){
                for(Class par : parametrs){
                    methodInString.append(MyUtils.getSimpleClassName(par.getName()));
                    methodInString.append(", ");
                }
                methodInString.deleteCharAt(methodInString.length() - 1);
            }
            methodInString.append(") {}");

        }
        return methods;
    }




    public static void main(String args[]){
        ReflectorImpl reflect = new ReflectorImpl();
        reflect.setClass(new Test().getClass());
        reflect.classFields();
        reflect.classMethods();
        System.out.println(reflect.classFields());
    }
}
