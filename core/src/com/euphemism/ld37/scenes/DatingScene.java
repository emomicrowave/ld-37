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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.euphemism.ld37.util.*;

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
    
    public DatingScene(SpriteBatch batch, Viewport viewport){
        super(batch, viewport);
        initializeSprites();
        
        speechFont = FontManager.createNewFont(30, "DisposableDroidBB.ttf");
        
        // create a test speech bubble
        TextButton navySealsCopyPasta = speechBubble("What the fuck did you just fucking say to me you \nlittle bitch? I'll have you know...");
        navySealsCopyPasta.setPosition(0, 600);
        stage.addActor(navySealsCopyPasta);
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
        batch.begin();
        stage.draw();
        batch.end();
    }
    
    // Button class to utilize the ninePatch usage and be able to
    // stretch a sprite
    private TextButton speechBubble(String message){
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("png/other/speechbubble_9patch.png")), 12, 12, 12, 12);
        NinePatchDrawable drawPatch = new NinePatchDrawable(patch);
        
        // Create a new TextButtonStyle
        TextButtonStyle style = new TextButtonStyle(drawPatch, drawPatch, drawPatch, speechFont);
        style.fontColor = Color.BLACK;
        
        // Instantiate the Button itself.
        TextButton button = new TextButton(message, style);
        
        return button;
    }
}
