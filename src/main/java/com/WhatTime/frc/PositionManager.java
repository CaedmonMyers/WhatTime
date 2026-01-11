package com.WhatTime.frc;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.List;
import java.util.function.Supplier;

import com.WhatTime.frc.MotorManagers.MotorWrapper;

public class PositionManager extends Command {
    private final double minValue;
    private final double maxValue;
    private final List<MotorWrapper> motors;
    private final double targetValue;
    private final double motorSpeed;
    private final double holdSpeed;
    private final double threshold;
    private final Supplier<Double> currentValueSupplier;

    public PositionManager(
        double minValue,
        double maxValue,
        List<MotorWrapper> motors,
        double targetValue,
        double motorSpeed,
        double holdSpeed,
        double threshold,
        Supplier<Double> currentValueSupplier
    ) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.motors = motors;
        this.targetValue = targetValue;
        this.motorSpeed = motorSpeed;
        this.holdSpeed = holdSpeed;
        this.threshold = threshold;
        this.currentValueSupplier = currentValueSupplier;
    }
    
    private boolean isFinishedToggle = false;

    @Override
    public void initialize() {
        isFinishedToggle = false;
    }

    @Override
    public void execute() {
        updateMotorSpeed();
    }

    private void updateMotorSpeed() {
        double currentValue = currentValueSupplier.get();

        // 1. Within threshold of target. Terminates command.
        if (Math.abs(currentValue - targetValue) <= threshold) {
            setAllMotors(0.0);
            isFinishedToggle = true;
            return;
        }

        boolean needToMoveUp = currentValue < targetValue;
        boolean needToMoveDown = currentValue > targetValue;

        // 2. At or above upper limit. Move down only.
        if (currentValue >= maxValue) {
            if (needToMoveDown) {
                setAllMotors(-motorSpeed);
            } else {
                // Want to move up but at max limit. Stop and finish
                setAllMotors(holdSpeed);
                isFinishedToggle = true;
            }
            return;
        }

        // 3. At or below lower limit. Move up only.
        if (currentValue <= minValue) {
            if (needToMoveUp) {
                setAllMotors(motorSpeed);
            } else {
                // Want to move down but at min limit - stop and finish
                setAllMotors(holdSpeed);
                isFinishedToggle = true;
            }
            return;
        }

        // 4. Within normal range. Move toward target.
        if (needToMoveDown) {
            setAllMotors(-motorSpeed);
        } else {
            setAllMotors(motorSpeed);
        }
    }

    @Override
    public boolean isFinished() {
        return isFinishedToggle;
    }
    
    @Override
    public void end(boolean interrupted) {
        setAllMotors(holdSpeed);
    }

    private void setAllMotors(double speed) {
        // Loops through all motors and sets the hold speed
        for (MotorWrapper motor : motors) {
            motor.set(speed);
        }
    }
}
