package jb.games.tickettoride.entities;

import java.util.HashMap;

public class CityReader {

    public String name;
    public float x, y;
    public HashMap<String, String> data;

    public CityReader() {}

    public CityReader(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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
