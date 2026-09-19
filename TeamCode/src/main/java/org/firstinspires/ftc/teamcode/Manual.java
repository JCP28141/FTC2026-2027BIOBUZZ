package org.firstinspires.ftc.teamcode;

public class Manual implements ActiveFunction{
    double lfPower,lbPower,rfPower,rbPower, axial, lateral, yaw;
    DrivePower driveBase;
    public Manual(DrivePower driveBase){
        this.lfPower = this.lbPower = this.rfPower = this.rbPower = 0;
        this.driveBase = driveBase;
        this.axial = driveBase.axial;
        this.lateral = driveBase.lateral;
        this.yaw = driveBase.yaw;
    }
    public void updateDrive(){
        this.lfPower = this.axial + this.lateral + this.yaw;
        this.lbPower = this.axial - this.lateral + this.yaw;
        this.rfPower = this.axial - this.lateral - this.yaw;
        this.rbPower = this.axial + this.lateral - this.yaw;


        double maxPower = Math.max(Math.abs(this.lfPower),
                Math.max(Math.abs(this.lbPower),
                        Math.max(Math.abs(this.rfPower),
                                Math.abs(this.rbPower))));

        if (maxPower > 1.0) {
            this.lfPower /= maxPower;
            this.lbPower /= maxPower;
            this.rfPower /= maxPower;
            this.rbPower /= maxPower;
        }
    }
    @Override
    public void init(){};
    @Override
    public void execute(){
        updateDrive();
        driveBase.leftFrontPower = this.lfPower;
        driveBase.leftBackPower = this.lbPower;
        driveBase.rightFrontPower = this.rfPower;
        driveBase.rightBackPower = this.rbPower;
    }
@Override
   public boolean isFinished(){ return false; }

}
