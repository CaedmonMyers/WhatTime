package com.btwrobotics.WhatTime.frc.SmartBrake;

import java.util.List;
import java.util.OptionalDouble;

import com.btwrobotics.WhatTime.frc.MotorManagers.MotorPositionHandler;
import com.btwrobotics.WhatTime.frc.MotorManagers.MotorWrapper;

import edu.wpi.first.wpilibj2.command.Command;

public class SmartBrakeHelper extends Command {
    private final List<MotorWrapper> motors;
    private final double maxSpeed;
    private final double targetPosition;
    private final double threshold;
    private final double maxValue;
    private final double minValue;

    public SmartBrakeHelper(List<MotorWrapper> motors, double maxSpeed, double targetPosition, double threshold, double maxValue, double minValue) {
        this.motors = motors;
        this.maxSpeed = maxSpeed;
        this.targetPosition = targetPosition;
        this.threshold = threshold;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    private boolean isFinishedToggle = false;

    private MotorPositionHandler motorPositionHandler = new MotorPositionHandler();

    @Override
    public void initialize() {
        isFinishedToggle = false;
    }

    @Override
    public void execute() {
        // If the position is correct, do nothing
        if (Math.abs(targetPosition - motorPositionHandler.averagePositions(motors)) <= threshold) {
        }

        // Calculate percentage of movement
        else {
            // Below target
            if (motorPositionHandler.averagePositions(motors) < targetPosition) {
                double percentageMoved = Math.abs(motorPositionHandler.averagePositions(motors) / (minValue - targetPosition));

                setAllMotors(percentageMoved * maxSpeed + 0.05);
            }
            // Above target
            else {
                double percentageMoved = Math.abs(motorPositionHandler.averagePositions(motors) / (maxValue - targetPosition));

                setAllMotors(-percentageMoved * maxSpeed + 0.05);
            }
        }
    }

    @Override
    public boolean isFinished() {
        return isFinishedToggle;
    }

    private void setAllMotors(double speed) {
        // Loops through all motors and sets the hold speed
        for (MotorWrapper motor : motors) {
            motor.set(speed);
        }
    }
}
