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
public class Boots extends Sprite implements Collectible{
    
    private final short BONUSMS;
    
    public Boots(short width, short height, short startPosX, short startPosY, 
            short bonus, String image){
        super(width, height, startPosX, startPosY, image);
        BONUSMS = bonus;
    }

    @Override
    public void giveBonus(Character hero) {
        
        hero.setSpeed((short)(hero.getSpeed() + BONUSMS));

    }
    
    
    
}
