package jb.games.tickettoride.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import jb.games.tickettoride.tools.DevTools;

import java.util.ArrayList;
import java.util.List;

public class RailRoad{

    private final City departureCity, destinationCity;
    private final int distance;
    private final List<Rail> rails;
    private List<Circle> selectCircles;

    private boolean selected;

    public RailRoad(City departureCity, City destinationCity, List<Rail> tempRails) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        selectCircles = new ArrayList<>();
        distance = tempRails.size();
        selected = false;
        rails = new ArrayList<>();
        for(Rail rail : tempRails) {
            Rail newRail = new Rail(rail.getX(), rail.getY(), rail.getRotation());
            newRail.setEditMode();
            rails.add(newRail);
            Circle circle = new Circle();
            circle.setRadius(40);
            circle.setPosition(rail.getX()+20, rail.getY()+20);
            selectCircles.add(circle);
        }
    }



    private float getAngle(Vector2 origin, Vector2 target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - origin.y, target.x - origin.x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }


    public void update(float delta) {
        selected = false;
        for(Rail rail : rails) {
            rail.update(delta);
            rail.setSelected(false);
        }

        for(Circle c : selectCircles) {
            if(c.contains(DevTools.getMouse())) {
                selected = true;
            }
        }

        for(Rail rail : rails) {
            rail.update(delta);
            rail.setSelected(true);
        }

    }

    public void render(SpriteBatch batch) {
        for(Rail rail : rails) {
            rail.render(batch);
        }

    }

    public void dispose() {

    }

    public City getDeparture() {
        return departureCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getHoverInfo() {
        return "Rail road from " + departureCity.getName() + " to " + destinationCity.getName() +"\n" +
                "Distance: " + distance;
    }

    public RailReader getSave() {
        return new RailReader(departureCity,destinationCity, rails);
    }
}
