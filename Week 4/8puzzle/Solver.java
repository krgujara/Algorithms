import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;


public class Solver {

    private MinPQ<SearchNode> pq;
    private MinPQ<SearchNode> pqTwin;
    private SearchNode goal;
    private boolean solvable;
    private boolean swapSolvable; 
    
    //private final Comparator<SearchNode> byHamming = new ByHamming();
    private final Comparator<SearchNode> byManhattan = new ByManhattan();
    
    
    private class SearchNode
    {
        private Board board;
        private int moves;
        private SearchNode prev;
        
        public SearchNode(Board b, int m, SearchNode p)
        {
            board = b;
            moves = m;
            prev = p;
        }
        public boolean equals(Object y)
        {
            if (y == this) return true;
            if (y == null) return false;
            if (y.getClass() != this.getClass()) return false;
            SearchNode that  = (SearchNode) y;
            if (that.board.equals(board))
                return true;
            return false;
        }
        public int hashCode()
        {
            return board.toString().hashCode();
        }
    }

    
    /*
    private class ByHamming implements Comparator<SearchNode>
    {
        public int compare(SearchNode n1, SearchNode n2)
        {
            return n1.board.hamming() - n2.board.hamming();
        }
    }*/
    private class ByManhattan implements Comparator<SearchNode>
    {
        
        // manhattan+moves = priority
        public int compare(SearchNode n1, SearchNode n2)
        {
            return n1.board.manhattan()+n1.moves-n2.board.manhattan()-n2.moves;
        }
    }
    
    
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        solvable = false;
        swapSolvable = false;
        
        if (initial == null)
            throw new IllegalArgumentException("Null argument passed");
        pq = new MinPQ<SearchNode>(byManhattan);
        pqTwin = new MinPQ<SearchNode>(byManhattan);
        
        SearchNode initNode = new SearchNode(initial,0, null);
        pq.insert(initNode);
        
        SearchNode twinNode = new SearchNode(initial.twin(), 0, null);
        pqTwin.insert(twinNode);
        
        while(!solvable && !swapSolvable)
        {
            solvable = solve(pq);
            swapSolvable = solve(pqTwin);
        }
    }
    private boolean solve(MinPQ<SearchNode> pq)
    {
        // pop min from queue, check of solved, if not, queue neighbours 
        SearchNode node = pq.delMin();
        if (node.board.isGoal()){
          goal = node;
          return true; 
        }
        for (Board board : node.board.neighbors())
        {
            SearchNode n = new SearchNode(board, node.moves+1,node);
            if (n.equals(node.prev))
                continue;
             pq.insert(n);
        }
        
        return false;
    }
    
    
    public boolean isSolvable()            // is the initial board solvable?
    {
        return solvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
       if (! solvable)
           return -1;
        return goal.moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (goal == null)
            return null;
        Stack<Board> stack = new Stack<Board>();
        SearchNode node = goal;
        stack.push(node.board);
        while(node.prev != null)
        {
            stack.push(node.prev.board);
            node = node.prev;
        }
        
        
        return stack;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        Out out = new Out();
        In in = new In("/Users/komalgujarathi/Desktop/Java/Coursera/Assignments/Week4/8puzzle_test/puzzle3X3-unsolvable.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        out.println(initial);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        out.println("No solution possible");
    else {
        out.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            out.println(board);
     }
    }
}