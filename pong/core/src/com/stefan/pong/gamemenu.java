/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefan.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Stefan
 */
public class gamemenu extends pong {
   
    Rectangle resume;
    Rectangle MainMenu;
    Rectangle Exit;
    Rectangle HighScores;
    private final int width = Gdx.graphics.getWidth()/10;
    private final int height = Gdx.graphics.getHeight()/20;
    private final int menux = (Gdx.graphics.getWidth()/2)-(Gdx.graphics.getWidth()/10);
    
    
    
    public gamemenu(){
     resume = new Rectangle();
     MainMenu = new Rectangle();
     Exit = new Rectangle();
     HighScores= new Rectangle();
      
      resume.set    (menux,       (Gdx.graphics.getHeight()/10)*1,width   , height);
      MainMenu.set  (menux,       (Gdx.graphics.getHeight()/10)*2,width   , height);
      Exit.set      (menux,       (Gdx.graphics.getHeight()/10)*3,width   , height);
      HighScores.set(menux,       (Gdx.graphics.getHeight()/10)*4,width   , height);
      
        
    }
    public void render(ShapeRenderer sr,SpriteBatch sb,BitmapFont bf){
          
          
          sb.begin();
          bf.draw(sb, "resume",menux,           (Gdx.graphics.getHeight()/10)*9);
          bf.draw(sb, "MainMenu",menux,         (Gdx.graphics.getHeight()/10)*8);
          bf.draw(sb, "Exit", menux,            (Gdx.graphics.getHeight()/10)*7);
          bf.draw(sb, "HighScores",menux,       (Gdx.graphics.getHeight()/10)*6);
          sb.end();
          
          
         
    }
}
