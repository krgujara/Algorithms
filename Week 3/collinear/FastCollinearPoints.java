//
////import java.util.HashMap;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;
//// import java.util.HashMap;
//import edu.princeton.cs.algs4.Out;
//
//public class FastCollinearPoints
//{
//    //private HashMap<Double, List<Point>> foundSegments = new HashMap<>();
//    private List<LineSegment> segments = new ArrayList<LineSegment>();
//   // private HashMap<Double, ArrayList<Point>> map = new HashMap<>();
//  
//    public FastCollinearPoints(Point[] points)
//    {
//        if (points == null)
//            throw new IllegalArgumentException("Illegal Argument passed");
//        checkPoints(points);
//        Point[] sortedOnY = Arrays.copyOf(points, points.length);
//        Arrays.sort(sortedOnY);
//        Point[] pointsCopy = Arrays.copyOf(sortedOnY,sortedOnY.length);
//        //Code to find out coolinear points 
//        //Out out = new Out();
//        
//        //out.println("Before Sorting : ");
//       // for (int i = 0; i< pointsCopy.length; i++)
//        //  out.println(pointsCopy[i]);
//        
//        for (Point point : sortedOnY)
//        {
//            ArrayList<Point> sameSlopePoints = new ArrayList<>();
//           // out.println("Sorting for point: "+ point);
//            Arrays.sort(pointsCopy, point.slopeOrder());
//            // for each point, check all the remaining points
//            Double previousSlope = 0.0;
//            Double slope = point.slopeTo(pointsCopy[1]);   
//            
//            for (int i = 1; i< pointsCopy.length; i++)
//            {    
//                slope = point.slopeTo(pointsCopy[i]);
//            //    out.println(pointsCopy[i]);
//             //   out.println(slope);
//                
//                if (sameSlopePoints.size() == 0)
//                {
//                //    out.println("Size() of sameSlopePoints = 0");
//                    // out.print("Point"+ pointsCopy[i]);
//                    sameSlopePoints.add(pointsCopy[i]);
//                    previousSlope = slope;
//                } else if (previousSlope.equals(slope))
//                {
//                   // out.println("found matching slope");
//                    // out.println("second time for " + pointsCopy[i]);
//                    sameSlopePoints.add(pointsCopy[i]);
//                }
//                else {
//                    if (sameSlopePoints.size()>=2)
//                    {
//                      //  out.println("Size>2");
//                        // out.println("More points");
//                        sameSlopePoints.add(point);
//                        // Add Segment
//                        
//                        // out.println("SLopePoints: ");
//                        // for (Point slopePoint: sameSlopePoints)
//                        // out.print(slopePoint);
//                        // out.println("\n");
//                        addSegmentIfNew(sameSlopePoints, slope);       
//                    }
//                    
//                    sameSlopePoints.clear();
//                    //sameSlopePoints.add(pointsCopy[i]);  
//                    previousSlope = slope;
//                    
//                }      
//            }
//            if (sameSlopePoints.size()>=2)
//            {
////                out.println("Going to find same slope points");
//                sameSlopePoints.add(point);
//                addSegmentIfNew(sameSlopePoints, slope);
//            }
//            sameSlopePoints.clear();
//            previousSlope = slope;
//        }
//        
//    }
//    
//    /*  public FastCollinearPoints(Point[] points)
//    {
//        if (points == null)
//            throw new IllegalArgumentException("Illegal Argument passed");
//        checkPoints(points);
//        
//        Point[] pointsCopy = Arrays.copyOf(points,points.length);
//        //Code to find out coolinear points 
//        Out out = new Out();
//        
//        out.println("Before Sorting : ");
//        for (int i = 0; i< pointsCopy.length; i++)
//          out.println(pointsCopy[i]);
//        
//        for (Point point : points)
//        {
//            ArrayList<Point> sameSlopePoints = new ArrayList<>();
//            out.println("Sorting for point: "+ point);
//            Arrays.sort(pointsCopy, point.slopeOrder());
//            // for each point, check all the remaining points
//            Double previousSlope = 0.0;
//            Double slope = point.slopeTo(pointsCopy[1]);   
//            
//            for (int i = 1; i< pointsCopy.length; i++)
//            {    
//                slope = point.slopeTo(pointsCopy[i]);
//                out.println(pointsCopy[i]);
//                out.println(slope);
//                
//                if (sameSlopePoints.size() == 0)
//                {
//                    out.println("Size() of sameSlopePoints = 0");
//                    // out.print("Point"+ pointsCopy[i]);
//                    sameSlopePoints.add(pointsCopy[i]);
//                    previousSlope = slope;
//                } else if (previousSlope.equals(slope))
//                {
//                    out.println("found matching slope");
//                    // out.println("second time for " + pointsCopy[i]);
//                    sameSlopePoints.add(pointsCopy[i]);
//                }
//                else {
//                    if (sameSlopePoints.size()>=2)
//                    {
//                        out.println("Size>2");
//                        // out.println("More points");
//                        sameSlopePoints.add(point);
//                        // Add Segment
//                        
//                        // out.println("SLopePoints: ");
//                        // for (Point slopePoint: sameSlopePoints)
//                        // out.print(slopePoint);
//                        // out.println("\n");
//                        addSegmentIfNew(sameSlopePoints, slope);       
//                    }
//                    
//                    sameSlopePoints.clear();
//                    //sameSlopePoints.add(pointsCopy[i]);  
//                    previousSlope = slope;
//                    
//                }      
//            }
//            if (sameSlopePoints.size()>=2)
//            {
//                out.println("Going to find same slope points");
//                sameSlopePoints.add(point);
//                addSegmentIfNew(sameSlopePoints, slope);
//            }
//            sameSlopePoints.clear();
//            previousSlope = slope;
//        }
//        
//    }
//    */
//    private void addSegmentIfNew(ArrayList<Point> sameSlopePoints, double slope)
//    {
//   //     Out out = new Out();
//        ArrayList<Point> copiedArrayList= new ArrayList<Point>();
//        copiedArrayList.addAll(sameSlopePoints);
//        Collections.sort(sameSlopePoints);
//        Point startPoint = sameSlopePoints.get(0);
//        Point endPoint = sameSlopePoints.get(sameSlopePoints.size()-1);
//        //map.put(slope, copiedArrayList);
//        //for (HashMap.Entry entry : map.entrySet())
//        //{
//         //   out.println(entry.getKey() + " - " + entry.getValue());
//      //  }
//        //if (map.indexOf(-0.5) != -1)
//         //   out.println("Present");
//        //else 
//         //  out.println("absent");
//        // out.println(( map.get(-0.5714285714285714)).contains(new Point(7000, 3000)));
//        boolean found = false;
//        for (LineSegment segment : segments)
//        {
//            // out.println("----"+startPoint+ endPoint);
//            // out.println("Start and End Point"+startPoint + endPoint);
//            if (segment.toString().equals(""+startPoint+"->"+endPoint))
//            {
//                found = true;
//                break;
//            }
//            
//        }
//        if (!found) 
//            segments.add(new LineSegment(startPoint, endPoint));
//       // for (int i = 0 ; i< segments.size(); i++)
//        //    out.println("Segments: "+ segments.get(i));
//    }
//    public int numberOfSegments()
//    {
//        return segments.size();
//    }
//    public LineSegment[] segments()
//    {
//        return segments.toArray(new LineSegment[segments.size()]);
//    }
//    private void checkPoints(Point[] points)
//    {
//        
//        /* Check if any of the points passed to the constructor is null*/
//        
//        for (Point point: points)
//            if (point == null)
//            throw new IllegalArgumentException("Argument Passed is null");
//        
//        /* Check if there is a duplicate point */
//        for (int i = 0; i< points.length-1; i++)
//            for (int j = i+1; j<points.length; j++)
//                if (points[i].compareTo(points[j])==0)
//                {
//                     throw new IllegalArgumentException("Duplicate Argument Passed");
//                }
//    }
//    
//    public static void main(String[] args)
//    {
//        Out out = new Out();
//        // Point[] points = {new Point(19000,10000),new Point(18000,10000), new Point(32000,10000), new Point(21000,10000), new Point(1234,5678), new Point(14000,10000)};
//        // Point[] points = {new Point(1,2),new Point(5,2), new Point(3,2), new Point(7,2), new Point(9,2), new Point(18,2),new Point(15,3), new Point(16,3), new Point(17,3), new Point(18,3)};
//        
//        // Point[] points = {new Point(10000,0), new Point(0,10000), new Point(3000,7000), new Point(7000,3000), new Point(2000,21000), new Point(3000,4000), new Point(14000,15000), new Point(6000,7000)};
//        //Point[] points = {new Point(1,1), new Point(2,1), new Point(3,1)};
//        Point[] points = {new Point(1,1), new Point(4,2),new Point(1,2),new Point(1,3), new Point(2,1), new Point(8,4), new Point(3,1),new Point(16,8)};
//        FastCollinearPoints fc =  new FastCollinearPoints(points);
//        LineSegment[] segments = fc.segments();
//        out.println("Segments");
//        for (LineSegment seg : segments)
//            out.println(seg);
//        
//    }
//}

