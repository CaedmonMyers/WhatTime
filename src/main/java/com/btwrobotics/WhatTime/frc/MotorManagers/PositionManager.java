package com.btwrobotics.WhatTime.frc.MotorManagers;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import java.util.List;
import java.util.function.Supplier;

public class PositionManager {
    private final double minValue;
    private final double maxValue;
    private final List<MotorWrapper> motors;
    private final double motorSpeed;
    private final double holdSpeed;
    private final double threshold;
    private final Supplier<Double> currentValueSupplier;
    
    private double targetValue;
    
    public PositionManager(
        double minValue,
        double maxValue,
        List<MotorWrapper> motors,
        double motorSpeed,
        double holdSpeed,
        double threshold,
        Supplier<Double> currentValueSupplier
    ) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.motors = motors;
        this.targetValue = minValue; // target value is initially the minimum value
        this.motorSpeed = motorSpeed;
        this.holdSpeed = holdSpeed;
        this.threshold = threshold;
        this.currentValueSupplier = currentValueSupplier;
    }
    
    private boolean isFinishedToggle = false;

    public boolean isFinished() {
        return isFinishedToggle;
    }

    // move the motors to a value
    public Command move(double target) {
        this.targetValue = target;
        this.isFinishedToggle = false;

        return Commands.run(() -> {
            updateMotorSpeed();
        });
    }


    
    public double getTarget() {
        return targetValue;
    }


    public void stop() {
        setAllMotors(holdSpeed);
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

    private void setAllMotors(double speed) {
        // Loops through all motors and sets the hold speed
        for (MotorWrapper motor : motors) {
            motor.set(speed);
        }
    }
}
