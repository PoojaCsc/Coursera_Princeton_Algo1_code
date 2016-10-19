package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int trials;
	
	private double[] thresholds; //array to store the threshold results after each trial
 
	
	// perform trials independent experiments on an n-by-n grid
	 public PercolationStats(int n, int trials) { 
		 
		 if(n <= 0 || trials <= 0)
			 throw new IllegalArgumentException();
		 
		 this.trials = trials;
	
		 int openSites = 0;
		 thresholds = new double[trials];
		
		 for(int k = 0 ; k < trials; k++){
			 
			 Percolation perc = new Percolation(n); 
			 
			 while(!perc.percolates()){
				int i = StdRandom.uniform(1, n+1);
				int j = StdRandom.uniform(1, n+1);
				if(!perc.isOpen(i, j))
					perc.open(i, j);
				openSites++;
				
			 }
			 thresholds[k] = openSites/n*n;
			
		 }
		 
	 }   
	 
	  // sample mean of percolation threshold
	   public double mean() {
		   return StdStats.mean(thresholds);
	   }         
	   
	// sample standard deviation of percolation threshold
	   public double stddev() {
		   return StdStats.stddev(thresholds);
	   }  
	   
	// low  endpoint of 95% confidence interval
	   public double confidenceLo(){
		   return mean() - (1.96 * stddev())/Math.sqrt(trials);
	   } 
	   
	// high  endpoint of 95% confidence interval
	   public double confidenceHi() {
		   return mean() + (1.96 * stddev())/Math.sqrt(trials);
	   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(n, trials);
		System.out.println("mean = " + stats.mean() );
		System.out.println("stddev = " + stats.stddev() );
		System.out.println("95% confidence interval = " + stats.confidenceLo() + "," + stats.confidenceHi());;
		

	}

}
