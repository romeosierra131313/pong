/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefan.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.stefan.pong.constants.ballState;

/**
 *
 * @author Stefan
 */
public class Ball {
    int x;
    int y;
    float speedX;
    float speedY;
    int height;
    int width;
    Rectangle box ;
    ballState bs;
    
    
   Ball(int x,int y, int speedX,int speedY,int height, int width){
    this.height = height;
    this.width = width;
    this.speedX = speedX;
    this.speedY = speedY;
    this.x = x;
    this.y = y;
    box = new Rectangle();
    box.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, width, height);
    bs = ballState.left;
    
    } 


  
  void render(ShapeRenderer sr){
      
      sr.box(box.x, box.y, 0, box.width,box.height,0);
    
   
    
    
  }
  void move(float speedX,float speedY){
      
     
    x     += speedX;
    box.x += speedX;
    
    y     += speedY;
    box.y += speedY;
   
    
  
  }
  void setspeedX(float i ){
    speedX = i;
   }
  void setspeedY(float i ){
    speedY = i;
   }
  float getspeedX(){
    return speedX;
   }
  float getspeedY(){
    return speedY;
   }   
  void setX(int i){
  x = i;}
  void setY(int i){
  y = i;}
  int getX(){
  return x;
  }
  int getY(){
  return y;
  }
  void setBS(ballState ballState){
    bs = ballState;
  
  }
  ballState getBS(){
   return bs;
  }
}
