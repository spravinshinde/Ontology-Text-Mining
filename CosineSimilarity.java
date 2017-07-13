/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;
import Jama.Matrix;
/**
 *
 * @author admin
 */
public class CosineSimilarity extends AbstractSimilarity {

  @Override
  protected double computeSimilarity(Matrix sourceDoc, Matrix targetDoc) {
    double dotProduct = sourceDoc.arrayTimes(targetDoc).norm1();
    double eucledianDist = sourceDoc.normF() * targetDoc.normF();
    return dotProduct / eucledianDist;
  }
}
