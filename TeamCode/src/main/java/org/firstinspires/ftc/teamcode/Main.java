package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="BIOBUZZ")
@Disabled

public class Main extends LinearOpMode {
    //Add motors and hardware here
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private ActiveFunction reference;
    private ActiveFunction defaultAction;
    private ActiveFunction placeholder;
    private GoBildaPinpointDriver odometryPinpoint;
    private DrivePower driveBase;
@Override
    public void runOpMode(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        driveBaseInit();
        peripheralHardwareInit();
        executionsInit();
        while(opModeIsActive()){
            inputs();
            executionSwitch();
            reference.execute();
            driveBaseMotorPower();
            telemetry();
    }
}
    public void telemetry(){
        telemetry.addData("Left Front", driveBase.leftFrontPower);
        telemetry.addData("Left Back", driveBase.leftBackPower);
        telemetry.addData("Right Front", driveBase.rightFrontPower);
        telemetry.addData("Right Back", driveBase.rightBackPower);
        telemetry.update();
    }
    public void driveBaseMotorPower(){
        leftFront.setPower(driveBase.leftFrontPower);
        leftBack.setPower(driveBase.leftBackPower);
        rightFront.setPower(driveBase.rightFrontPower);
        rightBack.setPower(driveBase.rightBackPower);
    }
    public void inputs(){
        driveBase.getInput(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_y);
    }
    public void driveBaseInit(){
        leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");
        rightBack = hardwareMap.get(DcMotor.class,"rightBack");
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void peripheralHardwareInit(){
        odometryPinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"odometryPinpoint");
    }
    public void executionsInit(){
        driveBase = new DrivePower();
        this.defaultAction = new Manual(driveBase);
    }
    public void executionSwitch(){
        this.reference  = (this.reference.getAction().equalsIgnoreCase(this.defaultAction.getAction())) ? this.defaultAction: this.reference.isFinished() ? gamepad1.a ? this.placeholder : this.defaultAction/*replace this to extend gamepad actions*/ : this.reference;


    }

}
