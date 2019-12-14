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
public class MainCharacter extends Character{
    
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private short ammoQty;
    private final int MAXHP = 150;
    
    
    public MainCharacter(short width, short height, short startPosX, 
                         short startPosY, short speed, short health,
                         byte movingDirection, short ammoQty, String image){
        
        super(width, height, startPosX, startPosY, speed, health, 
                movingDirection, image);
        
        this.ammoQty = ammoQty;
        
    }
    
    public void addAmmo(short bonusAmmo){
        ammoQty += bonusAmmo;
    }
    
    public short getAmmoQty(){
        return ammoQty;
    }
    
    public int getMaxHp(){
        return MAXHP;
    }
    
    @Override
    public Bullet shoot(){
        if(ammoQty > 0){
            Bullet bullet = new Bullet((short)10, (short)10, this.getPosX(), 
                    this.getPosY(), (short)2, (short)10, movingDirection, 
                    "src/mx/itesm/ProyectoFinal/bullet.png");
            ammoQty -= 1;
            return bullet;
        }
        return null;
    }
    
    @Override
    public void die(){
        isAlive = false;
    }
    
    @Override
    public void move(){
        //dejar que el personaje se mueva a donde sea
        canMoveUp = true; canMoveDown = true; canMoveLeft = true; canMoveRight = true;
        //si choca con las paredes de arriba o abajo no las puede atravesar
        if(posX <= 20)canMoveLeft = false;
        else if(posX + WIDTH >= 780)canMoveRight = false;
        
        if(posY <= 50)canMoveUp = false;
        else if(posY + HEIGHT >= 580)canMoveDown = false;
        
        switch (getDirection()){
            case -1:
                break;
            case 0:
                if(canMoveUp){
                    posY -= speed;
                }
                break;
            case 1:
                if(canMoveRight){
                    posX += speed;
                }
                break;
            case 2:
                if(canMoveDown){
                    posY += speed;
                }
                break;
            case 3:
                if(canMoveLeft){
                    posX -= speed;
                }
        }
    }
    
}
