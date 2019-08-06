package calculator.operations;


import calculator.objectDefinitions.Calculator;

import java.util.Scanner;

import static calculator.operations.UnitMeasures.*;

public class Main {
    public static void main(String[] args) {
        Calculator distance = inputExpression();
        try {
            System.out.println("The calculated distance is: " + distance.calculateDistance() + " " + distance.getResultUnitMeasure());
        } catch (ValidationException e) {
            System.out.println(e);
        }

    }

    private static Calculator inputExpression() {
        System.out.print("Write a distance calculation expression (distance formats: mm/cm/dm/m/km): ");
        String expression = userInputString();
        System.out.print("What unit measure should we use? (mm/cm/dm/m/km): ");
        String uM = userInputString();
        UnitMeasures unitMeasure = M;
        switch (uM) {
            case "mm":
                unitMeasure = MM;
                break;
            case "cm":
                unitMeasure = CM;
                break;
            case "dm":
                unitMeasure = DM;
                break;
            case "m":
                unitMeasure = M;
                break;
            case "km":
                unitMeasure = KM;
                break;
        }
        return new Calculator(expression, unitMeasure);
    }

    public static String userInputString() {
        Scanner scan = new Scanner(System.in);
        String value = scan.nextLine();
        return value;
    }
}
