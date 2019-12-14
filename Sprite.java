/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_First_Java_Game;

/**
 *
 * @author End User
 */
public abstract class Sprite {
    
    protected final short HEIGHT;
    protected final short WIDTH;
    protected short posX;
    protected short posY;
    protected String image;
    
    public Sprite(short width, short height, short startPosx, short startPosy,
                  String image){
        HEIGHT = height;
        WIDTH = width;
        posX = startPosx;
        posY = startPosy;
        this.image = image;
    }
    
    public short getHeight(){
        return HEIGHT;
    }
    
    public short getWidth(){
        return WIDTH;
    }
    
    public short getPosX(){
        return posX;
    }
    
    public short getPosY(){
        return posY;
    }
    
    public String getImage(){
        return image;
    }
    
    public void setPosX(short posX){
        this.posX = posX;
    }
    
    public void setPosY(short posY){
        this.posY = posY;
    }
    
    public void setImage(String path){
        image = path;
    }
    
}
