/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.euphemism.ld37.util.*;
import static com.euphemism.ld37.util.SceneManager.datingScene;

/**
 *
 * @author emilanov
 */
public class TitleScene extends GameScene{
    
    private Stage stage;
    private TextButton button;
    private TextButtonStyle textButtonStyle;
    
    private ActorText titleScreenMessage;
    
    
    public TitleScene(SpriteBatch batch, Viewport viewport){
        super(batch, viewport);
        
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        
        titleScreenMessage = new ActorText(font, "BDSM dating sim featuring Bismark.");
        titleScreenMessage.setPosition(winWidth/2 - titleScreenMessage.getLength()/2 , winHeight*0.9f);
        stage.addActor(titleScreenMessage);
        
        playButton(100, winHeight*0.3f);
        
        
    }
    
    public void draw(){
        viewport.apply();
        
        font.setColor(Color.BLACK);
        
        batch.begin();
        
        stage.draw();
        
        batch.end();
    }
    
    private void playButton(float posX, float posY){
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.BROWN;
        
        button = new TextButton("Start Dating!", textButtonStyle);
        button.setPosition(posX, posY);
        
        stage.addActor(button);
        
        // button function
        button.addListener(new ChangeListener() {
        @Override
        public void changed (ChangeListener.ChangeEvent event, Actor actor) {
            System.out.println("Starting dating sim...");
            
            SceneManager.currentScene = datingScene;
        }});
    }
    
}
