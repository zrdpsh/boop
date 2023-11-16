import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class calculating_pi {
	public static void main(String[] args) {
		double initial_value = 4;
		Scanner input = new Scanner(System.in);

		System.out.println("Насколько точным должно быть значение пи? Целое число: ");
		int accuracy = input.nextInt();

		for (int i = 1; i < accuracy; i++) {
			initial_value += ((4.0/(1+2*i))*(Math.pow(-1, i)));
			System.out.println(initial_value);
		} //for loop
		
		System.out.println("Получилось " + initial_value);
	} //public static main void
} //calculating PI class
