/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocationGenerator;

/**
 *
 * @author Admin
 */
public class Barrier {
    private int _location;
    private BarrierTypes _type;
    
    public Barrier(int location, BarrierTypes type) {

                    _location = location;
                    _type = type;
        }
    
    public int GetLocation(){
        return this._location;
    }
    public BarrierTypes GetType(){
        return this._type;
    }
}
