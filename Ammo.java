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
public class Ammo extends Sprite{
    
    private final short BONUSAMMO;
    
    public Ammo(short width, short height, short startPosX, short startPosY, 
            short bonus, String image){
        super(width, height, startPosX, startPosY, image);
        this.BONUSAMMO = bonus;
    }
    
    public void giveBonus(MainCharacter hero){
        
        hero.addAmmo(BONUSAMMO);
        
    }
    
}
