package org.example.solarsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file  operations for solar system.
 * maintains hierarchical file format
 */
public class Filemanager {
  private String filename;
  
  
  public Filemanager() {
    this("solarsystems.data");
  }


  //.
  public Filemanager(String filename) {
    this.filename = filename;
  }

  /**
   * The `save` method writes a list of `SolarSystem` objects to a file using a `PrintWriter`.
   *
   * @param solarSystems A list of SolarSystem objects that need to be saved to a file.
   */

  public void save(List<SolarSystem> solarSystems) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
      for (SolarSystem system : solarSystems) {
        writer.print(system.toString());
      }
      
    } catch (IOException e) {
      throw new RuntimeException("Save operation failed", e);
    }
  }



  /**
  * The `load` function reads data from a file,
  *parses it to create a hierarchical structure of solar systems, stars, planets, and moons,
  *and returns a list of SolarSystem objects.
  *
  * @return The `load()` method returns a List of SolarSystem objects.
  */
  public List<SolarSystem> load() {
    List<SolarSystem> systems = new ArrayList<>();
    File file = new File(filename);

    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        System.err.println("Error creating file: " + e.getMessage());
      }
      return systems;
    }
    // uses in built buffered reader to read text in chunks than one character at a time
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      Star currentStar = null;
      Planet currentPlanet = null;
      SolarSystem currentSystem = null;
      // uses read line to process entire line at once,iterates line by line
      while ((line = reader.readLine()) != null) {
        try {

          // calculates hierarchy level by counting leading dashes
          int hierarchyLevel = 0;
          while (line.startsWith("-")) {
            line = line.substring(1);
            hierarchyLevel++;
          }

          // splits line into parts
          String[] parts = line.split(":");
          String name = parts[0];
          int radius = Integer.parseInt(parts[1]);
          // checks if hierarchical structure is maintained before creating object
          switch (hierarchyLevel) {
            case 0 -> {
              currentStar = new Star(name, radius);
              currentSystem = new SolarSystem(currentStar);
              systems.add(currentSystem);
            }
            case 1 -> {
              double orbitRadius = Double.parseDouble(parts[2]);
              currentPlanet = new Planet(name, radius, orbitRadius);
              currentSystem.addPlanet(currentPlanet);
            }
            case 2 -> {
              if (currentPlanet == null) {
                System.err.println("Orphan moon found: " + name);
                continue;
              }
              double orbitRadius = Double.parseDouble(parts[2]);
              Moon moon = new Moon(name, radius, orbitRadius);
              currentPlanet.addMoon(moon);
            }
            default -> System.err.println("wrong hierarchy level for: " + name);
          }
        }  catch (Exception e) {
          System.err.println("invalid line: " + line + " - " + e.getMessage());
        }
      }
    } catch (Exception e) {
      System.err.println("Error reading file.");
    }
    return systems;
  }
  /**
   * The function `getFilename` returns the value of the `filename` variable.
   *
   * @return The method is returning the value of the variable `filename`.
   */

  protected String getFilename() {
    return filename;
  }
  
}

