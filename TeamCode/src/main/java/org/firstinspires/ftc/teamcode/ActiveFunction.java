package org.firstinspires.ftc.teamcode;

public interface ActiveFunction {
    public void init();
    public void execute();
    boolean isFinished();
    default String getAction(){
        return this.getClass().getSimpleName();
    }
}

