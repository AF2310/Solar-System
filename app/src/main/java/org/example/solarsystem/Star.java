
package org.example.solarsystem;

import java.util.ArrayList;
import java.util.List;
import org.example.solarsystem.constants.HeavenlyBodyLimits;

/**
 *Represents a star, which is a type of heavenly body.
 *has  name,average radius attributes and a list of planets orbiting it
 */
public final class Star extends HeavenlyBody {

  private final List<Planet> planets = new ArrayList<>();

  /**
   *.
   *
   * @description constructor for star objects
   * @param  name          name of heavenly body
   * @param  avgRadiusInKm average radius of a star
   *
   */
  
  public Star(String name, int avgRadiusInKm) {
    super(name, avgRadiusInKm);
  }

  /**
   *.
   *
   * @description adds new planet object to the star system
   * @param  name               name of the planet
   * @param  avgRadiusInKm      average radius of the planet
   * @param  avgOrbitRadiusInKm orbit radius of the planet
   * @return                    returns newly created planet object
   */
  public Planet addPlanet(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    Planet planet = new Planet(name, avgRadiusInKm, avgOrbitRadiusInKm);
    planets.add(planet);
    return planet;
  }

  public void addPlanet(Planet planet) {
    planets.add(planet);
  }

  public List<Planet> getPlanets() {
    return new ArrayList<>(planets);
  }

  /**
   *.
   *
   * @description retrieves all heavenly bodies withing the star system
   * @return   returns an array of heavenly body objects representing a star its planets,their moons
   *      creates a list to store all celestial bodies,adds the star object
   *      ,loops through each planet
   *      ,creates a copy of the planet and add it to the list
   *      ,loops through heavenly bodies and retrieves bodies that are instance of moons
   *      ,creates a copy of the moon and adds it to the list
   *      ,converts the list into an array and returns it
   *
   */
  public HeavenlyBody[] getHeavenlyBodies() {
    List<HeavenlyBody> allBodies = new ArrayList<>();
    Star starCopy = new Star(this.getName(), this.getAvgRadiusInKm());
    allBodies.add(starCopy);
    for (Planet planet : planets) {
      Planet planetCopy = new Planet(planet.getName(),
              planet.getAvgRadiusInKm(), planet.getAvgOrbitRadiusInKm());
      allBodies.add(planetCopy);
      for (HeavenlyBody body : planet.getHeavenlyBodies()) {
        if (body instanceof Moon) {
          Moon moonCopy = new Moon(body.getName(),
                  body.getAvgRadiusInKm(), ((Moon) body).getAvgOrbitRadiusInKm());
          allBodies.add(moonCopy);

        }
      }
    }
    return allBodies.toArray(new HeavenlyBody[0]);
  }

  /**
   *.
   *
   * @description validates average radius
   * @param  radius inputted radius
   * @return        returns validated radius,throws illegal argument if invalid
   */
  @Override
  protected int checkAvgRadiusInKm(int radius) {
    if (radius < HeavenlyBodyLimits.MINSTAR_RADIUS.getValue()
        || radius > HeavenlyBodyLimits.MAXSTAR_RADIUS.getValue()) {
      throw new IllegalArgumentException("Star radius must be within limits.");
    }
    return radius;
  }

  /**
   *.
   *
   * @description validates inputted name,else illegalarguent exception
   * @param  name inputted name
   * @return      returns validated name
   */
  @Override
  protected String checkName(String name) {
    if (name == null || name.strip().isEmpty()) {
      throw new IllegalArgumentException("star must have a name");
    }
    return name.strip();
  }

  /**
   *.
   *
   * @description Returns a hierarchical string representation of the Star object and its planets.
   * @return   returns formatted string representation
   *        ,creates string builder object to avoid creating multiple temporary string objects
   *        ,appends name and radius about star with formatted strings
   *        ,appends same details for each planet
   *        ,converts the built string to regular string object
   */
  @Override
  public String toString() {
    //return  "Star:"+ getName()+"average radius"+getAvgRadiusInKm();

    StringBuilder sb = new StringBuilder();
    sb.append("Star:").append(getName())
            .append("(average radius)").append(getAvgRadiusInKm()).append("km\n");
    for (Planet planet : planets) {
      sb.append("  ").append(planet.toString()).append("\n");

    }
    return sb.toString();

  }
}
