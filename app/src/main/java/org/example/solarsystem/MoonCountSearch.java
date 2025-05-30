package org.example.solarsystem;

/**
 * This Java class, MoonCountSearch,
 * implements a SearchStrategy interface to
 * filter HeavenlyBody objects based on the minimum number of moons specified.
 */
public class MoonCountSearch implements SearchStrategy {
  private final int minMoons;

  
  public MoonCountSearch(int minMoons) {
    this.minMoons = minMoons;
  }

  /**
   * This function checks if a HeavenlyBody object is a Planet ,
   * with at least a minimum number of moons.
   *
   * @return The method is returning a boolean value 
   *       based on whether the given `HeavenlyBody` object is an instance of `Planet`
   */
  @Override
  public boolean matches(HeavenlyBody body) {
    if (body instanceof Planet planet) {
      return planet.getMoons().size() >= minMoons;
    }
    return false;
  }

  
}
