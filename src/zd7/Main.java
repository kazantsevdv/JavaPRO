package zd7;

import zd7.annotation.AfterSuite;
import zd7.annotation.BeforeSuite;
import zd7.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Start(MyTest.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    static void Start(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] method = c.getDeclaredMethods();
        List<Method> list = new ArrayList<>();

        Method before = null;
        int countbefore = 0;
        Method afte = null;
        int countafte = 0;

        for (Method met : method) {

            if (met.isAnnotationPresent(BeforeSuite.class)) {
                if (++countbefore > 1) throw new RuntimeException("Анотация BeforeSuite содержится больше одного раза");
                before = met;
            }
            if (met.isAnnotationPresent(AfterSuite.class)) {
                if (++countafte > 1) throw new RuntimeException("Анотация AfterSuite содержится больше одного раза");
                afte = met;
            }
            if (met.isAnnotationPresent(Test.class)) {
                list.add(met);
            }
        }

        list.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        Object o = c.newInstance();
        if (before != null) {
            before.invoke(o);
        }
        for (Method met : list) {
            met.invoke(o);
        }
        if (afte != null) {
            afte.invoke(o);
        }
    }
}
