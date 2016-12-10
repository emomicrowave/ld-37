/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author emilanov
 */
public class DatingScene extends GameScene{
    
    private Texture img1;
    private Texture img2;
    private Sprite sprite1;
    private Sprite sprite2;
    
    public DatingScene(SpriteBatch batch, Viewport viewport){
        super(batch, viewport);
        initializeSprites();
    }
    
    private void initializeSprites(){
        img1 = new Texture(Gdx.files.internal("png/preset/shaolin_monk.png"));
        sprite1 = new Sprite(img1);
        
        img2 = new Texture(Gdx.files.internal("png/preset/bdsm_robot.png"));
        sprite2 = new Sprite(img2);
        sprite2.flip(true, false);
    }
    
    public void draw(){
        batch.begin();
        batch.draw(sprite1, 0,0,200, 540);
        batch.draw(sprite2, winWidth-200,0,200, 540);
        batch.end();
    }
}
