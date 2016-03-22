/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocationGenerator;

/**
 *
 * @author Artem
 */
public class Barrier {
    private int _locationX;
    private int _locationY;
    private BarrierTypes _type;
    
    public Barrier(int locationX,int locationY, BarrierTypes type) {

                    _locationX = locationX;
                    _locationY = locationY;
                    _type = type;
        }
    
    public int GetX(){
        return this._locationX;
    }
    public int GetY(){
        return this._locationY;
    }
    public BarrierTypes GetType(){
        return this._type;
    }
}
