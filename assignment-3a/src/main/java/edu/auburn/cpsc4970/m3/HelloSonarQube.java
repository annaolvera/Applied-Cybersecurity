package edu.auburn.cpsc4970.m3;

import java.util.logging.Logger;

/**
 * Simple Java Application
 *
 */
public class HelloSonarQube
{

    public static final String VALUE_PREFIX = "Value = ";
    public static final String LENGTH_PREFIX = "Length = ";

    public static void main( String[] args )
    {
        Logger.getLogger( "Hello World!" );

        HelloSonarQube helloSonarQube = new HelloSonarQube();
        helloSonarQube.printValue(String.valueOf(helloSonarQube));
    }


    private void printValue(String value) {


       Logger.getLogger(VALUE_PREFIX +value+ LENGTH_PREFIX +value.length());

    }
}
