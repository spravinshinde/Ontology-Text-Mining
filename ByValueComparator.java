/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author admin
 */
public class ByValueComparator<K,V extends Comparable<? super V>> implements Comparator<K> 
{

  private Map<K,V> map = new HashMap<K,V>();
  
  public ByValueComparator(Map<K,V> map) 
  {
    this.map = map;
  }

  public int compare(K k1, K k2) 
  {
    return map.get(k1).compareTo(map.get(k2));
  }
}