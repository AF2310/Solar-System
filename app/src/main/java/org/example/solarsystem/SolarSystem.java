package org.example.solarsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a solar system with a star, planets, and
 *  functionality for adding, removing, and sorting planets by size and orbit.
 */
public class SolarSystem {
  private Star star;
  private final List<Planet> planets = new ArrayList<>();

  /**
   *  This part of the code is the constructor for the class.
   * It takes a Star object as a parameter when creating a new SolarSystem instance
   *
   */
  public SolarSystem(Star star) {
    if (star == null) {
      throw new IllegalArgumentException("Star is required");
    }
    this.star = star;
  }

  /**
   * The  function returns a Star object.
   *
   * @return The method  is returning an object of type Star.
   */
  public Star getStar() {
    return star;

  }

  /**
   * The function returns a new ArrayList containing all the planets.
   *
   * @return A new ArrayList containing a copy of the "planets" list is being returned.
   */
  public List<Planet> getPlanets() {
    return new ArrayList<>(planets);
  }

  /**
   * The  function adds a Planet object to a list,
   * throwing an exception if the input is null.
   */
  public void addPlanet(Planet planet) {
    if (planet == null) {
      throw new IllegalArgumentException("cannot be null.");
    }
    planets.add(planet);
  }

  /**
   * The function  removes a planet by name from a list of planets,
   * clearing all its moons before removal.
   *
   * @return  returns a boolean value based on wether the planet name is found
   */
  public boolean removePlanet(String planetName) {
    for (int i = 0; i < planets.size(); i++) {
      Planet planet = planets.get(i);
      if (planet.getName().equalsIgnoreCase(planetName)) {
        planet.getMoons().clear(); // remove all moons of a planet before removing a planet
        planets.remove(i);
        return true;
      }
    }
    return false;
  }

  // manual bubble sorting
  /**
  * The function  sorts a list of planets based on their average radius in kilometers.
  *
  * @return method returns a List of Planet objects sorted by their
  *     average radius in kilometers in ascending order.
  */
  public List<Planet> getPlanetsSortedBySize() {
    List<Planet> sorted = new ArrayList<>(planets);
    for (int i = 0; i < sorted.size() - 1; i++) {
      for (int j = 0; j < sorted.size() - i - 1; j++) {
        if (sorted.get(j).getAvgRadiusInKm() > sorted.get(j + 1).getAvgRadiusInKm()) {
          Planet temp = sorted.get(j);
          sorted.set(j, sorted.get(j + 1));
          sorted.set(j + 1, temp);
        }

      }
    }
    return sorted;
  }



  /**
   * The function  sorts a list of planets based on their average orbit radius in ascending order.
   *
   * @return returns a List of Planet objects sorted
   *      by their average orbit radius in kilometers in ascending order.
   */
  public List<Planet> getPlanetsSortedByOrbit() {
    List<Planet> sorted = new ArrayList<>(planets);
    for (int i = 0; i < sorted.size() - 1; i++) {
      for (int j = 0; j < sorted.size() - i - 1; j++) {
        if (sorted.get(j).getAvgOrbitRadiusInKm() > sorted.get(j + 1).getAvgOrbitRadiusInKm()) {
          Planet temp = sorted.get(j);
          sorted.set(j, sorted.get(j + 1));
          sorted.set(j + 1, temp);
        }

      }
    }
    return sorted;
  }

  // hierarchical string builder
  /**
   * The method generates a formatted string
   * representation of a star system object and its components.
   *
   * @return The method is returning a formatted string representation of a star system. 
   *        It includes information about the star, planets,
   *        and moons in the system, such as their names,
   *        average radii in kilometers ect
   *        The format includes indentation to represent 
   *        the hierarchical structure of the star system.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(star.getName()).append(":").append(star.getAvgRadiusInKm()).append("\n");

    for (Planet planet : planets) {
      sb.append("-").append(planet.getName()).append(":")
          .append(planet.getAvgRadiusInKm()).append(":")
          .append(planet.getAvgOrbitRadiusInKm()).append("\n");
      for (Moon moon : planet.getMoons()) {
        sb.append("--").append(moon.getName()).append(":")
        .append(moon.getAvgRadiusInKm()).append(":")
        .append(moon.getAvgOrbitRadiusInKm()).append("\n");

      }

    }
    return sb.toString();
  }

  /*public String toFileFormat(){
    StringBuilder sb = new StringBuilder();

  }*/


}