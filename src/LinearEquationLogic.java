import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;


public class LinearEquationLogic {
    private static Scanner scanner = new Scanner(System.in);
    private static String coordinate1 = "";
    private static String coordinate2 = "";
    private static LinearEquation linearEquation = null;
    private static boolean running = true;


    public static void run() {
        System.out.println("Welcome to the linear equation calculator 5971!");
        while (running) {
            System.out.println("Please enter two cartesian coordinates pairs with integer coordinates, e.g. (1, 2)");
            initializeCoordinates();


            createLinearEquationObject();


            System.out.println("\n-------INFO-------");
            System.out.println(linearEquation.lineInfo());
            System.out.println();


            if (parseX(coordinate1) != parseX(coordinate2)) {
                findCorrespondingXCoordinate();
                System.out.println();
            }


            System.out.print("Would you like to enter another two points? (y/n): ");
            running = scanner.nextLine().equalsIgnoreCase("y");
            System.out.println();
        }
        System.out.println("Thank you for using the linear equation calculator 5971!");
    }


    private static void initializeCoordinates() {
        System.out.print("Enter your first coordinate pair: ");
        coordinate1 = scanner.nextLine();
        System.out.print("Enter your second coordinate pair: ");
        coordinate2 = scanner.nextLine();
    }


    private static void createLinearEquationObject() {
        int x1 = parseX(coordinate1);
        int y1 = parseY(coordinate1);
        int x2 = parseX(coordinate2);
        int y2 = parseY(coordinate2);


        linearEquation = new LinearEquation(x1, y1, x2, y2);
    }


    private static void findCorrespondingXCoordinate() {
        System.out.print("Enter in an x-coordinate to find its corresponding y-coordinate: ");
        try {
            double x = scanner.nextDouble();
            System.out.println("The coordinate for x = " + x + " is " + linearEquation.coordinateForX(x));
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid x-value, moving on...");
        }
        scanner.nextLine();
    }


    private static int parseX(String coordinate) {
        int commaIdx = coordinate.indexOf(',');
        String x = coordinate.substring(1, commaIdx);
        return Integer.parseInt(x);
    }


    private static int parseY(String coordinate) {
        int commaIdx = coordinate.indexOf(',');
        String y = coordinate.substring(commaIdx + 2, coordinate.length() - 1);
        return Integer.parseInt(y);
    }
}
