/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import java.util.ArrayList;

/**
 *
 * @author emilanov
 */
public class WordContainer extends Actor{
    private ArrayList<String> words;
    public ArrayList<String> wordBubble;
    
    private Stage stage;
    private BitmapFont font;
    
    NinePatchDrawable patchNormal;
    NinePatchDrawable patchPressed;
    
    private float lastX;
    private float lastY;
    
    private int wordLimit;
    
    
    public WordContainer(Stage stage, BitmapFont font){
        this.stage = stage;
        this.font = font;
        this.setBounds(0, 0, 0, 0);
        initializeNinePatch();
        wordBubble = new ArrayList();
    }
    
    public WordContainer(Stage stage, BitmapFont font, float posX, float posY, float sizeX, float sizeY){
        this.stage = stage;
        this.font = font;
        this.setBounds(posX, posY, sizeX, sizeY);
        
        this.lastX = posX;
        this.lastY = posY;
        initializeNinePatch();
        wordBubble = new ArrayList();
    }
    
    public void emptyContainer(){
        words.clear();
        wordBubble.clear();
    }
    
    public void newWord(final String word){
        
        TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(patchNormal, patchPressed, patchPressed, font);
        textButtonStyle.fontColor = Color.BLACK;
        
        final TextButton button = new TextButton(word, textButtonStyle);
        setButtonPos(button);
        
        stage.addActor(button);
        
        // button function
        button.addListener(new ChangeListener() {
        @Override
        public void changed (ChangeListener.ChangeEvent event, Actor actor) {
            System.out.println(word);
            if (!toggleWord(word)){
                button.setChecked(false);
            };
        }});
    }
    
    public String getWordBubble(){
        String temp = " ";
        int counter = 0;
        
        for (String word : wordBubble) {
            temp += word + " ";
            if (counter % 2 == 1){ temp += "\n";}
            counter++;
        }
        
        return temp;
    }
    
    public void setWordLimit(int limit){
        wordLimit = limit;
    }
    
    private void initializeNinePatch(){
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("png/other/speechbubble_9patch.png")), 12, 12, 12, 12);
        NinePatch patchPress = new NinePatch(new Texture(Gdx.files.internal("png/other/speechbubble_green_9patch.png")), 12, 12, 12, 12);
        
        patchNormal = new NinePatchDrawable(patch);
        patchPressed = new NinePatchDrawable(patchPress);
    }
    
    private void setButtonPos(TextButton button){
        float msgLength = button.getWidth();
        float msgHeight = button.getHeight();
        
        if (lastX + msgLength <= this.getWidth()){
            button.setPosition(lastX, lastY);
            lastX += msgLength + 10;
            return;
        }
        else if (lastY + msgHeight <= this.getHeight()){
            lastX = this.getX();
            lastY += msgHeight + 6;
            button.setPosition(lastX, lastY);
            lastX += msgLength + 10;
            return;
        }
        else{
            System.out.println("Unable to find place for button " + button.getLabel().toString());
        }
    }
    
    private boolean toggleWord(String word){
        if (!wordBubble.contains(word) && wordBubble.size() < wordLimit){
            wordBubble.add(word);
            return true;
        }else{
            wordBubble.remove(word);
            return false;
        }   
    }
    
}
