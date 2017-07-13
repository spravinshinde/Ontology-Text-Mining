/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math.stat.descriptive.moment.Mean;

import Jama.Matrix;
/**
 *
 * @author admin
 */
public class SOM1 
{
    private final Log log = LogFactory.getLog(getClass());
  
  private double maxDiameter;
  private boolean randomizeDocuments;
  
  public void setMaxRadius(double maxRadius) {
    this.maxDiameter = maxRadius * 2.0D;
  }
  
  public void setRandomizeDocuments(boolean randomizeDocuments) {
    this.randomizeDocuments = randomizeDocuments;
  }
  
  public List<Cluster> cluster(DocumentCollection collection) {
    if (randomizeDocuments) {
      collection.shuffle();
    }
    List<Cluster> clusters = new ArrayList<Cluster>();
    Set<String> clusteredDocNames = new HashSet<String>();
    cluster_r(collection, clusters, clusteredDocNames, 0);
    return clusters;
  }

  private void cluster_r(DocumentCollection collection, 
      List<Cluster> clusters, 
      Set<String> clusteredDocNames, int level) {
    int numDocs = collection.size();
    int numClustered = clusteredDocNames.size();
    if (numDocs == numClustered) {
      return;
    }
    Cluster cluster = new Cluster("C" + level);
    for (int i = 0; i < numDocs; i++) {
      Matrix document = collection.getDocumentAt(i);
      String docName = collection.getDocumentNameAt(i);
      if (clusteredDocNames.contains(docName)) {
        continue;
      }
      log.debug("max dist=" + cluster.getCompleteLinkageDistance(document));
      if (cluster.getCompleteLinkageDistance(document) < maxDiameter) {
        cluster.addDocument(docName, document);
        clusteredDocNames.add(docName);
      }
    }
    if (cluster.size() == 0) 
    {
      log.warn("No clusters added at level " + level + ", check diameter");
    }
    clusters.add(cluster);
    cluster_r(collection, clusters, clusteredDocNames, level + 1);
  }
}
