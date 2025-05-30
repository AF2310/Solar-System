package org.example.solarsystem;

/**
 * The RadiusSearch class implements a SearchStrategy interface
 * to filter HeavenlyBody objects based on their average radius within a specified range.
 */
public class RadiusSearch implements SearchStrategy {
  private final int minRadius;
  private final int maxRadius;

  public RadiusSearch(int min, int max) {
    this.minRadius = min;
    this.maxRadius = max;
  }

  /**
   * The function checks
   * if a HeavenlyBody's average radius falls within a specified range.
   * The method checks if the average radius of the HeavenlyBody
   *  falls within a specified range defined by minRadius and max radius.
   *
   * @return The method is returning a boolean value.
   */
  @Override
  public boolean matches(HeavenlyBody body) {
    int radius = body.getAvgRadiusInKm();
    return radius >= minRadius && radius <= maxRadius;
  }
  
}
