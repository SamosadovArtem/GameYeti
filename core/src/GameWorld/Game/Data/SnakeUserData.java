/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Data;

import Enums.UserDataType;

/**
 *
 * @author Pablo
 */
public class SnakeUserData extends UserData{
    
    public SnakeUserData(){
        super();
        userDataType = UserDataType.SNAKE;
    }
}
