package org.example.solarsystem.constants;



/**
 * The class is  defining an enum  which contains
 * constants for various limits related to heavenly bodies such as stars, planets, and moons.
 * Each constant has an associated integer value representing a specific limit.
 *
 */
public enum HeavenlyBodyLimits {
  MINSTAR_RADIUS(100000),
  MAXSTAR_RADIUS(2400000),
  MINPLANET_RADIUS(2000),
  MAXPLANET_RADIUS(100000),
  MINPLANET_ORBIT_RADIUS(10000),
  MINMOON_RADIUS(6),
  MAXMOON_RADIUS(5000),
  MINMOON_ORBIT_RADIUS(100);

  private final int value;

  private HeavenlyBodyLimits(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  

}