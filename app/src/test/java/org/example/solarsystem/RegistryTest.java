package org.example.solarsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistryTest {
  private Registry registry;

  @BeforeEach
  void setup() {
    registry = new Registry(new ArrayList<>());
  }

  @Test
  void addUniqueSolarSystem() {
    Star sun = new Star("Sun", 696340);
    SolarSystem solarSystem = new SolarSystem(sun);

    registry.addSolarSystem(solarSystem);
    assertEquals(1, registry.getSolarSystems().size());

  }

  @Test
  void removeSolarSystemSuccessfully() {
    Star sun = new Star("Sun", 696340);
    SolarSystem solarSystem = new SolarSystem(sun);
    registry.addSolarSystem(solarSystem);

    boolean removed = registry.removeSolarSystem("Sun");

    assertTrue(removed);
    assertEquals(0, registry.getSolarSystems().size());

  }

  @Test
  void removeNonExistentSolarSystemReturnsFalse() {
    boolean removed = registry.removeSolarSystem("Alpha Centauri");
    assertFalse(removed);

  }

  @Test
  void getSolarSystemsSortedByName() {
    Star alpha = new Star("Alpha", 500000);
    Star beta = new Star("Beta", 600000);
    Star gamma = new Star("Gamma", 400000);

    registry.addSolarSystem(new SolarSystem(beta));
    registry.addSolarSystem(new SolarSystem(gamma));
    registry.addSolarSystem(new SolarSystem(alpha));

    List<SolarSystem> sorted = registry.getSolarSystemsSortedByName();

    assertEquals("Alpha", sorted.get(0).getStar().getName());
    assertEquals("Beta", sorted.get(1).getStar().getName());
    assertEquals("Gamma", sorted.get(2).getStar().getName());

  }

  @Test
  void getSolarSystemsSortedByStarRadius() {
    Star smallStar = new Star("SmallStar", 400000);
    Star mediumStar = new Star("MediumStar", 500000);
    Star largeStar = new Star("LargeStar", 600000);

    registry.addSolarSystem(new SolarSystem(largeStar));
    registry.addSolarSystem(new SolarSystem(smallStar));
    registry.addSolarSystem(new SolarSystem(mediumStar));

    List<SolarSystem> sorted = registry.getSolarSystemsSortedByStarRadius();

    assertEquals("SmallStar", sorted.get(0).getStar().getName());
    assertEquals("MediumStar", sorted.get(1).getStar().getName());
    assertEquals("LargeStar", sorted.get(2).getStar().getName());

  }

  @Test
  void testSaveAndLoad() {
    Star sun = new Star("Sun", 696340);
    SolarSystem solarSystem = new SolarSystem(sun);
    registry.addSolarSystem(solarSystem);
    assertDoesNotThrow(() -> registry.save());
    registry.getSolarSystems().clear();
    assertDoesNotThrow(() -> registry.load());

    assertEquals(1, registry.getSolarSystems().size());
    assertEquals("Sun", registry.getSolarSystems().get(0).getStar().getName());

  }
}