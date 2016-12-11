/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.euphemism.ld37.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author emilanov
 */
public class DatingScene extends GameScene{
    
    private Texture img1;
    private Texture img2;
    private ActorSprite sprite1;
    private ActorSprite sprite2;
    private BitmapFont speechFont;
    
    private ArrayList<String> words;
    private WordContainer wordContainer;
    private NinePatchDrawable drawPatch;
    
    private TextButton otherSpeech;
    private TextButton personSpeech;
    
    private int lastWordCount;
    private boolean roundStarted;
    private Random rng;
    
    public DatingScene(SpriteBatch batch, Viewport viewport){
        super(batch, viewport);
        initializeSprites();
        
        words = new ArrayList();
        words.addAll(Arrays.asList("Butt", "Explosion", "Doggo", "Meme", "Ass", "Bazookablaster", "Decepticon", "Church", "Foo Fighters"));
        
        speechFont = FontManager.createNewFont(30, "DisposableDroidBB.ttf");
        wordContainer = new WordContainer(stage, speechFont, 200, 200, 800, 500);
        rng = new Random();
        
        roundStarted = false;
        
        // create a test speech bubble
        otherSpeech = speechBubble("What the fuck did you just fucking \nsay to me you \nlittle bitch? I'll have you know...");
        otherSpeech.setPosition(600, 490);
        stage.addActor(otherSpeech);
        
        // moreSpeech
        personSpeech = speechBubble("");
        personSpeech.setPosition(300, 490);
        stage.addActor(personSpeech);
        
        buildWordContainer();
    }
    
    private void initializeSprites(){
        img1 = new Texture(Gdx.files.internal("png/preset/shaolin_monk.png"));
        sprite1 = new ActorSprite(batch, img1);
        
        img2 = new Texture(Gdx.files.internal("png/preset/bdsm_robot.png"));
        sprite2 = new ActorSprite(batch, img2);
        sprite2.sprite.flip(true, false);
        
        sprite1.setBounds(0, 0, 200, 540);
        sprite2.setBounds(winWidth-200,0,200, 540);
        
        stage.addActor(sprite1);
        stage.addActor(sprite2);
        
    }
    
    public void draw(){
        // Stretches the NinePatch to cover the new or old words
        if (lastWordCount != wordContainer.wordBubble.size()){
            resetPlayerSpeech(wordContainer.getWordBubble(), 100, 490);
            lastWordCount = wordContainer.wordBubble.size();
        }
        
        if (!roundStarted){startRound();}
        
        viewport.apply();
        batch.begin();
        stage.draw();
        batch.end();
    }
    
    // Button class to utilize the ninePatch usage and be able to
    // stretch a sprite
    private TextButton speechBubble(String message){
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("png/other/speechbubble_9patch.png")), 12, 12, 12, 12);
        drawPatch = new NinePatchDrawable(patch);
        
        // Create a new TextButtonStyle
        TextButtonStyle style = new TextButtonStyle(drawPatch, drawPatch, drawPatch, speechFont);
        style.font = speechFont;
        style.fontColor = Color.BLACK;
        
        // Instantiate the Button itself.
        TextButton button = new TextButton(message, style);
        
        return button;
    }
    
    private void buildWordContainer(){
        for (int i = 0; i < words.size(); i++) {
            wordContainer.newWord(words.get(i));
        }
    }
    
    private void resetPlayerSpeech(String text, int posX, int posY){
        personSpeech.remove();
        personSpeech = speechBubble(text);
        personSpeech.setPosition(posX, posY);
        stage.addActor(personSpeech);
    }
    
    private void resetOtherSpeech(String text, int posX, int posY){
        otherSpeech.remove();
        otherSpeech = speechBubble(text);
        otherSpeech.setPosition(posX, posY);
        stage.addActor(otherSpeech);
    }
    
    private void startTimer(int seconds){
        
    }
    
    private void startRound(){
        resetOtherSpeech("What's your favourite movie?", 600, 490);
        
        // word limit beween 2 and 5
        wordContainer.setWordLimit(rng.nextInt(3)+2); 
        startTimer(60);
    }
}
