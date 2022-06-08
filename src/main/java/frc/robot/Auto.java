package frc.robot;  

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 

public class Auto {
    private double starttime;
    private double time; 
    private double distance; 

    public Auto() {
        starttime = 0;
        time = 0;
        distance = 0;
    }

    public Auto(double st, double t, double d) {
        starttime = st;
        time = t;
        distance = d; 
    }

    public void goForward(WPI_TalonSRX a, WPI_TalonSRX b, WPI_TalonSRX c, WPI_TalonSRX d) {
        if (time - starttime < distance) {
            a.set(-0.6);
            b.set(-0.6);
            c.set(0.6);
            d.set(0.6); 
        } else {
            a.set(0.0);
            b.set(0.0);
            c.set(0.0);
            d.set(0.0);
        }
    }

    public void rightTurn(WPI_TalonSRX a, WPI_TalonSRX b, WPI_TalonSRX c, WPI_TalonSRX d) {
        if (time - starttime < distance) {
            a.set(0.6);
            b.set(0.6);
            c.set(0.0);
            d.set(0.0); 
        } else {
            a.set(0.0);
            b.set(0.0);
            c.set(0.0);
            d.set(0.0);
        }
    }

    public void leftTurn(WPI_TalonSRX a, WPI_TalonSRX b, WPI_TalonSRX c, WPI_TalonSRX d) {
        if (time - starttime < distance) {
            a.set(0.0);
            b.set(0.0);
            c.set(-0.6);
            d.set(-0.6); 
        } else {
            a.set(0.0);
            b.set(0.0);
            c.set(0.0);
            d.set(0.0);
        }
    }

    public void goBackward(WPI_TalonSRX a, WPI_TalonSRX b, WPI_TalonSRX c, WPI_TalonSRX d) {
        if (time - starttime < distance) {
            a.set(-0.6);
            b.set(-0.6);
            c.set(0.6);
            d.set(0.6); 
        } else {
            a.set(0.0);
            b.set(0.0);
            c.set(0.0);
            d.set(0.0);
        }
    }
}
