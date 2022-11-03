package edu.auburn.cpsc4970.m1;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class SimpleApp
{
   public static void main( String[] args )
   {
   
      int a;
      int b; 
      int sum;
   
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter first number: ");
      a = sc.nextInt();
   
      System.out.print("Enter second number: ");
      b = sc.nextInt();
   
      sum = a + b;
   
      System.out.println("THe sum is: " + sum);
   
      System.out.println( "War Eagle!" );
    
   }

   /** Add method here to add two integers **/
   
   public int add(int a, int b) {
      return a + b;
   }

}
