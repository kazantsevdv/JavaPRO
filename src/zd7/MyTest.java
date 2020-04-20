package zd7;

import zd7.annotation.AfterSuite;
import zd7.annotation.BeforeSuite;
import zd7.annotation.Test;

public class MyTest {

    @BeforeSuite
    public void Begin() {
        System.out.println("Begin");
    }

    @AfterSuite
    public void End() {
        System.out.println("End");
    }

    @Test(priority = 1)
    public void Test1() {
        System.out.println("test1");
    }

    @Test(priority = 2)
    public void Test2() {
        System.out.println("test2");
    }

    @Test(priority = 10)
    public void Test3() {
        System.out.println("test3");
    }

    @Test(priority = 5)
    public void Test4() {
        System.out.println("test4");
    }

    @Test(priority = 4)
    public void Test5() {
        System.out.println("test5");
    }
}
