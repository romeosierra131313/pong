/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefan.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Stefan
 */
public class Highscore implements Serializable {
    int hs ;
    int temp;
    
    public void setnewhs(int score ){
        hs = score;
        
    }
    public int geths(){
    return hs;}
    public void writehs() throws FileNotFoundException, IOException{
   
    FileOutputStream fout = new FileOutputStream("hs.hs");
    ObjectOutputStream objout = new ObjectOutputStream (fout);
    objout.writeObject (this);
       
   
   }
    public void readhs(){
        Highscore hss = null;    
      
     
      try {
         FileInputStream fileIn = new FileInputStream("hs.hs");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         hss = (Highscore) in.readObject();
         temp = hss.hs;
         in.close();
         fileIn.close();
      }catch(IOException i) {
         temp = 0;
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
    
    
}
    public void render(ShapeRenderer sr,SpriteBatch sb,BitmapFont bf){
        
        sb.begin();
        bf.setColor(Color.GREEN);
        bf.draw(sb,Integer.toString(temp) , Gdx.graphics.getWidth()/2 , 10);
        sb.end();
    
    }
      
}
