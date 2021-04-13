package jb.games.tickettoride.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;
import java.util.List;

public class City  {
    private String name;
    private final List<City> adjacentCities;
    private final Texture texture;
    private final Texture textureSelected;
    private final Vector2 pos;
    private final Image image;

    private boolean selected;

    public City(float x, float y, String name) {
        pos = new Vector2(x,y);
        this.name = name;
        selected = false;
        adjacentCities = new ArrayList<>();
        texture = new Texture("city.png");
        textureSelected = new Texture("city_selected.png");
        image = new Image(texture);
        image.setOrigin(8,8);
        image.setPosition(x,y);
    }

    public void update(float delta) {
        float mX = Gdx.input.getX();
        float mY = Gdx.graphics.getHeight()-Gdx.input.getY();
        double deltaX = Math.abs(getX()+8-mX);
        double deltaY = Math.abs(getY()+8-mY);
        double dist = Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
        selected = dist < 8;

        if(selected) image.setDrawable(new SpriteDrawable(new Sprite(textureSelected)));
        else image.setDrawable(new SpriteDrawable(new Sprite(texture)));


    }

    public void render(SpriteBatch batch) {
        image.draw(batch,1);
    }

    public void dispose() {

    }

    public String getName() {
        return name;
    }

    public void addAdjacent(List<City> cities) {
        for(Cities cityEnum : Cities.values()) {
            if(cityEnum.getName().equals(name)) {
                for(String cityName : cityEnum.getAdjacentCities()) {
                    for(City city : cities) {
                        if(city.getName().equals(cityName)) {
                            adjacentCities.add(city);
                            break;
                        }
                    }
                }
            }
        }
    }

    public List<City> getAdjacentCities() {
        return adjacentCities;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getInfo() {
        return "City: " + name;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public Vector2 getPos() {
        return pos;
    }

    public CityReader getSave() {
        return new CityReader(name, getX(),getY());
    }

    public void setName(String name) {
        this.name = name;
    }
}
