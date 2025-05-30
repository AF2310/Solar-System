
package org.example.solarsystem;

import java.util.List;

/**
 * The AdittionalSearch class extends CompositeSearch
 * and checks if a HeavenlyBody matches all specified search strategies.
 */
public class AdittionalSearch extends CompositeSearch {


  //The constructor then calls the superclass constructor
  // passing the list of strategies to the superclass.
  public AdittionalSearch(List<SearchStrategy> strategies) {
    super(strategies);
  }

  // The method is implementing the logic to determine if a `HeavenlyBody` 
  //object matches all the specified search strategies stored in the `strategies` list.
  @Override
  public boolean matches(HeavenlyBody body) {
    for (SearchStrategy strategy : strategies) {
      if (!strategy.matches(body)) {
        return false;
      }
    }
    return true;

  }
  
}
