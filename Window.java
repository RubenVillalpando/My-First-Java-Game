/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_First_Java_Game;


import javax.swing.JFrame;

/**
 *
 * @author End User
 */
public class Window extends JFrame{
    
    public Window(int WIDTH, int HEIGHT, Game game){
        setTitle("Game");
        setSize(game.WIDTH, game.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(game);
        setVisible(true);
        game.start();
        
        
    }
    
}
