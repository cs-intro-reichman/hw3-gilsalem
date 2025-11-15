// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    // Gets the loan data and computes the periodical payment.
    public static void main(String[] args) {        
        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    // Computes the ending balance of a loan, given the loan amount, the periodical
    // interest rate (as a percentage), the number of periods (n), and the periodical payment.
    private static double endBalance(double loan, double rate, int n, double payment) { 
        double rest = loan;
        double r = rate / 100.0; // interest rate per period as decimal
        for (int i = 0; i < n; i++) {
            rest = rest * (1 + r) - payment;
        }
        return rest;
    }

    // Sequential search (brute force)
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0; 
        double g = loan / n; // initial guess

        while (endBalance(loan, rate, n, g) > 0) {
            g += epsilon;
            iterationCounter++;
        }

        return g;
    }

    // Bisection search
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) { 
        iterationCounter = 0; 
        double L = loan / n;            // Lower bound
        double H = loan * (1 + rate/100); // Upper bound: loan + one period of interest

        while ((H - L) > epsilon) { 
            iterationCounter++;
            double g = (L + H) / 2;
            double f_g = endBalance(loan, rate, n, g);

            if (f_g > 0) { 
                L = g; 
            } else {
                H = g;
            }
        }

        return (L + H) / 2;
    }    
}
