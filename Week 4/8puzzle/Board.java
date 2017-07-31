import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] board;
    private int size;
    private int moves;
    
    private Board(int [][] blocks, int moves)
    {
        this.moves = moves;
        this.size=blocks.length;
        this.board = new int[size][];

        for (int i = 0 ; i < size; i++)
        {
            board[i] = blocks[i].clone();   //creates the copy of each row
        }
        
    }
    
    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    {
        this(blocks,0);
    }
    public int dimension()                 // board dimension n
    {    
        return size;
    }
    public int hamming()                   // number of blocks out of place
    {
        int hamming = moves;
        Out out = new Out();
        for (int i = 0 ; i< size ; i++)
        {
            for (int j = 0; j< size ; j++)
            {
                if (board[i][j] != 0 && board[i][j]!=getGoalBoard(i,j) )    // 0 has no correct place
                    hamming++;
            }
        }
        return hamming;
    }
    private int getGoalBoard(int row,int col)
    {
        if (row == size-1 &&  col == size-1)
            return 0;
        return (row*size+col) +1;
    }
    
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int manhattanScore = moves;
        for (int row = 0 ; row< size ; row++)
        {
            for (int col = 0 ; col< size ; col++)
            {
                int comparevalue = board[row][col];
                if (comparevalue == 0)
                    continue;
                int expectedRow = (comparevalue - 1)/size;
                int expectedColumn = (comparevalue - 1) -expectedRow*size;
                
                manhattanScore += (Math.abs(expectedRow - row) + Math.abs(expectedColumn - col));
            }
        }
        return manhattanScore;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        return hamming() == 0;
    
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
            int[][] newBlocks = new int[size][size];
            for (int i = 0 ;i< size; i++)
            {    
                newBlocks[i] = board[i].clone();
            }
            // exchanging the block in the same row
            //Swap 2 blocks that are not 0
            int rowSwap = 0;
            if (newBlocks[0][0] == 0 || newBlocks[0][1] == 0) {
                   rowSwap = 1;
            }
     
           int temp = newBlocks[rowSwap][0];
           newBlocks[rowSwap][0] = newBlocks[rowSwap][1];
           newBlocks[rowSwap][1] = temp;
     
     //Create new board to return
     return new Board(newBlocks);
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if ( y != null && y instanceof Board)
        {
            for (int  i = 0; i < size; i++)
            {
                for (int j = 0; j< size; j++)
                {
                    if (board[i][j] != getGoalBoard(i,j))
                        return false;
                }
            }
            return true;
        }
        return false;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        
        // returns all possible neighbouring boards in the list (which implements Iterable)
        List<Board> neighbors = new ArrayList<Board>();
        int rowzero = 0;
        int colzero = 0;
        
        //get the block that has 0 (empty block)
        for (int i = 0 ;i<size; i++) {
            for (int j = 0 ; j< size; j++) {
                if (board[i][j] == 0) {
                      rowzero = i;
                      colzero = j;
                }
             }
         }
        // move top block element down
        if (rowzero>0)
        {
            int[][] topBlock = new int[size][size];
            for (int i = 0 ;i< size; i++)
            {    
                topBlock[i] = board[i].clone();
            }
            topBlock[rowzero][colzero] = board[rowzero-1][colzero];
            topBlock[rowzero-1][colzero] = 0;
        
            neighbors.add(new Board(topBlock));
        }
        // move bottom element up

        if (rowzero < size-1)
        {
            int[][] bottomBlock = new int[size][size];
            for (int i = 0 ;i< size; i++)
            {    
                bottomBlock[i] = board[i].clone();
            }
            
                   bottomBlock[rowzero][colzero] = board[rowzero+1][colzero];
                   bottomBlock[rowzero+1][colzero] = 0;
             
                   neighbors.add(new Board(bottomBlock));
        }
       
        // move left element to right
        if (colzero > 0)
        {
            int[][] leftBlock = new int[size][size];
            for (int i = 0 ;i< size; i++)
            {    
                leftBlock[i] = board[i].clone();
            }
            
                   leftBlock[rowzero][colzero] = board[rowzero][colzero-1];
                   leftBlock[rowzero][colzero-1] = 0;
             
                   neighbors.add(new Board(leftBlock));
        }

        
        //move right element to left
        if (colzero != size-1)
        {
            int[][] rightBlock = new int[size][size];
            for (int i = 0 ;i< size; i++)
            {    
                rightBlock[i] = board[i].clone();
            }
            
                   rightBlock[rowzero][colzero] = board[rowzero][colzero+1];
                   rightBlock[rowzero][colzero+1] = 0;
             
                   neighbors.add(new Board(rightBlock));
        }
    
       return neighbors;
        
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(board.length + "\n");
        for (int i = 0; i< size; i++)
        {
            for (int j = 0; j< size; j++)
            {
                sb.append(String.format("%2d ",board[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        Out out = new Out();
        In in = new In("/Users/komalgujarathi/Desktop/Java/Coursera/Assignments/Week4/8puzzle_test/puzzle4x4-03.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        out.println(initial.isGoal());
        out.println(initial);
        int[][]blocks1 = new int[n][n];
        blocks1[0][0] = 1;
        blocks1[0][1] = 2;
        blocks1[0][2] = 3;
        
        blocks1[1][0] = 4;
        blocks1[1][1] = 5;
        blocks1[1][2] = 6;
        
        blocks1[2][0] = 7;
        blocks1[2][1] = 8;
        blocks1[2][2] = 0;
        
        Board board2= new Board(blocks1);
        
        out.println("Hamming " + initial.hamming());
        
       out.println(initial.equals(board2));
       out.println("Manhattan: " + initial.manhattan());
       
        Iterable<Board> neighboards = initial.neighbors();
        for (Board bd : neighboards)
            out.println(bd);
        out.println("Twin Board: ");
        out.println(initial.twin());
    }
}
