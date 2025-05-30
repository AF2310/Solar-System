
package org.example.solarsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SolarSystemAppTest extends SolarSystemApp {
  private Registry registry;
  private Filemanager fileManager;
  private SolarSystemApp app;
  private SolarSystem testSystem;

  @TempDir
  Path tempDir;

  @BeforeEach
  void setup() {
    Path testFile = tempDir.resolve("solarsystems.data");
    fileManager = new Filemanager(testFile.toString());
    registry = new Registry(fileManager.load());
    app = new SolarSystemApp();
    app.setCurrentSystem(testSystem);

  }

  @Test
  void addSolarSystemToRegistry() throws IOException {
    SolarSystem system = createTestSystem("Sol", 696340);
    registry.addSolarSystem(system);
    assertEquals(1, registry.getSolarSystems().size());

    assertTrue(registry.removeSolarSystem("Sol"));
    assertTrue(registry.getSolarSystems().isEmpty());

  }

  @Test
  void saveAndLoadSolarSystems() throws IOException {
    Path testFile = tempDir.resolve("solarsystems.data");

    Registry registry = new Registry(new ArrayList<>());

    SolarSystem system = new SolarSystem(new Star("Beta", 200_000));
    registry.addSolarSystem(system);

    registry.save();

    Path defaultFile = Path.of("solarsystems.data");
    Files.move(defaultFile, testFile, StandardCopyOption.REPLACE_EXISTING);

    Filemanager testFileManager = new Filemanager(testFile.toString());
    List<SolarSystem> loadedSystems = testFileManager.load();
    Registry newRegistry = new Registry(loadedSystems);

    assertEquals(1, newRegistry.getSolarSystems().size());
    assertEquals("Beta", newRegistry.getSolarSystems().get(0).getStar().getName());

    Files.deleteIfExists(testFile);

  }

  @Test
  void sortSolarSystemsByStarName() {
    registry.addSolarSystem(createTestSystem("Zeta", 100000));
    registry.addSolarSystem(createTestSystem("Beta", 200000));
    registry.addSolarSystem(createTestSystem("Alpha", 300000));

    List<SolarSystem> sorted = registry.getSolarSystemsSortedByName();
    assertEquals("Alpha", sorted.get(0).getStar().getName());
    assertEquals("Beta", sorted.get(1).getStar().getName());
    assertEquals("Zeta", sorted.get(2).getStar().getName());

  }

  @Test
  void sortSolarSystemsByStarRadius() {
    registry.addSolarSystem(createTestSystem("Large", 300000));
    registry.addSolarSystem(createTestSystem("Medium", 200000));
    registry.addSolarSystem(createTestSystem("Small", 100000));

    List<SolarSystem> sorted = registry.getSolarSystemsSortedByStarRadius();
    assertEquals(100000, sorted.get(0).getStar().getAvgRadiusInKm());
    assertEquals(200000, sorted.get(1).getStar().getAvgRadiusInKm());
    assertEquals(300000, sorted.get(2).getStar().getAvgRadiusInKm());

  }

  @Test
  void handleFileLoadError() {
    Filemanager corruptManager = new Filemanager("invalid/path.data");
    Registry corruptRegistry = new Registry(corruptManager.load());
    assertTrue(corruptRegistry.getSolarSystems().isEmpty());

  }

  @Test
  void deleteSolarSystem() {
    SolarSystem system = createTestSystem("Andromeda", 500000);
    Planet planet = new Planet("Perseus", 10000, 250000);
    planet.addMoon(new Moon("Titan", 5000, 10000));
    system.addPlanet(planet);
    registry.addSolarSystem(system);

    assertTrue(registry.removeSolarSystem("Andromeda"));
    assertTrue(registry.getSolarSystems().isEmpty());
    assertEquals(0, registry.getSolarSystems().size());

  }

  private SolarSystem createTestSystem(String name, int radius) {
    return new SolarSystem(new Star(name, radius));

  }

  private SolarSystem createTestSystemWithPlanets() {
    SolarSystem system = createTestSystem("Sol", 696340);
    Planet earth = new Planet("Earth", 6371, 149600000);
    Planet mars = new Planet("Mars", 3389, 227900000);
    earth.addMoon(new Moon("moon", 1737, 384400));

    system.addPlanet(earth);
    system.addPlanet(mars);
    return system;
  }

  @Test
  void moveMoon_Success() {
    SolarSystem system = new SolarSystem(new Star("Sol", 696340));
    Planet earth = new Planet("Earth", 6371, 149600000);
    Planet mars = new Planet("Mars", 3389, 227900000);
    Moon luna = new Moon("Luna", 1737, 384400);
    earth.addMoon(luna);

    system.addPlanet(earth);
    system.addPlanet(mars);
    /*
     * Planet earth = new Planet("Earth", 6371, 149600000);
     * Planet mars = new Planet("Mars", 3389, 227900000);
     * earth.addMoon(new Moon("Luna", 1737, 384400));
     * system.addPlanet(earth);
     * system.addPlanet(mars);
     */
    /*
     * SolarSystem system = createTestSystemWithPlanets();
     * Planet earth = system.getPlanets().get(0);
     * Planet mars = system.getPlanets().get(1);
     */
    /*
     * Planet earth = system.getPlanets().get(0);
     * Planet mars = system.getPlanets().get(1);
     * Moon luna = earth.getMoons().get(0);
     */

    // String testInput = "1\n1\n"; // simulated input
    // String testInput = "1\n" + "1\n";
    // String testInput = "1\n" + "1\n";
    String testInput = "1\n2\n";

    InputStream originalIn = System.in;
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    // System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    try {
      // System.setIn(new ByteArrayInputStream(testInput.getBytes()));
      SolarSystemApp app = new SolarSystemApp();
      // SolarSystem system = createTestSystemWithPlanets();
      app.setCurrentSystem(system);
      app.moveMoon(earth);

      assertEquals(0, earth.getMoons().size(), "Earth should have no moons after move");
      assertEquals(1, mars.getMoons().size(), "Mars should have 1 moon after move");
      assertSame(luna, mars.getMoons().get(0), "Mars should contain the moved moon object");

      // assertEquals(0, earth.getMoons().size());
      // assertEquals(1, mars.getMoons().size());

      // app.moveMoon(earth);
      // assertEquals(0, earth.getMoons().size(), "Earth should have no moons");
      // assertEquals(1, mars.getMoons().size(), "Mars should have 1 moon");
      // assertEquals("Luna", mars.getMoons().get(0).getName());
    } finally {
      System.setIn(originalIn);
    }
    /*
     * app.currentSystem = system;
     * app.moveMoon(system.getPlanets().get(0));
     */

    /*
     * app.currentSystem = system;
     * app.moveMoon(system.getPlanets().get(0));
     */

    /*
     * app.moveMoon(earth);
     * assertEquals(0, earth.getMoons().size(),
     * "Earth should have no moons after move");
     * assertEquals(1, mars.getMoons().size(),
     * "Mars should have 1 moon after move");
     * assertEquals(luna, mars.getMoons().get(0),
     * "The moon should be the same object");
     */

  }

  /*
   * private void simulateInput(String input) {
   * InputStream in = new ByteArrayInputStream(input.getBytes());
   * System.setIn(in);
   * }
   */

  @Test
  void editPlanet_UpdatesNameAndRadiusSuccessfully() {
    SolarSystem system = new SolarSystem(new Star("Sol", 696340));
    Planet earth = new Planet("Earth", 6371, 149600000);
    Moon moon = new Moon("Luna", 1737, 384400);
    earth.addMoon(moon);
    system.addPlanet(earth);

    String input = "1\nTerra\n7000\n0\n\n";
    InputStream originalIn = System.in;

    try {
      System.setIn(new ByteArrayInputStream(input.getBytes()));

      SolarSystemApp app = new SolarSystemApp();
      app.setCurrentSystem(system);

      app.editPlanet(system);

    } finally {
      System.setIn(originalIn);
    }

    List<Planet> planets = system.getPlanets();
    assertEquals(1, planets.size());
    Planet updated = planets.get(0);
    assertEquals("Terra", updated.getName());
    assertEquals(7000, updated.getAvgRadiusInKm());
    assertEquals(149600000.0, updated.getAvgOrbitRadiusInKm(), 0.1);
    assertEquals(1, updated.getMoons().size());
    assertEquals("Luna", updated.getMoons().get(0).getName());

  }

  @Test
  void editStar_UpdatesNameAndRadiusSuccessfully() {
    SolarSystem system = new SolarSystem(new Star("Sol", 696340));
    Planet earth = new Planet("Earth", 6371, 149600000);
    system.addPlanet(earth);
    String input = "NewSol\n700000\n";
    InputStream originalIn = System.in;
    try {
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      SolarSystemApp app = new SolarSystemApp();
      app.setCurrentSystem(system);
      app.editStar(system);

      SolarSystem updatedSystem = app.getCurrentSystem();

      Star updatedStar = updatedSystem.getStar();
      assertEquals("NewSol", updatedStar.getName());
      assertEquals(700000, updatedStar.getAvgRadiusInKm());

      assertEquals(1, system.getPlanets().size());
      assertEquals("Earth", system.getPlanets().get(0).getName());

    } finally {
      System.setIn(originalIn);
    }

  }

  @Test
  void editMoon_UpdatesNameAndRadiusSuccessfully() {
    SolarSystem system = new SolarSystem(new Star("Sol", 696340));
    Planet earth = new Planet("Earth", 6371, 149600000);
    Moon moon = new Moon("Luna", 1737, 384400);
    earth.addMoon(moon);
    system.addPlanet(earth);

    String input = "1\nNewLuna\n2000\n5000\n";
    InputStream originalIn = System.in;

    try {
      System.setIn(new ByteArrayInputStream(input.getBytes()));

      SolarSystemApp app = new SolarSystemApp();
      app.setCurrentSystem(system);
      app.editMoon(earth);
    } finally {
      System.setIn(originalIn);
    }
    List<Moon> moons = earth.getMoons();
    assertEquals(1, moons.size());
    Moon updatedMoon = moons.get(0);
    assertEquals("NewLuna", updatedMoon.getName());
    assertEquals(2000, updatedMoon.getAvgRadiusInKm());
    assertEquals(5000.0, updatedMoon.getAvgOrbitRadiusInKm(), 0.1);

  }

  @Test
  void compositeSearchStrategy() {
    Planet earth = new Planet("Earth", 6371, 149600000);
    earth.addMoon(new Moon("Luna", 1737, 384400));

    Planet mars = new Planet("Mars", 3389, 227900000);
    mars.addMoon(new Moon("Phobos", 11, 9376));
    mars.addMoon(new Moon("Deimos", 6, 23460));

    Planet tellus = new Planet("Tellus", 9000, 200000000);
    tellus.addMoon(new Moon("Titan", 5000, 1200000));
    tellus.addMoon(new Moon("Ganymede", 4200, 1070000));

    List<HeavenlyBody> bodies = new ArrayList<>();
    bodies.add(earth);
    bodies.add(mars);
    bodies.add(tellus);

    SearchStrategy strategy = new AdittionalSearch(List.of(
        new NameSearch("Tellus"),
        new MoonCountSearch(2)));

    SearchContext context = new SearchContext();
    List<HeavenlyBody> result = context.search(bodies, strategy);

    assertEquals(1, result.size());
    assertEquals("Tellus", result.get(0).getName());

  }

  @Test
  void altSearch() {
    Planet venus = new Planet("Venus", 6051, 108200000);
    Planet saturn = new Planet("Saturn", 5232, 1427000000);
    saturn.addMoon(new Moon("Titan", 4150, 122187000));
    List<HeavenlyBody> bodies = List.of(venus, saturn);

    SearchStrategy strategy = new Altsearch(List.of(
        new NameSearch("Venus"),
        new MoonCountSearch(1)));

    SearchContext context = new SearchContext();
    List<HeavenlyBody> result = context.search(bodies, strategy);

    assertEquals(2, result.size());
  }

}