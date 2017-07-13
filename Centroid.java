/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;

/**
 *
 * @author admin
 */
public class Centroid 
{
      private double mCx, mCy;
     private Clustering mCluster;

     public Centroid(double cx, double cy)
	 {
         this.mCx = cx;
         this.mCy = cy;
     }

     public void calcCentroid() { //only called by CAInstance
         int numDP = mCluster.getNumDataPoints();
         double tempX = 0, tempY = 0;
         int i;
         //caluclating the new Centroid
         for (i = 0; i < numDP; i++) {
             tempX = tempX + mCluster.getDataPoint(i).getX();
             //total for x
             tempY = tempY + mCluster.getDataPoint(i).getY();
             //total for y
         }
         this.mCx = tempX / numDP;
         this.mCy = tempY / numDP;
		 //System.out.println("cx "+this.mCx);
		 //System.out.println("cy "+this.mCy);
         //calculating the new Euclidean Distance for each Data Point
         tempX = 0;
         tempY = 0;
         for (i = 0; i  <numDP; i++) {
             mCluster.getDataPoint(i).calcEuclideanDistance();
         }
         //calculate the new Sum of Squares for the Cluster
         mCluster.calcSumOfSquares();
     }

     public void setCluster(Clustering c)
     {
         this.mCluster = c;
     }

     public double getCx() {
         return mCx;
     }

     public double getCy() {
         return mCy;
     }

     public Clustering getCluster()
     {
         return mCluster;
     }   
}
