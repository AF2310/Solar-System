package org.example.solarsystem;

import java.util.ArrayList;
import java.util.List;
import org.example.solarsystem.constants.HeavenlyBodyLimits;

/**
 *represents a planet as a type of heavenly body.planets have names,average radius,orbit radius
 *each planet has multiple stored in a list for moons
 *,inherits methods from abstract class heavenlybody.
 */
public final class Planet extends HeavenlyBody {

  private final double avgOrbitRadiusInKm;
  private final List<Moon> moons = new ArrayList<>();

  /**
   *constructor for a planet inherits from abstract class heavenlybody
   *,performs validation inside constructor,throws illegal argument if failed.
   *
   * @param name planet name
   *
   *
   *@param avgRadiusInKm average radius in km
   *
   * @param avgOrbitRadiusInkm average orbit radius in km
   *
   */
  protected Planet(String name, int avgRadiusInKm, double avgOrbitRadiusInkm) {
    super(name, avgRadiusInKm);
    if (avgOrbitRadiusInkm < 18000) {
      throw new IllegalArgumentException("Planet orbit must be atleast 18000");
    }
    this.avgOrbitRadiusInKm = avgOrbitRadiusInkm;
  }

  /**
   *adds a moon to a planet,validates moons radius before adittion.
   *
   *
   *
   * @param name moons name
   *
   * @param avgRadiusInKm moons average radius
   *
   * @param avgOrbitRadiusInKm moons average orbit radius
   *
   * @return created moon object
   *
   * @throws illegalArgumentException if invalid radius
   */
  public Moon addMoon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    //if (avgRadiusInKm > this.getAvgRadiusInKm() / 2) {
    //throw new IllegalArgumentException("moon too large");
    //}
    Moon moon = new Moon(name, avgRadiusInKm, avgOrbitRadiusInKm);
    moons.add(moon);
    return moon;

  }

  public void addMoon(Moon moon) {
    moons.add(moon);
  }

  /**
   * Removes a moon from the planet by name.
   *
   * @param moonName The name of the moon to remove.
   * @return  if the moon was removed, false otherwise.
   *
   */
  public boolean removeMoon(String moonName) {
    if (moonName == null || moonName.strip().isEmpty()) {
      throw new IllegalArgumentException("Moon name cannot be null or empty.");
    }
    String validatedName = moonName.strip();
    return moons.removeIf(moon -> moon.getName().equalsIgnoreCase(validatedName));
  }

  public List<Moon> getMoons() {
    return new ArrayList<>(moons);
  }

  /**
   * gets the planets average orbital radius.
   *
   * @return The orbital radius.
   */
  public double getAvgOrbitRadiusInKm() {
    return avgOrbitRadiusInKm;
  }

  /**
   * returns all the heavenly bodies related to this planet.
   * creates deep copies of the planet and its moons to maintain immutability.
   *
   * @return  array of HeavenlyBody objects which includes the moon and the planets.
   *        ,creates a list to store heavenly bodies
   *        ,creates a copy of the current planet and its details and adds it to the list
   *        ,iterates through the moons and creates a copy of all moons with their details
   *        ,adds the moons to the list
   *        ,converts the list into an array and returns it
   */
  public HeavenlyBody[] getHeavenlyBodies() {
    List<HeavenlyBody> allBodies = new ArrayList<>();
    Planet planetCopy = new Planet(this.getName(),
            this.getAvgRadiusInKm(), this.getAvgOrbitRadiusInKm());
    allBodies.add(planetCopy);
    for (Moon moon : moons) {
      Moon moonCopy = new Moon(moon.getName(),
                moon.getAvgRadiusInKm(), moon.getAvgOrbitRadiusInKm());
      allBodies.add(moonCopy);
    }
    return allBodies.toArray(new HeavenlyBody[0]);
  }

  /**
   *method that validates planet radius within a range.
   *
   * @param radius inputted radius
   *
   * @return validated radius ,illegalArgument exception if invalid
   */
  @Override
  protected int checkAvgRadiusInKm(int radius) {
    if (radius < HeavenlyBodyLimits.MINPLANET_RADIUS.getValue()
        || radius > HeavenlyBodyLimits.MAXPLANET_RADIUS.getValue()) {
      throw new IllegalArgumentException("invalid planet radius");
    }
    return radius;
  }

  /**
   *validates planet name.
   *
   * @param name inputted planet name
   *
   * @return formatted and validated planet name, otherwise illegalArgumentException
   */
  @Override
  protected String checkName(String name) {
    if (name == null || name.strip().isEmpty()) {
      throw new IllegalArgumentException("planet must have a name");
    }
    return name.strip();
  }

  /**
   * Uses string builder to creates mutable sequence of characters represented as arrays
   * to not create multiple string instances.
   * generates a string representation of the planet and its moons.
   * creates string builder object instance
   * appends planets details then appends sourrounding moons details
   * by iterating through the moons
   *
   * @return   formatted string representation of planet and moons
   */
  @Override
  public String toString() {
    //return  "Moon:"+ getName()+"average radius"+getAvgRadiusInKm();
    StringBuilder sb = new StringBuilder();
    sb.append("planet:").append(getName())
            .append("(average radius)").append(getAvgRadiusInKm()).append("km\n");
    for (Moon moon : moons) {
      sb.append("  ").append(moon.toString()).append("\n");

    }
    return sb.toString();
  }
}
