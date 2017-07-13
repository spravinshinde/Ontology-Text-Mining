/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;

import java.util.Vector;

/**
 *
 * @author admin
 */
public class Clustering
{
     private String mName;
     private Centroid mCentroid;
     private double mSumSqr;
     private Vector mDataPoints;

     public Clustering(String name)
     {
         this.mName = name;
         this.mCentroid = null; //will be set by calling setCentroid()
         mDataPoints = new Vector();
     }

     public void setCentroid(Centroid c) {
         mCentroid = c;
     }

     public Centroid getCentroid() {
         return mCentroid;
     }

     public void addDataPoint(DataPoint dp) { //called from CAInstance
         dp.setCluster(this); //initiates a inner call to calc EuclideanDistance() in DP.
         this.mDataPoints.addElement(dp);
         calcSumOfSquares();
     }

     public void removeDataPoint(DataPoint dp) {
         this.mDataPoints.removeElement(dp);
         calcSumOfSquares();
     }

     public int getNumDataPoints() {
         return this.mDataPoints.size();
     }

     public DataPoint getDataPoint(int pos) {
         return (DataPoint) this.mDataPoints.elementAt(pos);
     }

     public void calcSumOfSquares() { //called from Centroid
         int size = this.mDataPoints.size();
         double temp = 0;
         for (int i = 0; i < size; i++) {
             temp = temp + ((DataPoint)
 this.mDataPoints.elementAt(i)).getCurrentEuDt();
         }
         this.mSumSqr = temp;
     }

     public double getSumSqr() {
         return this.mSumSqr;
     }

     public String getName() {
         return this.mName;
     }

     public Vector getDataPoints() {
         return this.mDataPoints;
     }
    
}
