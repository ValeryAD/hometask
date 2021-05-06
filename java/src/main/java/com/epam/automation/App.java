package com.epam.automation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new App().foo();
    }

    private void foo() {
        System.out.println(Thread.currentThread().getContextClassLoader().getResourceAsStream("data/prices.properties"));
    }
}
