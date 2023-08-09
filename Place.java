package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
//import java.util.Locale;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {}

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
    placeType = 0;
  }
  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription,
      final int setType) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
    placeType = setType;
  }

  private int placeType; // 0 = other, 1 = home, 2 = work, 3 = school

  public int getPlaceType() {
    return placeType;
  }

  // ID of the place
  private String id;

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude = 91;

  public double getLatitude() {
    return latitude;
  }

  private double longitude = 181;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() {
    return description;
  }

  public static List<Place> search(final List<Place> places, final String search) {
    if (places == null || search == null) {
      throw new IllegalArgumentException();
    }
    String trim = search.trim().toLowerCase();
    if (places.size() == 0 || trim.length() == 0) {
      return places;
    }
    for (Place p : places) {
      String d = "";
      for (int i = 0; i < p.description.length(); i++) {
        if (p.description.charAt(i) == '.' || p.description.charAt(i) == '!' || p.description.charAt(i) == '?'
            || p.description.charAt(i) == ',' || p.description.charAt(i) == ':' || p.description.charAt(i) == ';'
            || p.description.charAt(i) == '/') {
          d += " ";
        } else if (Character.isAlphabetic(p.description.charAt(i)) || Character.isDigit(p.description.charAt(i))
            || p.description.charAt(i) == ' ') {
          d += p.description.charAt(i);
        }
      }
      p.description = d;
    }
    List<Place> l = new ArrayList<Place>();
    for (Place p : places) {
      String[] words = p.description.split(" ");
      for (String s : words) {
        if (s.compareToIgnoreCase(trim) == 0) {
          l.add(p);
          break;
        }
      }

    }

    //public static boolean isWhitespace(char ch) {
      //return unmodified;
    //}
    return l;
  }

}
