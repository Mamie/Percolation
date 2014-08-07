Percolation
===========

Summary
---------
Percolation is an abstract process where a collection of initially isolated nodes gradually becomes connected by addition of random links. This program uses weighted quick-union data type to model a percolation system and estimates the threshold fraction of open sites in order for a system (initially with all sites closed) to be percolated using Monte Carlo simulation.

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

The PercolationStats uses Monto Carlo simulation to estimate percolation threshold:

      public class PercolationStats {
         public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
         public double mean()                     // sample mean of percolation threshold

         public double stddev()                   // sample standard deviation of percolation threshold
         public double confidenceLo()             // returns lower bound of the 95% confidence interval
         public double confidenceHi()             // returns upper bound of the 95% confidence interval
         public static void main(String[] args)   // test client, described below
      }

Test client: PercolationVisualizer
-----------------------
PercolationVisualizer visualizes Percolation and the directory of sample input files can be found at:
> http://coursera.cs.princeton.edu/algs4/testing/percolation/

###### Assignment details:
> http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
