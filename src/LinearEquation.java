/**
 * This class represents a linear equation object constructed from two points
 *
 * @author Matthew Rotondi
 */
public class LinearEquation {
    /** The x coordinate of the first point on the line */
    private int x1;
    /** The x coordinate of the second point on the line */
    private int x2;
    /** The y coordinate of the first point on the line */
    private int y1;
    /** The y coordinate of the second point on the line */
    private int y2;

    /**
     * Instantiates a new linear equation object from four integer coordinates
     * @param x1 The x coordinate of the first point on the line
     * @param y1 The y coordinate of the first point on the line
     * @param x2 The x coordinate of the second point on the line
     * @param y2 The y coordinate of the second point on the line
     */
    public LinearEquation(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * The distance the two points are away from each other on the line
     * @return A double representing the distance from the two points based on the coordinates the user entered, rounded to the nearest hundredth
     */
    public double distance() {
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        double result = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        return roundToNPlaces(result, 2);
    }

    /**
     * The y intercept of the line
     * @return A double representing the y intercept of the line, rounded to the nearest hundredth
     */
    public double yIntercept() {
        double result = y1 - slope() * x1;
        if (x1 == 0)
            result = y1;
        if (x2 == 0)
            result = y2;
        return roundToNPlaces(result, 2);
    }

    /**
     * The slope of the line
     * @return A double representing the slope of the line, rounded to the nearest hundredth
     */
    public double slope() {
        double result = (double) (y2 - y1) / (x2 - x1);
        return roundToNPlaces(result, 2);
    }

    /**
     * The equation of the line
     * @return A String representing the equation of the line.
     * <p>
     * If the line is horizontal, the output is in the form "y = b", where b is the y intercept
     * <p>
     * If the line is vertical, the output is in the form "x = a", where a is the x value
     * <p>
     * Otherwise, the output is in the form "y = mx + b", where m is the slope as a fraction in simplest form and b is the y intercept. If b is negative, the plus sign changes to a minus sign. If b is 0, the output is in the form "y = mx"
     */
    public String equation() {
        //Horizontal line case
        if (slope() == 0)
            return "y = " + yIntercept();
        //Vertical line case
        if (x1 == x2)
            return "x = " + x1;


        return "y = " + formatFraction() + "x " + formatYIntercept();
    }

    /**
     * Returns a coordinate representing a third point on the line for a given x value
     * @param x The x value for which to determine the coordinate
     * @return A String of the form (x, y), where x is the parameter and y is the corresponding y value rounded to the nearest hundredth
     */
    public String coordinateForX(double x) {
        double y = slope() * x + yIntercept();
        y =  roundToNPlaces(y, 2);


        return "(" + x + ", " + y + ")";
    }

    /**
     * Outputs a string representing all of the information about this line
     * @return A string with information on the line, in the following form:
     * <p>
     * Line 1: "The two points are: (x1, y1) and (x2, y2)" where x1, y1, x2, and y2 are the corresponding attributes
     * <p>
     * Line 2 (prints only if the two points provided are not the same, ie, x1 != x2 || y1 != y2): "The equation of the line between these points is: [equation]", where [equation] is the return value of equation()
     * <p>
     * Line 3 (prints only if the two x coordinates are not the same, ie, x1 != x2): "The y-intercept of this line is: b", where b is the return value of yIntercept()
     * <p>
     * Line 4 (prints only if the two x coordinates are not the same, ie, x1 != x2): "The slope of this line is: m", where m is the return value of slope()
     * <p>
     * Line 5: "The distance between these two points is: d", where d is the return value of distance()
     */
    public String lineInfo() {
        String output = "The two points are: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")\n";

        if (x1 != x2 || y1 != y2)
            output += "The equation of the line between these points is: " + equation() + "\n";


        if (x1 != x2) {
            output += "The y-intercept of this line is: " + yIntercept() + "\n";
            output += "The slope of this line is: " + slope() + "\n";
        }

        output += "The distance between these two points is: " + distance();

        return output;
    }

    /**
     * Private helper method. Returns the parameter rounded to the nearest n places.
     * @param num The number to round
     * @param n The number of places to round to
     * @return num rounded to n places (ex: roundToNPlaces(4.78291, 2) returns 4.78)
     */
    private double roundToNPlaces(double num, int n) {
        return Math.round(num * Math.pow(10, n)) / Math.pow(10, n);
    }

    /**
     * Private helper method. Outputs the slope as a fraction in simplest form
     * @return A string representing the slope as a fraction in simplest form
     * <p>
     * If the slope is 1, returns the empty string
     * <p>
     * If the slope is -1, returns "-"
     * <p>
     * If the slope is another integer, return a string containing that integer
     * <p>
     * Otherwise, return the slope in the form "numerator/denominator", fully simplified
     */
    private String formatFraction() {
        int numerator = y2 - y1;
        int denominator = x2 - x1;

        //If fraction reduces to an integer
        if (numerator % denominator == 0) {
            int slope = numerator / denominator;
            //If slope is 1, don't return anything
            if (slope == 1)
                return "";
                //if slope is -1, just return the negative sign
            else if (slope == -1)
                return "-";
                //otherwise, return the slope.
            else
                return "" + slope;
        }

        //Reducing fraction
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        return numerator + "/" + denominator;
    }

    /**
     * Private helper method. Returns the greatest common divisor between a and b (used in formatFraction() to simplify the fraction)
     * @param a The first number
     * @param b The second number
     * @return The gcd of a and b. (ex, gcd(6, 18) returns 6)
     */
    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    /**
     * Private helper method. Returns the y intercept after some formatting.
     * @return A string containing the sign of and the y intercept based on the following rules:
     * <p>
     * If the y intercept is 0, returns the empty string
     * <p>
     * If the y intercept is negative, return a string in the form "- b", where b is the absolute value of the intercept
     * <p>
     * Otherwise, return a string in the form "+ b" where y is the intercept
     */
    private String formatYIntercept() {
        double intercept = yIntercept();

        //If intercept is 0, don't return anything
        if (intercept == 0)
            return "";
            //If intercept is negative, change to subtraction sign
        else if (intercept < 0)
            return "- " + Math.abs(intercept);
            //otherwise, if it's positive, return as normal
        else
            return "+ " + intercept;
    }
}
