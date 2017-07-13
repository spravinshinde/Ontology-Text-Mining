/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;
import org.apache.commons.collections15.Transformer;

import Jama.Matrix;
/**
 *
 * @author admin
 */
public abstract class AbstractSimilarity implements Transformer<Matrix, Matrix> 
{

  public Matrix transform(Matrix termDocumentMatrix) {
    int numDocs = termDocumentMatrix.getColumnDimension();
    Matrix similarityMatrix = new Matrix(numDocs, numDocs);
    for (int i = 0; i < numDocs; i++) {
      Matrix sourceDocMatrix = termDocumentMatrix.getMatrix(
        0, termDocumentMatrix.getRowDimension() - 1, i, i); 
      for (int j = 0; j < numDocs; j++) {
        Matrix targetDocMatrix = termDocumentMatrix.getMatrix(
          0, termDocumentMatrix.getRowDimension() - 1, j, j);
        similarityMatrix.set(i, j, 
          computeSimilarity(sourceDocMatrix, targetDocMatrix));
      }
    }
    return similarityMatrix;
  }

  protected abstract double computeSimilarity(
      Matrix sourceDoc, Matrix targetDoc);
}