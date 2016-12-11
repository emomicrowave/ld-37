/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.euphemism.ld37.util.FontManager;

/**
 *
 * @author emilanov
 */
public abstract class GameScene {
    protected Viewport viewport;
    protected BitmapFont font;
    protected SpriteBatch batch;
    public Stage stage;
    
    protected int winWidth, winHeight;
    
    public GameScene(SpriteBatch batch, Viewport viewport){
        this.font = FontManager.createNewFont(48, "DisposableDroidBB.ttf");
        this.batch = batch;
        this.viewport = viewport;
        this.stage = new Stage(viewport);
        
        winWidth = Gdx.graphics.getWidth();
        winHeight = Gdx.graphics.getHeight();
        
    }
    
    public void draw(){}
    public void gameLogic(){}
    
    public void setScreenResolution(int width, int height){
        viewport.update(width, height);
        winWidth = width;
        winHeight = height;
    }
}
