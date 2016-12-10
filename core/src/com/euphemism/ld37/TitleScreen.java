/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author emilanov
 */
public class TitleScreen extends GameScreen{
    
    private Stage stage;
    private TextButton button;
    private TextButtonStyle textButtonStyle;
    Skin skin;
    TextureAtlas buttonAtlas;
    
    
    public TitleScreen(SpriteBatch batch, Viewport viewport){
        super(batch, viewport);
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        button = new TextButton("Button1", textButtonStyle);
        button.setPosition(winWidth/2, 200);
        stage.addActor(button);
        
        button.addListener(new ChangeListener() {
        @Override
        public void changed (ChangeEvent event, Actor actor) {
            System.out.println("Button Pressed");
        }});
        
    }
    
    public void draw(){
        viewport.apply();
        
        font.setColor(Color.BLACK);
        
        batch.begin();
        stage.draw();
        font.draw(batch, "BDSM dating sim featuring Bismark.", winWidth*0.2f, winHeight*0.87f);
        batch.end();
    }
    
    
}
