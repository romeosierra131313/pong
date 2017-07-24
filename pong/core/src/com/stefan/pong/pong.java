package com.stefan.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.stefan.pong.constants.menuState;

public class pong extends ApplicationAdapter  implements InputProcessor {
        int mouseX;
        int mouseY;
        SpriteBatch sb;
        BitmapFont bf;
        Rectangle mouse;
        Rectangle startBox;
        private menuState ms;
        private Highscore hs;
        game g;
        
        
       
        private ShapeRenderer sr;
	
	@Override
	public void create () {
        setmenuState(menuState.inmenu);    
	bf = new BitmapFont();
        bf.getData().scale(1);
        sb = new SpriteBatch();
        mouse = new Rectangle();
        startBox = new Rectangle();
        startBox.set((Gdx.graphics.getWidth()/2), (Gdx.graphics.getHeight()/10) , Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/10);
        Gdx.input.setInputProcessor(this);
        g = new game();
        hs = new Highscore();
        hs.readhs();
        
        
        sr = new ShapeRenderer();
	}

	@Override
	public void render () {
           
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                sb.begin();
           
             if(ms == menuState.inmenu){
                
                bf.draw(sb,"Start", (Gdx.graphics.getWidth()/2), (Gdx.graphics.getHeight()/10) *9);
               
               }
             if(ms == menuState.ingame){
               g.render();
               if(g.getmenuState() == menuState.inmenu){
                setmenuState(menuState.inmenu);
                setInput(this);
               }
                
             }
             
               sb.end();  
               
                
                
                sr.end();
             
	}
        


    @Override
    public boolean keyDown(int i) {
      return false;   }

    @Override
    public boolean keyUp(int i) {
     return false;   }

    @Override
    public boolean keyTyped(char c) {
      return false;   }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
         System.out.println("click");
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
        setMouseRect(mouseX,mouseY);
        checkcoll();
       return false;  }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
      return false;   }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
      return false;   }

    @Override
    public boolean mouseMoved(int i, int i1) {
      return false;  }

    @Override
    public boolean scrolled(int i) {
     return false;  
    }

    public void setmenuState(menuState menuState) {
       
        ms = menuState; }
    private menuState getmenuState() {
        return ms; }
    public void setInput(InputProcessor ip){
      Gdx.input.setInputProcessor(ip);
    
    }
    void setMouseRect(int mouseX,int mouseY){
            mouse.set(mouseX, mouseY, 10, 10);
          }
    Boolean checkcoll(){
         System.out.println(ms);
       
          if ( startBox.contains(mouse)){
              
              newGame();
              return true;
          }
       
        
        
        
            return false;
        }
    public void newGame(){
       System.out.println("newgame"); 
       g.p1.resetScore();
       g.p2.resetScore();
      setmenuState(ms.ingame);
      g.setmssub(ms.ingame);
      g.setMenu(false);

      
      Gdx.input.setInputProcessor(g);
    }
}
