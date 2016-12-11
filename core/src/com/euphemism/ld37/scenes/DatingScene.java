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
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.euphemism.ld37.util.*;
import static com.euphemism.ld37.util.SceneManager.datingScene;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author emilanov
 */
public class DatingScene extends GameScene{
    
    private int ROUND_TIME = 10;
    private int MAX_ROUNDS = 5;
    
    private Texture img1;
    private Texture img2;
    private ActorSprite sprite1;
    private ActorSprite sprite2;
    private BitmapFont speechFont;
    
    private ArrayList<String> words;
    private WordContainer wordContainer;
    private NinePatchDrawable drawPatch;
    
    private ActorText timerLabel;
    private ActorText scoreLabel;
    
    private Person player;
    private Person otherChar;
    
    private TextButton otherSpeech;
    private TextButton personSpeech;
    
    private int lastWordCount;
    private boolean roundStarted;
    private boolean timerStarted;
    private float timeLeft;
    private Random rng;
    private int score;
    private int roundsPassed;
    
    public DatingScene(SpriteBatch batch, Viewport viewport){
        super(batch, viewport);
        initializeSprites();
        
        words = new ArrayList();
        words.addAll(Arrays.asList("Butt", "Explosion", "Doggo", "Meme", "Ass", "Bazookablaster", "Decepticon", "Church", "Foo Fighters"));
        
        speechFont = FontManager.createNewFont(30, "DisposableDroidBB.ttf");
        wordContainer = new WordContainer(stage, speechFont, 180, 200, 800, 500);
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
        
        font.setColor(Color.BLACK);
        // timer
        timerLabel = new ActorText(font, "Time Left: " + timeLeft );
        timerLabel.setPosition(winWidth / 2 - timerLabel.getLength() / 2, winHeight * 0.95f);
        stage.addActor(timerLabel);
        
        // score
        scoreLabel = new ActorText(font, "Score: " + score);
        scoreLabel.setPosition(winWidth*0.8f, winHeight * 0.95f);
        stage.addActor(scoreLabel);
        
        submitWordsButton(800, 200);
        buildWordContainer();
    }
    
    
    
    public void draw(){
        // Stretches the NinePatch to cover the new or old words
        if (lastWordCount != wordContainer.wordBubble.size()){
            resetPlayerSpeech(wordContainer.getWordBubble(), 100, 490);
            lastWordCount = wordContainer.wordBubble.size();
        }
        
        if (!roundStarted){startRound();}
        if (timerStarted){
            timeLeft -= Gdx.graphics.getDeltaTime();
            timerLabel.setMessage("Time Left: " + timeLeft);
        }
        
        if (timeLeft <= 0){
            endRound();
        }
        
        scoreLabel.setMessage("Score: " + score);
        
        viewport.apply();
        batch.begin();
        stage.draw();
        batch.end();
    }
    
    private void initializeSprites(){
        player = new Person();
        otherChar = new Person();
        
        ActorSprite background = new ActorSprite(batch, new Texture("png/other/background.png"));
        background.setBounds(0, 0, 1200, 680);
        background.sprite.flip(true, false);
        stage.addActor(background);
        
        sprite1 = new ActorSprite(batch, player.getRandomTexture());
        sprite2 = new ActorSprite(batch, otherChar.getRandomTexture());
        sprite2.sprite.flip(true, false);
        
        sprite1.setBounds(0, 0, 200, 540);
        sprite2.setBounds(winWidth-200,0,200, 540);
        
        stage.addActor(sprite1);
        stage.addActor(sprite2);
        
        
        
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
    
    private void startTimer(float seconds){
        timerStarted = true;
        timeLeft = seconds;
    }
    
    private void endRound(){
        roundsPassed ++;
        timerStarted = false;
        
        if (roundsPassed < MAX_ROUNDS){
            generateNewQuestion();
            sprite2.remove();
            sprite2.clear();
            
            sprite2 = new ActorSprite(batch, otherChar.getRandomTexture());
            sprite2.setBounds(winWidth-200,0,200, 540);
            sprite2.sprite.flip(true, false);
            stage.addActor(sprite2);
            
            startRound();
        }
    }
    
    private void startRound(){
        resetOtherSpeech("What's your favourite movie?", 600, 490);
        
        // word limit beween 2 and 5
        wordContainer.setWordLimit(rng.nextInt(3)+2); 
        startTimer(ROUND_TIME);
        roundStarted = true;
    }
    
    private void submitWordsButton(float posX, float posY) {
        TextButtonStyle textButtonStyle = new TextButtonStyle(drawPatch, drawPatch, drawPatch, font);
        textButtonStyle.fontColor = Color.BROWN;

        TextButton button = new TextButton("Submit!", textButtonStyle);
        button.setPosition(posX, posY);

        stage.addActor(button);

        // button function
        button.addListener(new ChangeListener() {

        @Override
        public void changed (ChangeListener.ChangeEvent event, Actor actor) {
            generateNewQuestion();
        }});

    }
    
    private void generateNewQuestion(){
        wordContainer.emptyContainer();
        wordContainer.setWordLimit(rng.nextInt(3)+2); 
        score += 10;
        
        words.clear();
        words.addAll(Arrays.asList("Shotgun", "Banana", "Clown", "Pineapple", "Couch", "Spank"));
        resetOtherSpeech("What do you like to eat on cold days?", 600, 490);
        buildWordContainer();
    }
}
