/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/**
 *
 * @author admin
 */
public class IdGenerator 
{
    private int upperBound;
  
  private Random randomizer;
  private Set<Integer> ids = new HashSet<Integer>();
  
  public IdGenerator(int upperBound) {
    this.upperBound = upperBound;
    randomizer = new Random();
  }
  
  public int getNextId() {
    if (ids.size() == upperBound) {
      ids.clear();
    }
    for (;;) {
      int id = randomizer.nextInt(upperBound);
      if (ids.contains(id)) {
        continue;
      }
      ids.add(id);
      return id;
    }
  }
}
