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
public class HealthKit extends Sprite implements Collectible{
    
    private final short BONUSHEALTH;
    
    public HealthKit(short width, short height, short startPosX, short startPosY, 
                     byte bonusHP, String image){
        
        super(width, height, startPosX, startPosY, image);
        this.BONUSHEALTH = bonusHP;
        
    }
    
    @Override
    public void giveBonus(Character hero){
        hero.setHealth((short)(hero.getHealth() + BONUSHEALTH));
    }
    
}
