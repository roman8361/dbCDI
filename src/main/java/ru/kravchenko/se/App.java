package ru.kravchenko.se;

import javax.enterprise.inject.se.SeContainerInitializer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        SeContainerInitializer.newInstance().addPackages(App.class).initialize();
    }

}
