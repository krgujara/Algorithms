import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.Out;

// works properly for 3 or 4 points but not for more than that.

public class BruteCollinearPoints {
   private LineSegment[] segments; 
   private int length; 
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
       if (points == null)
           throw new IllegalArgumentException("Argument passed is null");
       checkDuplicateEntries(points);
      
       ArrayList <LineSegment> foundSegments = new ArrayList<LineSegment>();
       Point[] pointsCopy = Arrays.copyOf(points, points.length);   // creates a completely new arrayand copies 
       //sort points based on their y coordinates, this helps avoiding the duplicates
       Arrays.sort(pointsCopy);

      // check if 4 lines at a time are collinerar
       
       for (int i = 0; i< pointsCopy.length-3 ; i++)
       {
           for (int j = i+1; j< pointsCopy.length-2; j++)
           {
               for (int k = j+1; k< pointsCopy.length-1; k++)
               {
                   for (int l = k+1 ; l<pointsCopy.length; l++)
                   {
                       //Tests for exact floating-point equality. Because floating-point calculations may involve rounding, the calculated values may be imprecise.
                       if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[k]) &&
                           pointsCopy[i].slopeTo(pointsCopy[k]) == pointsCopy[i].slopeTo(pointsCopy[l]))
                       {
                           foundSegments.add(new LineSegment(pointsCopy[i],pointsCopy[l]));
                       }
                   }
               }
           }
       }
       // to array allocates the memory
       segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
       length = segments.length;
   }
   public int numberOfSegments()        // the number of line segments
   {
       return length;
   }
   public LineSegment[] segments()                // the line segments
   {
       //this returns the reference to the mutable object stored in the instance variable segments,
       // which represents the internal structure of the class, instead create a defensive copy  
       // of the object referenced by 'segments' and return the copy
       //return segments;
       return Arrays.copyOf(segments, numberOfSegments());  

   }
   private void checkDuplicateEntries(Point[] points)
   {
       for (int i = 0;i < points.length; i++)
       {  
           for(int j = i+1; j < points.length ; j++)
           {
               if (points[i].compareTo(points[j]) == 0)
                   throw new IllegalArgumentException("Duplicate argument exception");
           }
       }   
   }
   public static void main(String[] args)
   {
       Point p1 = new Point(9000,9000);
       Point p2 = new Point(8000,8000);
       Point p3 = new Point(7000,7000);
       Point p4 = new Point(6000,1000);
       Point p5 = new Point(5000,5000);
       
       Point[] points = {p1,p2,p3,p4,p5, new Point(2,1),new Point(4,2), new Point(6,3), new Point(8,4), new Point(10,3)};
       BruteCollinearPoints bp = new BruteCollinearPoints(points);
  
       Out out = new Out();
       out.println(bp.numberOfSegments());
      LineSegment[] ls = bp.segments();
      for ( int i = 0; i< bp.numberOfSegments(); i++)
      {
          out.println("Segment"+ls[i]);
      }
      
       
   }
}