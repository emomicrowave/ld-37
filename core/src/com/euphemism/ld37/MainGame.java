package com.euphemism.ld37;

import com.euphemism.ld37.scenes.*;
import com.euphemism.ld37.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
 
    
    private Viewport viewport;
    private OrthographicCamera camera;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            
            viewport = new ScreenViewport(camera);
            viewport.setScreenPosition(0, 0);
            
            SceneManager.titleScene = new TitleScene(batch, viewport);
            SceneManager.datingScene = new DatingScene(batch, viewport);
            SceneManager.currentScene = SceneManager.titleScene;
        
	}

	@Override
	public void render () {
            // important stuff I don't really understand
            Gdx.gl.glClearColor(0.6f, 0.6f, 0.85f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            
            // draw
            SceneManager.currentScene.draw();
            
            camera.update();
        }
	
	@Override
	public void dispose () {
            batch.dispose();
	}
        
        // called when a window resize happens
        public void resize(int width, int height) {
            SceneManager.currentScene.setScreenResolution(width, height);
        }
}
