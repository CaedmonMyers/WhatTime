package com.WhatTime.frc;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.List;
import java.util.function.Supplier;

public class PositionManager extends Command {
    private final double minValue;
    private final double maxValue;
    private final List<MotorWrapper> motors;
    private final double targetValue;
    private final double motorSpeed;
    private final double holdSpeed;
    private final double threshold;
    private final Supplier<Double> currentAngleSupplier;

    public PositionManager(
        double minValue,
        double maxValue,
        List<MotorWrapper> motors,
        double targetValue,
        double motorSpeed,
        double holdSpeed,
        double threshold,
        Supplier<Double> currentAngleSupplier
    ) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.motors = motors;
        this.targetValue = targetValue;
        this.motorSpeed = motorSpeed;
        this.holdSpeed = holdSpeed;
        this.threshold = threshold;
        this.currentAngleSupplier = currentAngleSupplier;
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
        double currentAngle = currentAngleSupplier.get();

        // Within threshold of target. Terminates command.
        if (Math.abs(currentAngle - targetValue) <= threshold) {
            setAllMotors(0.0);
            isFinishedToggle = true;
            return;
        }

        if (currentAngle > targetValue) {
            // At or below lower limit. Cannot move down more.
            if (currentAngle <= minValue) {
                setAllMotors(0.0);
                return;
            }
            // Moving down
            setAllMotors(-motorSpeed);
        } else {
            // At or above upper limit. Cannot move up more.
            if (currentAngle >= maxValue) {
                setAllMotors(0.0);
                return;
            }
            // Moving up
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