import java.util.*;


/**
 * A fst solution of finding colinear points in a set of points.
 *
 * @author ISchwarz
 */
public class FastCollinearPoints {

    private HashMap<Double, List<Point>> foundSegments = new HashMap<>();
    private List<LineSegment> segments = new ArrayList<>();


    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Illegal Argument passed");

        checkDuplicatedEntries(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
//        Arrays.sort(pointsCopy);

        for (Point startPoint : points) {
            Arrays.sort(pointsCopy, startPoint.slopeOrder());

            List<Point> slopePoints = new ArrayList<>();
            double slope = 0;
            double previousSlope = Double.NEGATIVE_INFINITY;

            for (int i = 1; i < pointsCopy.length; i++) {
                slope = startPoint.slopeTo(pointsCopy[i]);
                if (slope == previousSlope) {
                    slopePoints.add(pointsCopy[i]);
                } else {
                    if (slopePoints.size() >= 3) {
                        slopePoints.add(startPoint);
                        addSegmentIfNew(slopePoints, previousSlope);
                    }
                    slopePoints.clear();
                    slopePoints.add(pointsCopy[i]);
                }
                previousSlope = slope;
            }

            if (slopePoints.size() >= 3) {
                slopePoints.add(startPoint);
                addSegmentIfNew(slopePoints, slope);
            }
        }
    }

