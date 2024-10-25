public class LinearEquation {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public LinearEquation(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double distance() {
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        double result = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));


        return roundToNPlaces(result, 2);
    }

    public double yIntercept() {
        double result = y1 - slope() * x1;
        if (x1 == 0)
            result = y1;
        if (x2 == 0)
            result = y2;
        return roundToNPlaces(result, 2);
    }

    public double slope() {
        double result = (double) (y2 - y1) / (x2 - x1);
        return roundToNPlaces(result, 2);
    }

    public String equation() {
        //Horizontal line case
        if (slope() == 0)
            return "y = " + yIntercept();
        //Vertical line case
        if (x1 == x2)
            return "x = " + x1;


        return "y = " + formatFraction() + "x " + formatYIntercept();
    }

    public String coordinateForX(double x) {
        double y = slope() * x + yIntercept();
        y =  roundToNPlaces(y, 2);


        return "(" + x + ", " + y + ")";
    }

    public String lineInfo() {
        String output = "The two points are: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")\n";

        if (x1 != x2 || y1 != y2)
            output += "The equation of the line between these points is: " + equation() + "\n";


        if (x1 != x2) {
            output += "The y-intercept of this line is: " + yIntercept() + "\n";
            output += "The slope of this line is: " + slope() + "\n";
        }

        output += "The distance between these two points is " + distance();

        return output;
    }

    private double roundToNPlaces(double num, int n) {
        return Math.round(num * Math.pow(10, n)) / Math.pow(10, n);
    }

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

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

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
