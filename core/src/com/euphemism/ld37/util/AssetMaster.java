/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author filaret
 */
public class AssetMaster {

    public static Array<Pixmap> getBodies() {
        return getPixmapsFromFolder("body");
    }
    
    public static Array<Pixmap> getTorso() {
        return getPixmapsFromFolder("torso");
    }
    
    public static Array<Pixmap> getLegs() {
        return getPixmapsFromFolder("legs");
    }    
    
    public static Array<Pixmap> getHeads() {
        return getPixmapsFromFolder("head");
    }
        
    public static Array<Pixmap> getMisc() {
        return getPixmapsFromFolder("misc");
    }
    
    public static Array<Pixmap> getShoes() {
        return getPixmapsFromFolder("shoes");
    }
    
    public static Array<Pixmap> getPreset() {
        return getPixmapsFromFolder("preset");
    }
    
    private static Array<Pixmap> getPixmapsFromFolder(String folder) {
        Array<Pixmap> returnArray = new Array();
        AssetManager manager = new AssetManager();
        FileHandle directory = Gdx.files.internal("png/"+folder);

        for (FileHandle file : directory.list()) {
            if (!file.isDirectory()) {
                manager.load(file.path(), Pixmap.class);
            } else if (file.isDirectory()) {
                returnArray.addAll(getPixmapsFromFolder(file.path()));
            }
        }

        manager.finishLoading();
        manager.getAll(Pixmap.class, returnArray);

        return returnArray;
    }
}
