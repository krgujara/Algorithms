import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Out;
/*
 * The Percolation class builds N*N grid of WeightedQuickUnionUF objects
 * to demonstrate percolation system.
 * All nodes in system are blocked initially,
 * 
 */
public class Percolation{
    
    private final int virtual_top;
    private final int virtual_bottom;
    private final int n; //total sites in lattice = size*size
    
    private int[] lattice; //to store if the site is open or close (its boolean because you dont need to initiaize it tehn)
    private int number_of_open_sites;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF fullness;
    
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <=0)
             throw new IllegalArgumentException("n must be greater than 0");  
        this.n = n;
        lattice = new int[n*n];
        number_of_open_sites = 0;
        uf = new WeightedQuickUnionUF(n*n+2);
        fullness = new WeightedQuickUnionUF(n*n+1);
        
        virtual_top = site_id(n,n)+1;
        virtual_bottom = site_id(n,n)+2;
    }
    private int site_id(int row, int col)
    {
        return (row-1)*n + col -1;
    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (isOpen(row,col))
            return;
        // 1. validate indices
        validate(row);
        validate(col);
        // 2. open the site
        lattice[site_id(row, col)]=1;                         // mark site as open
        
        connectWithSurroundingOpenSites(row, col);

        //3. Increase the number of open sites
        number_of_open_sites++;
        
    }
    private void connectWithSurroundingOpenSites(int row, int col)
    {
        int index = site_id(row, col);
        
        if (row == 1)
        {
            // connect the node to virtual top if it is the first row in grid
            uf.union(virtual_top, index);
            fullness.union(virtual_top,index);
        }
        if (row == n)
        {
            // connecting index node to virtual bottom if it is in the last row
            uf.union(virtual_bottom, index);
        }
        if (isValid(row,col-1)&& isOpen(row,col-1))
        {
            uf.union(site_id(row, col-1),index);
            fullness.union(site_id(row, col-1),index);
        }
        if (isValid(row,col+1)&& isOpen(row,col+1))
        {
            uf.union(site_id(row, col+1),index);
            fullness.union(site_id(row, col+1),index);
        }
        if (isValid(row-1,col)&& isOpen(row-1,col))
        {
            uf.union(site_id(row-1, col),index);
            fullness.union(site_id(row-1, col),index);
        }
        if (isValid(row+1,col)&& isOpen(row+1,col))
        {
            uf.union(site_id(row+1, col),index);
            fullness.union(site_id(row+1, col),index);
        }
    
    }
    private boolean isValid(int row, int col)
    {
        return row>=1 && row<=n && col>=1 && col<=n;
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

        return fullness.connected(site_id(row, col),virtual_top);
    
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return number_of_open_sites;
    }
    public boolean percolates()              // does the system percolate?
    {
        return (uf.connected(virtual_top,virtual_bottom));
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
        percolation.open(3,3);
        percolation.open(4,3);
        out.println("isfull"+percolation.isFull(3,1));
        percolation.open(4,1);
        
        out.println(percolation.isFull(4,1));
        
        out.println(percolation.isFull(3,1));
        out.println("Number Of Open Sites - "+percolation.numberOfOpenSites());
    }
    
}