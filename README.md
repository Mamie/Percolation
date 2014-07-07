Percolation
===========

Percolation
------------
To model a percolation system, Percolation data type is created using follwing API:

      public class Percolation {
         public Percolation(int N)              // create N-by-N grid, with all sites blocked
         public void open(int i, int j)         // open site (row i, column j) if it is not already
         public boolean isOpen(int i, int j)    // is site (row i, column j) open?
         public boolean isFull(int i, int j)    // is site (row i, column j) full?
         public boolean percolates()            // does the system percolate?
      }

PercolationStats
-------------------

The PercolationStats data type uses Monto Carlo simulation to estimate percolation threshold:

      public class PercolationStats {
         public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
         public double mean()                     // sample mean of percolation threshold
         public double stddev()                   // sample standard deviation of percolation threshold
         public double confidenceLo()             // returns lower bound of the 95% confidence interval
         public double confidenceHi()             // returns upper bound of the 95% confidence interval
         public static void main(String[] args)   // test client, described below
      }

PercolationVisualizer
-----------------------
PercolationVisualizer is a client of Percolation data type which reads an input file to visualize the progress of percolation.
