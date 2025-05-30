package org.example.solarsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * The SearchContext class provides a method to search for
 * HeavenlyBody objects based on a specified search strategy.
 */
public class SearchContext {

  /**
   * The search function takes a list of HeavenlyBody objects and a SearchStrategy,
   *  and returns a list of `HeavenlyBody` objects that match the given strategy.
   *
   *
   * @return A list of HeavenlyBody objects that match the search criteria .
   */
  public List<HeavenlyBody> search(List<HeavenlyBody> bodies, SearchStrategy strategy) {
    List<HeavenlyBody> results = new ArrayList<>();
    for (HeavenlyBody body : bodies) {
      if (strategy.matches(body)) {
        results.add(body);
      }
    }
    return results;
  }
  
  
}