    private void addSegmentIfNew(List<Point> slopePoints, double slope) {
        List<Point> endPoints = foundSegments.get(slope);
        Collections.sort(slopePoints);

        Point startPoint = slopePoints.get(0);
        Point endPoint = slopePoints.get(slopePoints.size() - 1);

        if (endPoints == null) {
            endPoints = new ArrayList<>();
            endPoints.add(endPoint);
            foundSegments.put(slope, endPoints);
            segments.add(new LineSegment(startPoint, endPoint));
        } else {
            for (Point currentEndPoint : endPoints) {
                if (currentEndPoint.compareTo(endPoint) == 0) {
                    return;
                }
            }
            endPoints.add(endPoint);
            segments.add(new LineSegment(startPoint, endPoint));
        }
    }


    private void checkDuplicatedEntries(Point[] points) {
    //        /* Check if any of the points passed to the constructor is null*/
        
        for (Point point: points){
            if (point == null)
                throw new IllegalArgumentException("Argument Passed is null");
        }
        /* Check if there is a duplicate point */
        for (int i = 0; i< points.length-1; i++)
            for (int j = i+1; j<points.length; j++)
                if (points[i].compareTo(points[j])==0)
                {
                     throw new IllegalArgumentException("Duplicate Argument Passed");
                }

    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

}