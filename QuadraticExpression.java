import java.lang.*;

/**
    The QuadraticExpression class provides several methods that allow manipulation and calculation of a quadratic equation.

    @author Cameron Wickersham
*/
public class QuadraticExpression implements Cloneable {
    private double coeffA; 
    private double coeffB;
    private double coeffC;

    /**
        Default constructor that sets the coefficients of the the quadratic equation to 0.

        @param none
    */
    public QuadraticExpression()
    {
        coeffA = 0;
        coeffB = 0;
        coeffC = 0;
    }

    /**
        Constructs class object if parameters are given.

        @param a
            First coefficient of a quadratic equation to be set to coeffA.
        @param b
            Second coefficient of a quadratic equation to be set to coeffB.
        @param c
            Third coefficient of a quadratic equation to be set to coeffC.
    */
    public QuadraticExpression(double a, double b, double c)
    {
        coeffA = a;
        coeffB = b;
        coeffC = c;
    }

    /**
        Overrides toString method to display Object.

        @param none
        @return
            expression converted to String.
    */
    public String toString()
    {
        String equation = "";

        if (coeffA != 0)
            equation = equation + coeffA + "x^2";
        if (coeffB != 0)
            equation = equation + " + " + coeffB + "x";
        if (coeffC == 0 && equation == "")
            return "0";
        else if (coeffC !=0)
            equation = equation + " + " + coeffC;

        return equation;
    }

    /**
        Calculates the quadratic equation with the given parameter x.

        @param x
            Acts as x variable of a quadratic equation.
        @return
            Calculated expression from variable x plugged into quadratic equation.
    */
    public double evaluate(double x)
    {
        return coeffA * x * x + coeffB * x + coeffC;
    }

    /**
        Sets coeffA to a new variable passed with method call.

        @param newA
            A new number to be set to coeffA.
    */
    public void setA(double newA)
    {
        coeffA = newA;
    }

    /**
        Sets coeffB to a new variable passed with method call.

        @param newB
            A new number to be set to coeffB.
    */
    public void setB(double newB)
    {
        coeffB = newB;
    }

    /**
        Sets coeffC to a new variable passed with method call.

        @param newC
            A new number to be set to coeffC.
    */
    public void setC(double newC)
    {
        coeffC = newC;
    }

    /**
        Multiplies the QuadraticExpression object by a constant number r.

        @param r
            An integer to be multiplied to the components of the object q.
        @param q 
            A QuadraticExpression object.
        @return
            Returns a new QuadraticExpression object that is multiplied by a given scalar.
    */
    public static QuadraticExpression scale(double r, QuadraticExpression q)
    {
        double newA = q.coeffA * r;
        double newB = q.coeffB * r;
        double newC = q.coeffC * r;

        QuadraticExpression newObject = new QuadraticExpression(newA, newB, newC);
        return newObject;
    }

    /**
        Calculates the amount of roots from called QuadraticExpression object.

        @param none
        @return
            amount of real roots.
    */
    public int numberOfRoots()
    {
        if (coeffA == 0 && coeffB == 0 && coeffC == 0)
            return 3;
        else if (coeffA == 0 && coeffB == 0 && coeffC != 0)
            return 0;
        else if (coeffA == 0 && coeffB != 0 && coeffC != 0)
            return 1;
        else if (coeffA != 0 && coeffB == 0 && coeffC == 0)
            return 2;

        double discriminant = coeffB * coeffB - 4 * coeffA * coeffC;

        if (discriminant == 0)
            return 1;
        if (discriminant > 0)
            return 2;
        else 
            return 0;
    }

    /**
        Adds two QuadraticExpression objects together.

        @param q1
            a QuadraticExpression object
        @param q2
            a QuadraticExpression object
        @return
            a QuadraticExpression object thats the sum of q1 and q2.
    */
    public static QuadraticExpression add(QuadraticExpression q1, QuadraticExpression q2)
    {
        double newA = q1.coeffA + q2.coeffA;
        double newB = q1.coeffB + q2.coeffB;
        double newC = q1.coeffC + q2.coeffC;

        QuadraticExpression q3 = new QuadraticExpression(newA, newB, newC);
        return q3;
    }

    /**
        Adds QuadraticExpression q to the calling object.

        @param q
            a QuadraticExpression object
    */
    public void add(QuadraticExpression q)
    {
        coeffA += q.coeffA;
        coeffB += q.coeffB;
        coeffC += q.coeffC;
    }

    /**
        Calculates the smaller root of the quadratic equation

        @param none
        @return 
            a double of the calculated smaller root
    */
    public double smallerRoot() throws Exception
    {
        int roots = this.numberOfRoots();

        if (roots == 0)
            throw new Exception(this + " has no solution, thus no small root");
        if (roots == 1 && (coeffA == 0 && coeffB != 0 && coeffC != 0))
            return -coeffC / coeffB;
        if (roots == 1 || roots == 2)
            return (-coeffB - Math.sqrt(Math.pow(coeffB, 2) - 4 * coeffA * coeffC)) / (2 * coeffA);
        else
            return -Double.MAX_VALUE;
    }

    /**
        Calculates the larger root of the quadratic equation

        @param none
        @return 
            a double of the calculated larger root
    */
    public double largerRoot() throws Exception
    {
        int roots = this.numberOfRoots();

        if (roots == 0)
            throw new Exception(this + " has no solution, thus no large root");
        if (roots == 1 && (coeffA == 0 && coeffB != 0 && coeffC != 0))
            return -coeffC / coeffB;
        if (roots == 1 || roots == 2)
            return (-coeffB + Math.sqrt(Math.pow(coeffB, 2) - 4 * coeffA * coeffC)) / (2 * coeffA);
        else
            return Double.MAX_VALUE; 
    }

    /**
        Compares the called object with the parameter object to test if they're the same.

        @param anotherObject
            an object which will be compared with QuadraticExpression 
        @return
            A return value of true if the two objects are equal, false if the objects aren't equal.
    */
    public boolean equals(Object anotherObject)
    {
        if (anotherObject instanceof QuadraticExpression)
        {
            QuadraticExpression obj_ref = (QuadraticExpression) anotherObject;
            return (coeffA == obj_ref.coeffA) && (coeffB == obj_ref.coeffB) && (coeffC == obj_ref.coeffC);
        }
        return false;
    }

    /**
        Creates a new QuadraticExpression object clone of the called object

        @param none
        @return
            Returns an object copy of the called QuadraticExpression object.
    */
    public QuadraticExpression clone()
    {
        QuadraticExpression obj_clone;

        try
        {
            obj_clone = (QuadraticExpression) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException("This class does not implement Cloneable");
        }

        return obj_clone;
    }
}

