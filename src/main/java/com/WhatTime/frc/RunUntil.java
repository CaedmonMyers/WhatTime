package com.WhatTime.frc;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.BooleanSupplier;

public class RunUntil extends Command {
    private BooleanSupplier conditionSupplier;
    private Runnable runAction;

    private boolean isFinishedToggle = false;

    public RunUntil(BooleanSupplier conditionSupplier, Runnable runAction) {
        this.conditionSupplier = conditionSupplier;
        this.runAction = runAction;
    }

    @Override
    public void initialize() {
        isFinishedToggle = false;
    }

    @Override
    public void execute() {
        if (conditionSupplier.getAsBoolean()) {
            runAction.run();
        }
        else {
            isFinishedToggle = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinishedToggle;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
