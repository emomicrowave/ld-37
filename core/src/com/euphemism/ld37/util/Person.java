/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37.util;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author filaret
 */
public class Person {
    Pixmap body, torso, legs, shoes, head, misc;
    Texture texture;

    public Person() {
    }

    public Texture getRandomTexture(){
        Pixmap wholePerson = getRandomPerson();
        Texture toReturn = new Texture(wholePerson);
        return toReturn;
    }
    
    private Pixmap getRandomPerson(){
        Pixmap toReturn = AssetMaster.getBodies().random();
        toReturn.drawPixmap(AssetMaster.getLegs().random(), 0, 0);
        toReturn.drawPixmap(AssetMaster.getTorso().random(), 0, 0);
        toReturn.drawPixmap(AssetMaster.getShoes().random(), 0, 0);
        toReturn.drawPixmap(AssetMaster.getHeads().random(), 0, 0);
        toReturn.drawPixmap(AssetMaster.getMisc().random(), 0, 0);
        
        return toReturn;
    }
}
