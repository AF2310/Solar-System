package org.example.solarsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileManagerTest {
  @TempDir
  Path tempDir;

  @Test
  void loadNonExistentFile() throws IOException {
    Path filePath = tempDir.resolve("new.data");
    File testFile = filePath.toFile();

    // Verify file doesn't exist initially
    assertFalse(testFile.exists());
    Filemanager fm = new Filemanager(filePath.toString());
    List<SolarSystem> result = fm.load();
    assertTrue(testFile.exists());
    assertTrue(result.isEmpty());

  }

  @Test
  void saveAndLoadSolarSystem() {
    Path filePath = tempDir.resolve("system.data");
    Filemanager fm = new FilemanagerWithPath(filePath.toString());

    Star sun = new Star("Sun", 696340);
    Planet earth = new Planet("Earth", 6371, 149_600_000);
    Moon moon = new Moon("Moon", 1737, 384_400);
    earth.addMoon(moon);

    SolarSystem solarSystem = new SolarSystem(sun);
    solarSystem.addPlanet(earth);

    // Save and load
    fm.save(List.of(solarSystem));
    List<SolarSystem> loadedSystems = fm.load();

    assertEquals(1, loadedSystems.size(), "Should load 1 system");
    SolarSystem loadedSystem = loadedSystems.get(0);

    assertEquals("Sun", loadedSystem.getStar().getName());
    assertEquals(1, loadedSystem.getPlanets().size(), "1 planet expected");

    Planet loadedPlanet = loadedSystem.getPlanets().get(0);
    assertEquals("Earth", loadedPlanet.getName());
    assertEquals(1, loadedPlanet.getMoons().size(), "1 moon expected");

    Moon loadedMoon = loadedPlanet.getMoons().get(0);
    assertEquals("Moon", loadedMoon.getName());

  }

  @Test
  void loadInvalidData() throws IOException {
    Path filePath = tempDir.resolve("invalid.data");
    Files.write(filePath, List.of(
        "BadStarData",
        "-Planet:Missing:Orbit:Data",
        "--Moon:No:Parent:1737:385000",
        "ValidStar:100000",
        "-ValidPlanet:5000:1000000",
        "---InvalidHierarchy:1234:5678"));

    // Filemanager fm = new FilemanagerWithPath(filePath.toString());
    Filemanager fm = new Filemanager(filePath.toString());

    List<SolarSystem> result = fm.load();
    assertEquals(1, result.size(), "Should load valid star");
    assertEquals(1, result.get(0).getPlanets().size(), "Should load valid planet");
    assertEquals(0, result.get(0).getPlanets().get(0)
        .getMoons().size(), "Invalid moon should be skipped");

  }

  @Test
  void loadEmptyFile() throws IOException {
    Path filePath = tempDir.resolve("empty.data");
    Files.createFile(filePath);
    // Filemanager fm = new FilemanagerWithPath(filePath.toString());
    Filemanager fm = new Filemanager(filePath.toString());
    List<SolarSystem> result = fm.load();

    assertTrue(result.isEmpty(), "empty file should empty list");

  }

  @Test
  void saveFail() {
    Path filePath = tempDir.resolve("readonly.data");
    File testFile = filePath.toFile();

    assertDoesNotThrow(() -> {
      testFile.createNewFile();
      testFile.setReadOnly();
    }, "Test setup should succeed");

    Filemanager fm = new Filemanager(filePath.toString());
    SolarSystem system = new SolarSystem(new Star("TestStar", 100000));

    Exception exception = assertThrows(RuntimeException.class,
        () -> fm.save(List.of(system)),
        "Should throw when saving to read-only file");

    assertTrue(exception.getCause() instanceof IOException,
        "Root cause should be IOException");

    assertTrue(testFile.exists(), "File should still exist after failed save");
    assertTrue(testFile.length() == 0, "File should remain empty after failed save");

  }

  private class FilemanagerWithPath extends Filemanager {
    private final String filePath;

    public FilemanagerWithPath(String path) {
      this.filePath = path;
    }

    @Override
    protected String getFilename() {
      return filePath;
    }

    protected FileWriter createWriter() throws IOException {
      return new FileWriter(getFilename());
    }
  }

}