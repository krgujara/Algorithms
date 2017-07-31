import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import java.util.*;               //for List
public class Percolation{
    int[] lattice; //to store if the site is open or close
    int n; //total sites in lattice = size*size
    int number_of_open_sites;
    int virtual_top;
    int virtual_bottom;
    double percolation_threshold;
    WeightedQuickUnionUF uf;
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if(n <=0)
             throw new IllegalArgumentException("n must be greater than 0");  
        this.n = n;
        lattice = new int[n*n];
        number_of_open_sites = 0;
        uf = new WeightedQuickUnionUF(n*n+2);
        for (int i=0;i<n*n;i++)
        {
            //lattice[i] = 0 means - site is blocked.
            //lattice[i] = 1 means - site is open
            lattice[i] = 0;
        }
        virtual_top = site_id(n,n)+1;
        virtual_bottom = site_id(n,n)+2;
        for(int i=0;i<n;i++)
        {
            uf.union(i,virtual_top);
            Out out = new Out();
            out.println("Union("+ i +" , "+ virtual_top+")");
        }
        for(int j=n*(n-1);j<n*n;j++)
        {
            Out out = new Out();
            uf.union(j,virtual_bottom);
            out.println("Union("+ j +" , "+ virtual_bottom+")");
        }
    }
    private int site_id(int row, int col)
    {
        return (row-1)*n + col -1;
    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        //1. validate indices
        validate(row);
        validate(col);
        //2. open the site
        int site_id = site_id(row, col);
        Out out = new Out();
        out.println("Opening Site"+ site_id);
        lattice[site_id]=1;                         //mark site as open
        //2. find adjacent sites to the opened site
        List<Integer> adjacent = adjacent_sites(site_id);
        out.println("Adjacent sites are: ");
        out.println(adjacent);
        //2. connect the sites to adjacent open sites.
        for (int each: adjacent)
        {
            if(lattice[each]==1)  //only if site is open, connect to adjacent site
                uf.union(site_id,each);
        }
        //3. Increase the number of open sites
        number_of_open_sites++;
        
    }
    private List<Integer> adjacent_sites(int site_id)
    {
        List<Integer> adjacent_sites = new ArrayList<Integer>();
        
        Out out = new Out();
        //out.println("Adjacent Sites = ");
        int left = site_id-1;
        int right =site_id+1;
        int top = site_id-n;
        int bottom = site_id+n;
        if (left >=0 && (site_id%n!=0))
            adjacent_sites.add(left);
        if(right<n*n && (site_id)%n!=n-1)
            adjacent_sites.add(right);
        if(top>=0)
            adjacent_sites.add(top);
        if(bottom<n*n)
            adjacent_sites.add(bottom);
        //out.println(adjacent_sites);
        return adjacent_sites;
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        validate(row);
        validate(col);
        if (lattice[site_id(row, col)]==1)
            return true;
        return false;
        
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        validate(row);
        validate(col);
        if(isOpen(row, col) && uf.connected(site_id(row, col),virtual_top))
        {  
            //if site is not connected to neighbours (for last row)
            if((site_id(row,col)>=n*(n-1))&& (site_id(row,col) <n*n))
            {
                Out out = new Out();
                out.println("Last Row "+ site_id(row,col));
                
                List<Integer> adjacent_sites = adjacent_sites(site_id(row,col));
                for(int site: adjacent_sites)
                {
                    out.println("Adjacent Sites to "+site_id(row,col));
                    if(lattice[site]==1 && uf.connected(site,virtual_top))
                    {
                        out.println("Found true for"+site);
                        out.println(lattice[site]);
                        out.println(uf.connected(site,virtual_top));
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
            return false;
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return number_of_open_sites;
    }
    public boolean percolates()              // does the system percolate?
    {
        if(uf.connected(virtual_top,virtual_bottom))
        {
            percolation_threshold = (numberOfOpenSites()*1.0/(n*n));
            return true;
        }
        return false;
    }
    public double getThreshold()
    {
        return percolation_threshold;
    }
    private void validate(int p)
    {
        if (p < 1 || p > n) {
            throw new IllegalArgumentException("index " + p + " must be between 1 and " + n);  
        }
    }
    public static void main(String[] args)
    {
             Out out = new Out();
   
        Percolation percolation = new Percolation(4);
        percolation.open(1,1);
        percolation.open(1,2);
        percolation.open(3,1);
        percolation.open(1,3);
        percolation.open(2,3);
        percolation.open(6,3);
        percolation.open(4,3);
        out.println("isfull"+percolation.isFull(3,1));
        percolation.open(4,1);
        
        out.println(percolation.isFull(4,1));
        
        out.println(percolation.isFull(3,1));
        
        
        
        out.println("Number Of Open Sites - "+percolation.numberOfOpenSites());
    }
    
}