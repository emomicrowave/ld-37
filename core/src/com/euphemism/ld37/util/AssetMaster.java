/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author filaret
 */
public class AssetMaster{
    public AssetMaster(){
    }

    public Array<Texture> getTexturesFromFolder(String folder){
        Array<Texture> returnArray = new Array();        
        AssetManager manager = new AssetManager();
        FileHandle directory = Gdx.files.internal(folder);
        
        for(FileHandle file : directory.list()){
            if(!file.isDirectory()){
                manager.load(file.path(), Texture.class);
            }else if(file.isDirectory()){
                returnArray.addAll(getTexturesFromFolder(file.path()));
            }
        }
        
        manager.finishLoading();
        manager.getAll(Texture.class, returnArray);
        
        return returnArray;
    }

}
