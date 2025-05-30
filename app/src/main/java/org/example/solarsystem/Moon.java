package org.example.solarsystem;

import org.example.solarsystem.constants.HeavenlyBodyLimits;

/**
 * Represents a moon, which is a type of heavenly body.
 * It has a name, an average radius, and an orbital radius.
 * inherits methods of heavenly body abstract class
 */
public final class Moon extends HeavenlyBody {

  private final double avgOrbitRadiusInKm;

  //protected   Moon(String name,int avgOrbitRadiusInKm) {
  //this(name,avgOrbitRadiusInKm,100);
  //}
  /**
   *.
   *
   * @description Constructs a Moon object with name,average radius,orbitradius.
   * @param  name                name of the moon
   * @param  avgRadiusInKm      average radius
   * @param  avgOrbitRadiusInKm average orbit radius
   * @throw illegal argument exception if invalid orbit radius
   */
  protected Moon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    super(name, avgRadiusInKm);
    if (avgOrbitRadiusInKm < HeavenlyBodyLimits.MINMOON_ORBIT_RADIUS.getValue()) {
      throw new IllegalArgumentException("invalid radius");
    }
    this.avgOrbitRadiusInKm = avgOrbitRadiusInKm;
  }

  /**
   *.
   *
   * @description get the average orbit radius
   *
   * @return   returns average orbit radius
   */
  public double getAvgOrbitRadiusInKm() {
    return avgOrbitRadiusInKm;
  }

  /**
   *.
   *
   * @description validates and returns correct average radius
   * @param  radius inputted radius
   * @return        return validated radius
   */
  @Override
  protected int checkAvgRadiusInKm(int radius) {
    if (radius < HeavenlyBodyLimits.MINMOON_RADIUS.getValue()
          || radius > HeavenlyBodyLimits.MAXMOON_RADIUS.getValue()) {
      throw new IllegalArgumentException("invalid planet radius");
    }
    return radius;
  }

  /**
   *.
   *
   * @description validates name
   *
   * @param  name inputted
   * @return      return validated name
   */
  @Override
  protected String checkName(String name) {
    if (name == null || name.strip().isEmpty()) {
      throw new IllegalArgumentException("planet must have a name");
    }
    return name.strip();
  }

  //@Override
  //public int compareTo(HeavenlyBody other) {
  //return Integer.compare(this.getAvgRadiusInKm(),other.getAvgRadiusInKm());
  //}
  /**
   *.
   *
   * @description string representation of heavenly body
   * @return   returns string representation
   */
  @Override
  public String toString() {
    return "Moon:" + getName() + "average radius" + getAvgRadiusInKm();

  }

}
