public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    public static void main(String[] args) {        
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    // End balance calculation using correct formula per period
    private static double endBalance(double loan, double rate, int n, double payment) {
        double balance = loan;
        double r = rate / 100.0;
        for (int i = 0; i < n; i++) {
            balance = balance * (1 + r) - payment;
        }
        return balance;
    }

    // Brute force solver
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = loan / n;
        while (endBalance(loan, rate, n, payment) > epsilon) {
            payment += epsilon;
            iterationCounter++;
        }
        return payment;
    }

    // Bisection solver
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double r = rate / 100.0;
        double L = loan / n;
        double H = loan * Math.pow(1 + r, n);
        double payment = 0;

        while ((H - L) > epsilon) {
            iterationCounter++;
            payment = (L + H) / 2;
            double f = endBalance(loan, rate, n, payment);
            if (f > 0) {
                L = payment;
            } else {
                H = payment;
            }
        }

        return (L + H) / 2;
    }
}
