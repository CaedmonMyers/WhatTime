package com.WhatTime.frc.SmartBrake;

import java.util.List;
import java.util.OptionalDouble;

import com.WhatTime.frc.MotorManagers.MotorWrapper;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;

public class SmartBrake {
    private final List<MotorWrapper> motors;
    private double targetPosition;
    private final double maxSpeed;
    private final double threshold;
    private final double maxValue;
    private final double minValue;


    public SmartBrake(List<MotorWrapper> motors, OptionalDouble maxSpeed, double targetPosition, double threshold, double maxValue, double minValue) {
        this.motors = motors;

        // Sets the custom max speed with a fallback of 0.3.
        this.maxSpeed = maxSpeed.orElse(0.3);

        this.targetPosition = targetPosition;
        this.threshold = threshold;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public Command enableSmartBrakes() {
        return new SmartBrakeHelper(motors, maxSpeed, targetPosition, threshold, maxValue, minValue);
    }

    public void updateBrakePosition(double targetPosition) {
        this.targetPosition = targetPosition;
    }

    public double getBrakePositionAsDouble() {
        return targetPosition;
    }

    public void enableBrakes() {
        for (MotorWrapper motor : motors) {
            motor.getMotor().setNeutralMode(NeutralModeValue.Brake);
        }
    }

    public void disableBrakes() {
        for (MotorWrapper motor : motors) {
            motor.getMotor().setNeutralMode(NeutralModeValue.Coast);
        }
    }
}
