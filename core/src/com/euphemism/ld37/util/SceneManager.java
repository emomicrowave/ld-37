/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.util;

import com.badlogic.gdx.Gdx;
import com.euphemism.ld37.scenes.*;

/**
 *
 * @author emilanov
 */
public class SceneManager {
    public static GameScene currentScene;
    
    public static TitleScene titleScene;
    public static DatingScene datingScene;
    
    public static void switchGameScene(GameScene newScene){
        currentScene = newScene;
        Gdx.input.setInputProcessor(newScene.stage);
    }
}
