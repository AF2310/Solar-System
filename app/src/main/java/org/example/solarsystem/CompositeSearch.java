package org.example.solarsystem;

import java.util.List;

/**
 * The CompositeSearch class is an abstract class that implements 
 * the SearchStrategy interface and contains a list of search strategies.
 */
public abstract class CompositeSearch implements SearchStrategy {
  protected  List<SearchStrategy> strategies;

  public CompositeSearch(List<SearchStrategy> strategies) {
    this.strategies = strategies;
  }
  
}
