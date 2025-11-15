public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    public static void main(String[] args) {        
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    // Computes the ending balance of a loan after all periods
    private static double endBalance(double loan, double rate, int n, double payment) {    
        double r = rate / 100.0;          
        double balance = loan;
        for(int i = 0; i < n; i++) {
            balance = (balance - payment) * (1 + r);
        }
        return balance;
    }

    // Brute force search 
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double r = rate / 100.0;
        double payment = loan / n * (1 + r * n / 2.0);  
        while(endBalance(loan, rate, n, payment) > 0) {
            payment += epsilon;
            iterationCounter++;
        }
        return payment;
    }

    // Bisection search
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double lo = loan / n;                 
        double hi = loan * (1 + rate / 100);  
        double g = 0;

        while((hi - lo) > epsilon) {
            iterationCounter++;
            g = (lo + hi) / 2.0;
            double currentBalance = endBalance(loan, rate, n, g);
            if(currentBalance > 0) {
                lo = g;
            } else {
                hi = g;
            }
        }
        return (lo + hi) / 2.0;
    }
}
