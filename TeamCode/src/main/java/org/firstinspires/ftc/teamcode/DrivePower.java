package org.firstinspires.ftc.teamcode;

public class DrivePower {
    public double leftFrontPower, leftBackPower, rightFrontPower, rightBackPower, axial, lateral, yaw; //could improve to getters and setters but no point rn
    public void getInput(double x, double y, double z){
        this.axial = x;
        this.lateral = y;
        this.yaw = z;
    }
}
