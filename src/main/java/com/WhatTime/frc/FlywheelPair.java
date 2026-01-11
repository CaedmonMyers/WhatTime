package com.WhatTime.frc;

import com.WhatTime.frc.MotorManagers.MotorWrapper;

// This class creates a pair of motors that act as a group
// It is mainly designed to be used in a dual motor system for grabbing things.
public class FlywheelPair {
    private MotorWrapper motor1;
    private MotorWrapper motor2;
    private double speed;

    public FlywheelPair(
        MotorWrapper motor1,
        MotorWrapper motor2,
        double speed
    ) {
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.speed = speed;
    }

    // Runs at default class speed
    public void runForward() {
        runForward(speed);
    }

    // Runs at other speed (passed directly to the method)
    public void runForward(double overrideSpeed) {
        motor1.set(overrideSpeed * (motor1.isInverted() ? -1 : 1));
        motor2.set(overrideSpeed * (motor2.isInverted() ? -1 : 1));
    }

    // Runs at default class speed
    public void runBackward() {
        runBackward(speed);
    }

    // Runs at other speed (passed directly to the method)
    public void runBackward(double overrideSpeed) {
        motor1.set(overrideSpeed * (motor1.isInverted() ? 1 : -1));
        motor2.set(overrideSpeed * (motor2.isInverted() ? 1 : -1));
    }

    public void stopMotors() {
        motor1.set(0);
        motor2.set(0);
    }
}
