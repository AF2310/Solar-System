package org.example.solarsystem;

/**
 *Represents a heavenly body with a name and an average radius.
 *provides functionality for all types of heavenly bodies.
 */

public abstract class HeavenlyBody implements Comparable<HeavenlyBody> {

  private String name; // name field
  private int avgRadiusInKm; // radius field

  // constructor tor for heavenly body
  /**
   *.
   * Constructs a HeavenlyBody with a specific name and radius.
   *
   * @description
   * @param  name          name of heavenly body
   * @param  avgRadiusInKm average radius
   *
   */
  protected HeavenlyBody(String name, int avgRadiusInKm) {
    setName(name);
    setAvgRadiusInKm(avgRadiusInKm);
  }

  /**
   *.
   *
   * @description gets name of heavenly body
   * @return   return heavenlybody name
   */
  public String getName() {
    return name;
  }

  /**
   *.
   *
   * @description get average radius of HB
   * @return   return HB radius
   */
  public int getAvgRadiusInKm() {
    return avgRadiusInKm;
  }

  /**
   *.
   *
   * @description sets and validates the name of the heavenlybody
   * @param  newName the new validated name to assign
   */
  private void setName(String newName) {
    this.name = checkName(newName); // validates before setting

  }

  /**
   *.
   *
   * @description sets and validates average radius of the heavenlybody
   * @param  avgRadiusInKm the new validated radius to assign
   */
  private void setAvgRadiusInKm(int avgRadiusInKm) {
    this.avgRadiusInKm = checkAvgRadiusInKm(avgRadiusInKm);
  }

  // abstract method to validate and return correct radius
  /**
   *.
   *
   * @description checkAvgRadiusInKm description
   * @param  radius radius description
   * @return        return description
   */
  protected abstract int checkAvgRadiusInKm(int radius);

  
  /**
   *.
   *
   * @description abstract method that validates and returns correct name
   * @param  name inputted name
   * @return      returns validated name
   */
  protected abstract String checkName(String name);

  // returns the string representation of the heavenly body
  //protected abstract String getType();
  
  /**
   *.
   *
   * @description compares this heavenly body to another based on radius
   *      implements the comparable interface for sorting objects by size
   * @param  other the other heavenly body to compare to
   * @return       a value where its sign is determined by the comparison beetwen heavenly bodies
   */
  @Override
  public int compareTo(HeavenlyBody other) {
    return Integer.compare(this.avgRadiusInKm, other.avgRadiusInKm);
  }

  /**
   *.
   *
   * @description returns a string representation of the heavenly body
   * @return   formatted string showing type,name and radius
   */
  @Override
  public String toString() {
    return getClass().getSimpleName() + ":" + name + "average radius" + avgRadiusInKm + "km";

  }
}
