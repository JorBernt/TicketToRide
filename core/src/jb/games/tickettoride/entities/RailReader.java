package jb.games.tickettoride.entities;

import java.util.HashMap;
import java.util.List;

public class RailReader {

    public String departureCity;
    public String destinationCity;
    public List<Rail> rails;
    public HashMap<String, String> data;

    public RailReader() {}

    public RailReader(City departureCity, City destinationCity, List<Rail> rails) {
        this.departureCity = departureCity.getName();
        this.destinationCity = destinationCity.getName();
        this.rails = rails;
    }



    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public void putFloat (String key, float value) {
        data.put(key, "" + value);
    }

    public void putInt (String key, int value) {
        data.put(key, "" + value);
    }

    public void putBoolean (String key, boolean value) {
        data.put(key, "" + value);
    }

    public void putString (String key, String value) {
        data.put(key, value);
    }

    public float getFloat (String key, float defaultValue) {
        if (data.containsKey(key)) {
            try {
                return Float.parseFloat(data.get(key));
            } catch (Exception e) {
                return defaultValue;
            }
        } else
            return defaultValue;
    }

    public int getInt (String key, int defaultValue) {
        if (data.containsKey(key)) {
            try {
                return Integer.parseInt(data.get(key));
            } catch (Exception e) {
                return defaultValue;
            }
        } else
            return defaultValue;
    }

    public boolean getBoolean (String key, boolean defaultValue) {
        if (data.containsKey(key)) {
            try {
                return Boolean.parseBoolean(data.get(key));
            } catch (Exception e) {
                return defaultValue;
            }
        } else
            return defaultValue;
    }

    public String getString (String key, String defaultValue) {
        if (data.containsKey(key)) {
            return data.get(key);
        } else
            return defaultValue;
    }



}
