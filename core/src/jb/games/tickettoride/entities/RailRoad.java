package jb.games.tickettoride.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class RailRoad{

    private final City departureCity, destinationCity;
    private final int distance;
    private final List<Rail> rails;
    private final Rectangle selectRect;
    private boolean selected;

    public RailRoad(City departureCity, City destinationCity, int distance) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.distance = distance;
        selected = false;

        selectRect = new Rectangle();
        rails = new ArrayList<>();
        createRail();
    }

    public void createRail() {
        double deltaX = Math.abs(departureCity.getX()-destinationCity.getX());
        double deltaY = Math.abs(departureCity.getY()-destinationCity.getY());
        double delta = Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
        deltaX /= delta/35;
        deltaY /= delta/35;

        float x = 0f;
        float y = 0f;

        if(destinationCity.getX() < departureCity.getX()) {
            x = departureCity.getX()-(float)deltaX/2;
            deltaX *= -1;
        }
        else {
            x = departureCity.getX()+(float)deltaX/2;
        }
        if(destinationCity.getY() < departureCity.getY()) {
            y = departureCity.getY()-(float)deltaY/2;
            deltaY *= -1;
        }
        else {
            y = departureCity.getY()+(float)deltaY/2;
        }

        selectRect.x = x;
        selectRect.y = y;
        selectRect.width = (float)delta-16;
        selectRect.height = 16;

        for(int i = 0; i < distance; i++) {
            Rail rail = new Rail(x, y, getAngle(departureCity.getPos(), destinationCity.getPos()));
            rails.add(rail);
            x+=deltaX;
            y+=deltaY;
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
        float mX = Gdx.input.getX();
        float mY = Gdx.graphics.getHeight()-Gdx.input.getY();
        if(mX >= selectRect.x && mX <= selectRect.x+ selectRect.width && mY >= selectRect.y && mY <= selectRect.y+ selectRect.height) {
            for(Rail rail : rails) {
                rail.setSelected(true);
            }
            selected = true;
        }
        else {
            for(Rail rail : rails) {
                rail.setSelected(false);
            }
            selected = false;
        }
        for(Rail rail : rails) {
            rail.update(delta);
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
}
