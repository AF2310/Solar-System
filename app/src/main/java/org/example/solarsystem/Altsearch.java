package org.example.solarsystem;

import java.util.List;

/**
 * The Altsearch class extends CompositeSearch and implements a search strategy 
 * that checks if a HeavenlyBody matches any of the provided search strategies.
 */
public class Altsearch extends CompositeSearch {

  public Altsearch(List<SearchStrategy> strategies) {
    super(strategies);

  }

  /**
   * The function iterates through a list of search strategies
   * to determine if a given `HeavenlyBody` object matches any of the strategies.
   *
   *
   * @return The method is returning a boolean value.
   *      It returns true if any of the strategies in the list matches the provided HeavenlyBody, 
   *      otherwise it returns false.
   */
  @Override
  public boolean matches(HeavenlyBody body) {
    for (SearchStrategy strategy : strategies) {
      if (strategy.matches(body)) {
        return true;
      }
    }
    return false;

  }
  
}
