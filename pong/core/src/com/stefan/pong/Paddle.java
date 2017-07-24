/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefan.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Stefan
 */
public class Paddle {
    int height ;
    int width ;
    int score = 0;
    int x ;
    int y = Gdx.graphics.getHeight()/2;
    int velocity;
    Rectangle boxfull;
    
     
    public Paddle(int height,int width,int x,int velocity ){
      this.height = height;
      this.width = width;
      this.x = x;
      this.velocity = velocity;
      boxfull = new Rectangle();
      boxfull.set(x,y,width,height);
      
     
    
    }
    
      void render (ShapeRenderer sr){
     
     sr.box(x, y, 0, width,height,0);
     
     
    
    
     }
     
     void move(int dir){
         if(dir < 0 && y > 0){
       y += dir;
       boxfull.y +=dir;
       
         }
         if(dir > 0 && y < Gdx.graphics.getHeight()- height){
        y += dir;
        boxfull.y +=dir;
         
         }
     }

    void setVelocity(int i){
    velocity = i;
    }
    int getVelocity(){
    return velocity;}
    void setScore(){
      score++;
    }
    void resetScore(){
      score = 0;
    }
    int getScore(){
    return score;}
    
}
