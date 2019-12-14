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
public abstract class Character extends Sprite{
    
    protected short speed;
    protected short health;
    protected boolean isAlive = true;
    protected byte movingDirection;
    
    
    public Character(short width, short height, short startPosX,
            short startPosY, short speed, short health, byte movingDirection,
            String image){
        
        super(width, height, startPosX, startPosY, image);
        this.speed = speed;
        this.health = health;
        this.movingDirection = movingDirection;
        
    }
    
    public short getHealth(){
        return health;
    }
    
    public short getSpeed(){
        return speed;
    }
    
    public void setHealth(short health){
        this.health = health;
    }
    
    public void setSpeed(short speed){
        this.speed = speed;
    }
    
    public void lowerHealth(short damage){
        health -= damage;
    }
    
    public byte getDirection(){
        return movingDirection;
    }
    
    public void setDirection(byte dir){
        movingDirection = dir;
    }
    
    public boolean getStatus(){
        return isAlive;
    }
    
    abstract Bullet shoot();
    
    abstract void die();
    
    abstract void move();
    
    
    
}
