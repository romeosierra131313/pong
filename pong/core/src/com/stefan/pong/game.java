/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefan.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.stefan.pong.constants.menuState;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class game extends pong implements InputProcessor{
       Rectangle playArea;
       Paddle p1;
       Paddle p2;
       Ball b;
       ShapeRenderer sr ;
       Random rand = new Random();
       int enemytimer = 0;
       Boolean menu = false;
        gamemenu gm;
       private menuState ms;
       Highscore hss;
       
       

    
    public game(){
        playArea = new Rectangle();
        playArea.set(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        sr = new ShapeRenderer();
        p1 = new Paddle(50,10,20,0);
        p2 = new Paddle(50,10,Gdx.graphics.getWidth()-20,0);
        b = new Ball(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,-5,-3,10,10);
        sb = new SpriteBatch();
        bf = new BitmapFont();
        gm = new gamemenu();
        hss = new Highscore();
        hss.readhs();
        
       //
    
    }
    
       @Override
   public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sr.begin(ShapeRenderer.ShapeType.Filled);
         checkandsetscore();
       if(!menu){
        enemytimer ++;
        checkBallPosition();
       // moveenemy(p2.y,b.box.y);
        checkpaddle();
        
        sb.begin();
        bf.draw(sb,Integer.toString(p1.score),(Gdx.graphics.getWidth()/4),Gdx.graphics.getHeight()/2);
        bf.draw(sb,Integer.toString(p2.score),(Gdx.graphics.getWidth()/4)*3,Gdx.graphics.getHeight()/2);
          sb.end();
        p1.move(p1.velocity);
        p2.move(p2.velocity);
        b.move(b.speedX, b.speedY);
        p1.render(sr);
        p2.render(sr);
        b.render(sr);
        
        
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.box(b.box.x, b.box.y, 0, b.box.width, b.box.height, 0);
        sr.box(p1.boxfull.x, p1.boxfull.y, 0, p1.boxfull.width, p1.boxfull.height, 0);
         sr.box(p2.boxfull.x, p2.boxfull.y, 0, p2.boxfull.width, p2.boxfull.height, 0);
        
       }
       if(menu){
       // sr.begin(ShapeRenderer.ShapeType.Line);
          p1.render(sr);
          p2.render(sr);
          b.render(sr);
          gm.render(sr,sb,bf);
          hss.render(sr, sb, bf);
       
       }
         
         
       
        sr.end();
   }

    @Override
    public boolean keyDown(int i) {
       if( i == Keys.W){
           p1.setVelocity(6);
       } 
       if(i == Keys.S){
           p1.setVelocity(-6);
       }
       if(i == Keys.SPACE){
              
              setMenu(true);
           
          System.out.println("space");
          
       }
       return false;}

    @Override
    public boolean keyUp(int i) {
        p1.setVelocity(0);
       return false; }

    @Override
    public boolean keyTyped(char c) {
        return false;}

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        Rectangle m = new Rectangle();  
        m.setPosition(Gdx.input.getX(), Gdx.input.getY());
        m.setSize(10, 10);
         hss.readhs();
        checkandsetscore();
      if(menu){
        if (gm.resume.overlaps(m)){
             menu = false;}
        if (gm.Exit.overlaps(m)){
             Gdx.app.exit();
             checkandsetscore();
        }
        if (gm.MainMenu.overlaps(m)){
            
            checkandsetscore();
            
              ms = ms.inmenu;
             
             }
        if (gm.HighScores.overlaps(m)){
             hss.readhs();
             checkandsetscore();
             System.out.println(hss.temp);
        }
      }
        
        
       return false; }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
       return false; }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
      return false; }

    @Override
    public boolean mouseMoved(int i, int i1) {
      return false; }

    @Override
    public boolean scrolled(int i) {
      return false; }

    private void checkBallPosition() {
      if(!playArea.contains(b.box) && b.box.y > Gdx.graphics.getHeight() - b.box.height){
          
         b.setspeedY(-b.getspeedY());
      }
      if(!playArea.contains(b.box) && b.box.y < b.box.height/2 ){
         b.setspeedY(-b.getspeedY());
      }
      if(!playArea.contains(b.box) && b.box.x < 0 ){
        p2.setScore();
        resetball();
      }
      if(!playArea.contains(b.box) && b.box.x > Gdx.graphics.getWidth() ){
        p1.setScore();
        resetball();
      }
      
      
    
    
    }
    private void checkpaddle() {
      
        if(p1.boxfull.overlaps(b.box)){
             b.setspeedX(-b.getspeedX());
             if(b.getspeedY() <= 15){
             b.setspeedY(b.getspeedY() + rand.nextInt(3));} else{
             b.setspeedY(15);
             }
             b.setBS(constants.ballState.right);
            
         } 
        if(p2.boxfull.overlaps(b.box)){
             b.setspeedX(-b.getspeedX());
               if(b.getspeedY() <= 15){
             b.setspeedY(b.getspeedY() + rand.nextInt(3));} else{
               b.setspeedY(15);
               }
             b.setBS(constants.ballState.left);
             
         } 
        }
    private void moveenemy(int y, float y0) {
        
     if (enemytimer >= 0){ 
       if (y == y0){
           p2.setVelocity(0);
       }
       if(y+(p2.height/2) < y0){
          p2.setVelocity(6);
       
       }
       if(y+(p2.height/2) > y0){
          p2.setVelocity(-6);
       
       }
       enemytimer = 0;
     }
    }
    private void resetball(){
        b.setX(Gdx.graphics.getWidth()/2);
        b.setY(Gdx.graphics.getHeight()/2);
        b.box.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 10, 10);
    
    }
    public void setMenu(Boolean b){
      menu = b ;
    
    }
    public Boolean getMenu(){
     return menu;
    }
    public void setmssub(menuState menuState){
       ms = menuState;
    }
    public menuState getmenuState(){
      return ms;
    }

    private void checkandsetscore() {
        hss.hs = p1.score;
        hss.readhs();
              if(hss.temp <= hss.hs){
                 hss.temp = p1.score;
            try {
                hss.writehs();
            } catch (IOException ex) {
                Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            }} }
 
 
   
     
     
     
    }
    

