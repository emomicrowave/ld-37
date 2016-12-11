/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * We need a class to extend the LibGDX Actor class, so that 
 * we can add text to Stage so that it can be drawn.
 * @author emilanov
 */


public class ActorText extends Actor {
    BitmapFont font;
    String text;
    SpriteBatch batch;
    
    
    /** The constructor. Takes three arguments and returns an Actor Object, 
    * which can then be added to a stage so it can be drawn. Uses local
    * coordinates to draw, so use ActorText.setX() or something
    * @param font The font handle
    * @param message The text string you want to be printed
    */
    public ActorText(BitmapFont font, String message){
        this.font = font;
        this.text = message;
        this.batch = batch;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
         font.draw(batch, text, this.getX(), this.getY());
         //Also remember that an actor uses local coordinates for drawing within
         //itself!
    }
    
    public float getLength(){
        return font.getData().spaceWidth*1.3f * text.length();
    }
    
    public void setMessage(String message){
        this.text = message;
    }
}
