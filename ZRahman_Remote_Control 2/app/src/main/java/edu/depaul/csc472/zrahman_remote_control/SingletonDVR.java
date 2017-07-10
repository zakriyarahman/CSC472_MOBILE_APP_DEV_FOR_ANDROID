package edu.depaul.csc472.zrahman_remote_control;

import android.widget.Button;

/**
 * Created by Zak on 10/21/15.
 */
public class SingletonDVR {
    private static SingletonDVR theInstance;

    public States  stateStatus = States.Stopped;
    public PowerState  powerStatusIndicator = PowerState.OFF;

    public static SingletonDVR getTheInstance(){

        if(theInstance == null){
            theInstance = new SingletonDVR();
        }
        return theInstance;
    }
}
