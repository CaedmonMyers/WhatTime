package com.btwrobotics.WhatTime.frc.MotorManagers;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class MotorWrapper {
    private final TalonFX motor;
    private final boolean inverted;

    public MotorWrapper(TalonFX motor, boolean inverted) {
        this.motor = motor;
        this.inverted = inverted;
    }

    // This method adds the option of using the .of() method instead of creating a new object.
    public static MotorWrapper of(TalonFX motor, boolean inverted) {
        return new MotorWrapper(motor, inverted);
    }

    public void set(double speed) {
        double actualSpeed = inverted ? -speed : speed;
        motor.set(actualSpeed);
    }

    public TalonFX getMotor() {
        return motor;
    }

    // Checks if the motor object is inverted or not.
    // This determines the direction it will rotate for some other classes.
    public boolean isInverted() {
        return inverted;
    }

    public double getPosition() {
        return motor.get();
    }
}
