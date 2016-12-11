/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author emilanov
 */
public class ActorSprite extends Actor{
    public Sprite sprite;
    
    private SpriteBatch batch;
    
    public ActorSprite(SpriteBatch batch, Sprite sprite){
        this.sprite = sprite;
        this.batch = batch;
    }
    
    public ActorSprite(SpriteBatch batch, Texture texture){
        this.sprite = new Sprite(texture);
        this.batch = batch;
    }
    
    @Override
    public void draw (Batch batch, float parentAlpha) {
         batch.draw(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
