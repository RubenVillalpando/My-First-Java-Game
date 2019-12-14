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
public class Enemy extends Character{
    
    public Enemy(short width, short height, short startPosX, short startPosY,
                 short speed, short health, byte movingDirection, String image){
        
        super(width, height, startPosX, startPosY, speed, health, movingDirection,
                image);
        
    }
    
    @Override
    public Bullet shoot(){
        Bullet bullet = new Bullet((short)10, (short)10, getPosX(), getPosY(), 
                (short)2, (short)10, movingDirection, 
                "src/mx/itesm/ProyectoFinal/EnemyBullet.png");
        return bullet;
    }
    
    @Override
    public void die(){
        isAlive = false;
    }
    
    @Override
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
    
    public void detectEnemy(MainCharacter main){
        int difX = main.getPosX() - this.getPosX();
        int difY = main.getPosY() - this.getPosY();
        if(Math.abs(difX) < Math.abs(difY)){
            if(difY<0){
                setDirection((byte)0);
            }else{
                setDirection((byte)2);
            }
        }else if(difX<0){
            setDirection((byte)3);
        }else{
            setDirection((byte)1);
        }
    }
    
}
