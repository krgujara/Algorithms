import edu.princeton.cs.algs4.Out;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private int n,trials;
    private double[] thresholds;
    private double mean,stddev,confidenceLo,confidenceHi;
    //Percolation percolation;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       if(n<=0 ||trials<=0)
           throw new IllegalArgumentException("n and Trial values should be greater than 0");
       this.n = n;
       this.trials = trials;
       thresholds  = new double[trials];
   }
   public void set_thresholds(double[] list)
   {
       this.thresholds = list;
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(thresholds);
   }
       
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       double neumerator = 0;
       for(double each: thresholds )
       {
           neumerator+=Math.pow((each-mean),2);
       
       }
       stddev = Math.sqrt((neumerator/(trials-1)));
       return stddev;
   }
   
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       
       return 1;
   }
       
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
     return 1;   
    }
    
   public static void main(String[] args)        // test client (described below)
   {
       int n,trials;
       Out out = new Out();
       n = Integer.parseInt(args[0]);
       trials = Integer.parseInt(args[1]);
       double[] thresholds = new double[trials];
       //StdRandom random_site = new StdRandom();
       PercolationStats ps = new PercolationStats(n,trials);
        //for all trials
       for(int i=0;i<trials;i++)
       {
        //1. Initilaize all sites to blocked -  done in the constrictor of percolation
         Percolation percolation = new Percolation(n);
   
        //2. Randomly select site among blocked sites
        while(!percolation.percolates())
        {
            int rand_row = StdRandom.uniform(1,n+1);
            int rand_col = StdRandom.uniform(1,n+1);
            if(!percolation.isOpen(rand_row,rand_col))
            {
                percolation.open(rand_row,rand_col);
            }
        }
        thresholds[i]=(percolation.getThreshold());
       }
       ps.set_thresholds(thresholds);
       out.println("mean                   =  "+ps.mean());
       out.println("stddev                  =  "+ ps.stddev());
       out.println("");
   }
}