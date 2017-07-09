import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Out;
import java.util.Iterator;

/*
 @author Komal Gujarathi
 @version 1.0 2017/7/4 5.47pm
*/

public class Permutation
{
    public static void main(String[] args)
    {
        Out out = new Out();
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
       
        for (String s : StdIn.readAllStrings())
            rq.enqueue(s);
        for (int i=0;i<n;i++)
            out.println(rq.dequeue());
    }
}
