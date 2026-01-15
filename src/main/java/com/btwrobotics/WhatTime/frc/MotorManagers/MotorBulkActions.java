package com.btwrobotics.WhatTime.frc.MotorManagers;

import java.util.List;

import com.ctre.phoenix6.signals.NeutralModeValue;

public class MotorBulkActions {
    public void setNeutralModeBulk(List<MotorWrapper> motors, NeutralModeValue neutralModeValue) {
        for (MotorWrapper motor : motors) {
            motor.getMotor().setNeutralMode(neutralModeValue);
        }
    }
}
