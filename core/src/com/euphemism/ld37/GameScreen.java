/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author emilanov
 */
public abstract class GameScreen {
    protected Viewport viewport;
    protected BitmapFont font;
    protected SpriteBatch batch;
    
    protected int winWidth, winHeight;
    
    public GameScreen(SpriteBatch batch, Viewport viewport){
        this.font = FontManager.createNewFont(48, "DisposableDroidBB.ttf");
        this.batch = batch;
        this.viewport = viewport;
        
    }
    
    public void draw(){}
    public void gameLogic(){}
    
    public void setScreenResolution(int width, int height){
        viewport.update(width, height);
        winWidth = width;
        winHeight = height;
    }
}
