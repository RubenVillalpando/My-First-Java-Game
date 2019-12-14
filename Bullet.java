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
public class Bullet extends Sprite{
    
    private final short speed;
    private final short damage;
    private byte direction;
    
    public Bullet(short width, short height, short startPosX,
            short startPosY, short speed, short damage, byte direction, String image){
        super(width, height, startPosX, startPosY, image);
        this.speed = speed;
        this.damage = damage;
        this.direction = direction;
        
    }
    
    public short getSpeed(){
        return speed;
    }
    
    public byte getDirection(){
        return direction;
    }
    
    public short getDamage(){
        return damage;
    }
    
    public void setDirection(byte direction){
        this.direction = direction;
    }
    
    public void move(){
        switch (getDirection()){
            case 0:
                posY -= speed;
                break;
            case 1:
                posX += speed;
                break;
            case 2:
                posY += speed;
                break;
            case 3:
                posX -= speed;
        }
    }
    
}
