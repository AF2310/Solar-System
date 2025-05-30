package org.example.solarsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolarSystemTest {
  private SolarSystem solarSystem;

  @BeforeEach // runs before each package
  void setup() {
    Star sun = new Star("Sun", 696340);
    solarSystem = new SolarSystem(sun);

  }
  
  @Test
  void addPlanetToSolarSystem() {
    Planet planet = new Planet("Earth", 6371, 20000);
    solarSystem.addPlanet(planet);

    assertEquals(6371, planet.getAvgRadiusInKm());
    assertEquals(20000, planet.getAvgOrbitRadiusInKm());
    assertEquals(1, solarSystem.getPlanets().size());
    assertEquals("Earth", solarSystem.getPlanets().get(0).getName());
  }

  @Test
  void preventAddingPlanetWithInvalidOrbit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      Planet invalidPlanet = new Planet("Mercury", 2439, 5000); // small orbit
      solarSystem.addPlanet(invalidPlanet);
    });

    assertEquals("Planet orbit must be atleast 18000", exception.getMessage());
  }

  @Test
  void removePlanetAndEnsureMoonsAreRemoved() {
    Planet mars = new Planet("Mars", 3389, 23000);
    Moon phobos = new Moon("Phobos", 11, 9378);
    mars.addMoon(phobos);
    solarSystem.addPlanet(mars);

    assertEquals(1, mars.getMoons().size());

    boolean removed = solarSystem.removePlanet("Mars");

    assertTrue(removed);
    assertEquals(0, solarSystem.getPlanets().size());
    assertFalse(solarSystem.getPlanets().contains(mars));

  }


  @Test
  void preventAddingNullPlanet() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      solarSystem.addPlanet(null);
    });
    assertEquals("cannot be null.", exception.getMessage());

  }

  @Test
  void planetsSortedBySize() {
    Planet mars = new Planet("Mars", 3389, 22790);
    Planet earth = new Planet("Earth", 6371, 149600);
    solarSystem.addPlanet(mars);
    solarSystem.addPlanet(earth);

    assertEquals("Mars", solarSystem.getPlanetsSortedBySize().get(0).getName());
    assertEquals("Earth", solarSystem.getPlanetsSortedBySize().get(1).getName());
  }

  @Test
  void planetsSortedByOrbit() {
    Planet mars = new Planet("Mars", 3389, 22790);
    Planet earth = new Planet("Earth", 6371, 149600);
    solarSystem.addPlanet(mars);
    solarSystem.addPlanet(earth);

    assertEquals("Mars", solarSystem.getPlanetsSortedByOrbit().get(0).getName());
    assertEquals("Earth", solarSystem.getPlanetsSortedByOrbit().get(1).getName());
  }

  @Test
  void preventAddingPlanetWithEmptyName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new Planet("", 5000, 18000);
    });

    assertEquals("planet must have a name", exception.getMessage());
  }

  @Test
  void toStringFormatCheck() {
    Planet earth = new Planet("Earth", 6371, 149600);
    Moon moon = new Moon("Luna", 1737, 384);
    earth.addMoon(moon);
    solarSystem.addPlanet(earth);

    String expected = """
        Sun:696340
        -Earth:6371:149600.0
        --Luna:1737:384.0
        """;

    assertEquals(expected.trim(), solarSystem.toString().trim());
  }




        
  
}