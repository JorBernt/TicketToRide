package jb.games.tickettoride.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import jb.games.tickettoride.GameLauncher;
import jb.games.tickettoride.entities.CityLoader;
import jb.games.tickettoride.entities.CityManager;
import jb.games.tickettoride.tools.TextInputListener;

public class GameScreen implements Screen {

    private final SpriteBatch batch;
    private final GameLauncher gameFile;
    private final Texture bg;
    private final BitmapFont font;
    private final CityManager cityManager;
    private Viewport viewport;
    private final OrthographicCamera camera;


    public GameScreen(SpriteBatch batch, GameLauncher gameFile) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        this.batch = batch;
        this.gameFile = gameFile;
        font = new BitmapFont();

        cityManager = new CityManager();
        bg = new Texture("map.png");

    }
    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            CityLoader.saveCities("Cities", cityManager.getCities());
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if(cityManager.isEntitySelected()) {
                cityManager.deleteCity(cityManager.getSelectedCity());
            }
            else {
                cityManager.createCity(Gdx.input.getX()-8, Gdx.graphics.getHeight()-Gdx.input.getY()-8);
            }

        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            if(cityManager.isEntitySelected()) {
                TextInputListener listener = new TextInputListener();
                listener.addCity(cityManager.getSelectedCity());
                Gdx.input.getTextInput(listener, "Add city name", "", "City name");
            }
        }

        cityManager.update(delta);
        batch.begin();

        batch.draw(bg, 0,0);
        cityManager.render(batch);

        if(cityManager.isEntitySelected()) {
            font.draw(batch, cityManager.getHoverInfo(), Gdx.input.getX()+10, Gdx.graphics.getHeight()-Gdx.input.getY());

        }
        //font.draw(batch, Gdx.input.getX()+" " +  (Gdx.graphics.getHeight()-Gdx.input.getY()),Gdx.input.getX()+10, Gdx.graphics.getHeight()-Gdx.input.getY());

        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
