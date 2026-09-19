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
    private ActiveFunction manual;
@Override
    public void runOpMode(){
    telemetry.addData("Status", "Initialized");
    telemetry.update();
    leftFront = hardwareMap.get(DcMotor.class,"leftFront");
    leftBack = hardwareMap.get(DcMotor.class,"leftBack");
    rightFront = hardwareMap.get(DcMotor.class,"rightFront");
    rightBack = hardwareMap.get(DcMotor.class,"rightBack");
    rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
    rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
    leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    DrivePower driveBase = new DrivePower();
    manual = new Manual(driveBase);
    while(opModeIsActive()){
        driveBase.getInput(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_y);
        reference = manual;//if we have anything else that should fully take over motor power, implement the interface and add a condition to set the reference to it.
        reference.execute();
        leftFront.setPower(driveBase.leftFrontPower);
        leftBack.setPower(driveBase.leftBackPower);
        rightFront.setPower(driveBase.rightFrontPower);
        rightBack.setPower(driveBase.rightBackPower);
        telemetry.addData("Left Front", driveBase.leftFrontPower);
        telemetry.addData("Left Back", driveBase.leftBackPower);
        telemetry.addData("Right Front", driveBase.rightFrontPower);
        telemetry.addData("Right Back", driveBase.rightBackPower);
        telemetry.update();
    }
}
}
