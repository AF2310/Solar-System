package org.example.solarsystem;

/**
 * The NameSearch class implements the SearchStrategy interface
 * to match a `HeavenlyBody` object based on its name.
 */
public class NameSearch implements SearchStrategy {
  private final String targetName;

  public NameSearch(String name) {
    this.targetName = name;
  }

  @Override
  public boolean matches(HeavenlyBody body) {
    return body.getName().equalsIgnoreCase(targetName);
  }
  
}
