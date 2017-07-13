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
public class DataPoint 
{
     private double mX,mY;
     private String mObjName;
     private Clustering mCluster;
     private double mEuDt;

     public DataPoint(double x, double y, String name) {
         this.mX = x;
         this.mY = y;
         this.mObjName = name;
         this.mCluster = null;
     }

     public void setCluster(Clustering cluster) {
         this.mCluster = cluster;
         calcEuclideanDistance();
     }

     public void calcEuclideanDistance() {

     //called when DP is added to a cluster or when a Centroid is recalculated.
         mEuDt = Math.sqrt(Math.pow((mX - mCluster.getCentroid().getCx()),
                                    2) + Math.pow((mY - mCluster.getCentroid().getCy()), 2));//Math.pow(double   a,   double   b) ����a��b�η���
     }

     public double testEuclideanDistance(Centroid c) {
         return Math.sqrt(Math.pow((mX - c.getCx()), 2) + Math.pow((mY - c.getCy()), 2));
     }

     public double getX() {
         return mX;
     }

     public double getY() {
         return mY;
     }

     public Clustering getCluster() {
         return mCluster;
     }

     public double getCurrentEuDt() {
         return mEuDt;
     }

     public String getObjName() {
         return mObjName;
     }   
}
