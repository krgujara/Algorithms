import edu.princeton.cs.algs4.Out;
import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private int n,trials;
    private double[] thresholds; //stores the results of percolation experiments
 
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
       if(n<=0 ||trials<=0)
           throw new IllegalArgumentException("n and Trial values should be greater than 0");
       this.n = n;
       this.trials = trials;
       thresholds  = new double[trials];
       
       //for all trials
       for(int i=0;i<trials;i++)
       {
        //1. Initilaize all sites to blocked -  done in the constrictor of percolation
         Percolation percolation = new Percolation(n);
   
        //2. Randomly select site among blocked sites
        while(!percolation.percolates())
        {

            boolean isOpen = true;
            int randomRow = 0;
            int randomCol = 0;
            while(isOpen)
            {
                randomRow = StdRandom.uniform(1,n+1);
                randomCol = StdRandom.uniform(1,n+1);
   
                isOpen = percolation.isOpen(randomRow,randomCol);
            }
            percolation.open(randomRow,randomCol);
            
        }
        thresholds[i]=percolation.numberOfOpenSites()/(double)(n*n);
       }
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(thresholds);
   }
       
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(thresholds);
   }
   
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       return mean()-((1.96*stddev())/Math.sqrt(trials));
   }
       
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
       return mean()+((1.96*stddev())/Math.sqrt(trials));
    }
    
   public static void main(String[] args)        // test client (described below)
   {
       int n,trials;
       Out out = new Out();
       n = Integer.parseInt(args[0]);
       trials = Integer.parseInt(args[1]);
       double[] thresholds = new double[trials];
       PercolationStats ps = new PercolationStats(n,trials);
           
       out.println("mean\t\t\t\t =  "+ps.mean());
       out.println("stddev\t\t\t\t =  "+ ps.stddev());
       out.println("95% confidence interval\t\t =  ["+ps.confidenceLo()+ ", "+ps.confidenceHi()+"]");
   }
}
