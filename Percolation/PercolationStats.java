/**
 * Homework Assignment #4: Percolation
 *
 * - PercolationStats for simulation of percolation
 *
 * @ Student ID : 20145720
 * @ Name       : Im Gi-chan
 **/

import java.util.Random;

public class PercolationStats {

    private int mT;             // number of experiments to run
    private double mMean;       // mean of percolation threshold
    private double mStddev;     // standard deviation of percolation threshold

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0)
            throw new java.lang.IllegalArgumentException(
                            "N and T must be greater than 1");
        mT = T;

        Random random = new Random(System.currentTimeMillis());
        double[] thresholds = new double[mT];	//thresholds is a array of threshold.
        
        for(int i = 0; i < mT; i++) {
        	Percolation percolation = new Percolation(N);
        	int openSites = 0;						//number of open site.
        	while(!percolation.percolates()) {
        		int row = 1 + random.nextInt(N);	//Randomly assigned row.
        		int col = 1 + random.nextInt(N);	//Randomly assigned column.
        		if(!percolation.isOpen(row, col)) {	//Check that (row,col) site is Open.
        			percolation.open(row,  col);
        			openSites++;
        		}
        	}
        	thresholds[i] = (double) openSites / (N * N);	//thresholds is openSites / n^2.
        }
        
        double total = 0;		//Total of thresholds
        for(int i = 0; i < thresholds.length; i++) {
        	total += thresholds[i];
        }
        mMean = total / thresholds.length;		//mean = total / T
        
        double totalDev = 0;				//total of Dev.
        for(int i = 0; i < thresholds.length; i++) {
        	totalDev += (thresholds[i] - mMean) * (thresholds[i] - mMean);
        }
        mStddev = Math.sqrt(totalDev/ (thresholds.length - 1));		//stddev = sqrt(totalDev / (T - 1))

    }

    // sample mean of percolation threshold
    public double mean() {
        return mMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return mStddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mMean - 1.96 * mStddev / Math.sqrt(mT);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mMean + 1.96 * mStddev / Math.sqrt(mT);
    }

    public static void main(String[] args)    // test client(described below)
    {
        PercolationStats percStats;
        int N = 0;
        int T = 0;
        long start = System.currentTimeMillis();
        double time;

        // you must get two inputs N and T
        if (args.length > 0) {
            try {
                N = Integer.parseInt(args[0]);  // get N
                T = Integer.parseInt(args[1]);  // get T
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " and " 
                                            + args[1] + " must be integers.");
                return;
            }
        }

        // run experiment
        percStats = new PercolationStats(N, T);

        // print result
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println("95% confidence interval = "
                + percStats.confidenceLo() + ", " + percStats.confidenceHi());

        time = (System.currentTimeMillis() - start) / 1000.0;
        System.out.printf("total time = %f sec (N = %d, T = %d)\n", time, N, T);
    }
}

