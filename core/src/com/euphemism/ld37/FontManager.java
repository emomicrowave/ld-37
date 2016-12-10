/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.euphemism.ld37;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 *
 * @author emilanov
 */
public class FontManager {
    
    public static BitmapFont createNewFont(int fontSize, String internalFontFilePath){
            BitmapFont font = new BitmapFont();
        
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(internalFontFilePath));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = fontSize;
            font = generator.generateFont(parameter);
            generator.dispose();
            
            return font;
        }
}
