/**
* @author Mamie
*
* Estimate percolation threshold by repeated experiments of randomly choosing grids to open
* @see http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
*/

public class PercolationStats {

    private double[] threshold;

    public PercolationStats(int N, int T) {
        threshold = new double[T];
        int counter = 0;   //the counter for open sites
        int row, column;
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("Argument out of bound");
        for (int t = 0; t < T ; t++) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                row = StdRandom.uniform(1,N+1);      //randomly choose a site to open
                column = StdRandom.uniform(1,N+1);
                if (perc.isOpen(row, column))   continue;
                perc.open(row,column);
                counter++;
            }// the site chosen is already open, choose another site. If not open it and increment the counter
            threshold[t] = (double) counter/(N*N);        // the threshold for this trial is recorded in the threshold[]
            counter = 0;
        }
    }

/**
* @return average value of percolation threshold from repeated trials
*/
    public double mean() {
        return StdStats.mean(threshold);
    }

/**
* @return standard deviation of the percolation threshold experiments
*/
    public double stddev() {
        return StdStats.stddev(threshold);
    }

/**
* @return lower bound of 95% confidence interval
*/
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(threshold.length);
    }

/**
* @return upper bound of 95% confidence interval
*/
    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt(threshold.length);
    }

/**
* test client
*/
    public static void main(String[] args) {
        PercolationStats perc2 = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.printf("mean                  = %f\n",perc2.mean());
        System.out.printf("stddev                = %f\n",perc2.stddev());
        System.out.printf("95%%confidence interval= %f,%f\n",perc2.confidenceLo(),perc2.confidenceHi());
    }
}
