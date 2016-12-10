package com.euphemism.ld37;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
        GameScreen currentGameScreen;
 
    
    private Viewport viewport;
    private OrthographicCamera camera;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            
            viewport = new FitViewport(1024, 600);
            viewport.setScreenPosition(0, 0);
            
            currentGameScreen = new TitleScreen(batch, viewport);
        
	}

	@Override
	public void render () {
            // important stuff I don't really understand
            Gdx.gl.glClearColor(0.4f, 0.4f, 0.65f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            
            // draw
            currentGameScreen.draw();
            
            camera.update();
        }
	
	@Override
	public void dispose () {
            batch.dispose();
	}
        
        // called when a window resize happens
        public void resize(int width, int height) {
            currentGameScreen.setScreenResolution(width, height);
        }
}
