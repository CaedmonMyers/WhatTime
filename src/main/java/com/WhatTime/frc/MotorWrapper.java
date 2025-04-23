package com.WhatTime.frc;

import com.ctre.phoenix6.hardware.TalonFX;

public class MotorWrapper {
    private final TalonFX motor;
    private final boolean inverted;

    public MotorWrapper(TalonFX motor, boolean inverted) {
        this.motor = motor;
        this.inverted = inverted;
    }

    public void set(double speed) {
        double actualSpeed = inverted ? -speed : speed;
        motor.set(actualSpeed);
    }

    public TalonFX getMotor() {
        return motor;
    }

    public boolean isInverted() {
        return inverted;
    }
}
