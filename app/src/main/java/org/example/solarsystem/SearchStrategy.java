package org.example.solarsystem;

/**
 * This interface declares a single method `matches`
 * that takes a HeavenlyBody object as a parameter and returns a boolean value.
 *  which defines the logic for determining
 * whether a given HeavenlyBody object matches certain criteria.
 *
 * @return      return description
 */

public interface SearchStrategy {
  boolean matches(HeavenlyBody body);
}
