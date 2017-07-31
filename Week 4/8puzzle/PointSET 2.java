import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.ResizingArrayQueue;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Queue;
import java.util.Iterator;
public class PointSET {

   private Set<Point2D> pointSet;
   public PointSET()                               // construct an empty set of points 
   {
     pointSet = new TreeSet<Point2D>();
   }
   public boolean isEmpty()                      // is the set empty? 
   {
        return pointSet.isEmpty();
   }
   public int size()                         // number of points in the set 
   {
        return pointSet.size();
   }
   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
        if (p == null) throw new IllegalArgumentException("Null argument passed");
        pointSet.add(p);
   }
   public boolean contains(Point2D p)            // does the set contain point p? 
   {
   return (pointSet.contains(p));
   }
   public void draw()                         // draw all points to standard draw 
   {
       for (Point2D p : pointSet)
       {
           p.draw();
       }
   }
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary) 
   {
       Queue<Point2D> raq = new Queue<Point2D>();
       for (Point2D point : pointSet)
       {
           if (rect.contains(point))
               raq.enqueue(point);
       }
       return raq;
   }
   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
   {
       if ((pointSet.isEmpty()) || p == null)
           throw new IllegalArgumentException("Null argument passed");
       Point2D currentNearestPoint = null;
       double currentSqMinDistance = Double. POSITIVE_INFINITY;
       for (Point2D point : pointSet)
       {
           double currentPointDistance = point.distanceSquaredTo(p); 
           if (currentPointDistance < currentSqMinDistance)
           {
               currentSqMinDistance = currentPointDistance;
               currentNearestPoint = point;
           }
       }
       return currentNearestPoint;
   }

   public static void main(String[] args)                  // unit testing of the methods (optional)
   {
       PointSET pointSET = new PointSET();
      System.out.println(pointSET.isEmpty());
      for (int i = 0; i < 1000; i++) {
         double x = Math.random();
         double y = Math.random();
         Point2D point2D = new Point2D(x, y);
         pointSET.insert(point2D);
         System.out.println(pointSET.contains(point2D) + " ");
      }
      System.out.println();
      
      System.out.println("Size = " + pointSET.size());
      
      System.out.println("Is contains (0.1, 0.1) = " + pointSET.contains(new Point2D(0.1, 0.1)));
      System.out.println();
      
      Point2D nearest = pointSET.nearest(new Point2D(0.1, 0.1));
      System.out.println("Nearest point to (0.1, 0.1) is " + nearest.toString());
      System.out.println();
      
      RectHV rangeRect = new RectHV(0.45, 0.45, 0.55, 0.55);
      for (Point2D inRangePoint: pointSET.range(rangeRect)) {
         System.out.println(inRangePoint.toString());
      }
      
      pointSET.draw();
      
   }
}