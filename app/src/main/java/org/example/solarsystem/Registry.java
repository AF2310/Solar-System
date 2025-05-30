package org.example.solarsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * The Registry class maintains a list of SolarSystem objects and
 * utilizes a Filemanager for file operations.
 */
public class Registry {
  private final List<SolarSystem> solarSystems; //= new ArrayList<>();
  private final Filemanager fileManager; //= new Filemanager();

  public Registry(List<SolarSystem> initialSystems) {
    this.solarSystems = new ArrayList<>(initialSystems);
    this.fileManager = new Filemanager();
  }


  


  /**
   * The function `addSolarSystem` checks if a new solar system has
   * a unique star name before adding it to the list of existing solar systems.
   *
   */
  public void addSolarSystem(SolarSystem solarSystem) {
    for (SolarSystem existingSystem : solarSystems) {
      String existingName = existingSystem.getStar().getName();
      String newName = solarSystem.getStar().getName();
      if (existingName.equalsIgnoreCase(newName)) {
        throw new IllegalArgumentException("Star name must be unique");
      }
    }
    solarSystems.add(solarSystem); // add if no duplicates

  }

  /**
   * The function returns
   * a new ArrayList containing all SolarSystem objects in the solarSystems list.
   *
   * @return A new ArrayList containing the elements of the solarSystems list is being returned.
   */
  public List<SolarSystem> getSolarSystems() {
    return new ArrayList<>(solarSystems);
  }

  /**
   * The function sorts a list of SolarSystem objects 
   * based on the average radius of their stars in ascending order.
   *
   * @return returns a List of SolarSystem objects sorted in ascending order
   *      based on the average radius of the stars in each SolarSystem.
   */
  public List<SolarSystem> getSolarSystemsSortedByStarRadius() {
    List<SolarSystem> sorted = new ArrayList<>(solarSystems);
    for (int i = 0; i < sorted.size() - 1; i++) {
      for (int j = 0; j < sorted.size() - i - 1; j++) {
        int radius1 = sorted.get(j).getStar().getAvgRadiusInKm();
        int radius2 = sorted.get(j + 1).getStar().getAvgRadiusInKm();
        
        if (radius1 > radius2) {
          SolarSystem temp = sorted.get(j);
          sorted.set(j, sorted.get(j + 1));
          sorted.set(j + 1, temp);
        }
      }
    }
    return sorted;

  }


  /**
   * The function sorts a list of SolarSystem objects based on
   * the name of the star associated with each SolarSystem in a case insensitive way.
   *
   * @return returns a List of SolarSystem objects sorted
   *     alphabetically by the name of the star in each SolarSystem.
   */
  public List<SolarSystem> getSolarSystemsSortedByName() {
    List<SolarSystem> sorted = new ArrayList<>(solarSystems);
    for (int i = 0; i < sorted.size() - 1; i++) {
      for (int j = 0; j < sorted.size() - i - 1; j++) {
        String name1 = sorted.get(j).getStar().getName().toLowerCase();
        String name2 = sorted.get(j + 1).getStar().getName().toLowerCase();
        
        if (name1.compareTo(name2) > 0) {
          SolarSystem temp = sorted.get(j);
          sorted.set(j, sorted.get(j + 1));
          sorted.set(j + 1, temp);
        }
      }
    }
    return sorted;

  }

  /**
   * This method removes a solar system from a collection based on the name of its star.
   *
   */
  public boolean removeSolarSystem(String starName) {
    return solarSystems.removeIf(s ->
        s.getStar().getName().equalsIgnoreCase(starName)
    );
  }






  /**
   * The load function clears the existing list of solar systems and
   * adds all solar systems loaded from a file, handling any exceptions that occur.
   */
  public void load() {
    try {
      solarSystems.clear();
      solarSystems.addAll(fileManager.load());
    } catch (Exception e) {
      System.err.println("Load error: " + e.getMessage());
    }
  }

  /**
   * The save function attempts to save the solar systems data using a file manager and
   * handles any exceptions by printing an error message and throwing a RuntimeException.
   */
  public void save() {
    try {
      fileManager.save(solarSystems);
    } catch (Exception e) {
      System.err.println("Save error: " + e.getMessage());
      throw new RuntimeException("Critical save failure", e);
    }
  }

}