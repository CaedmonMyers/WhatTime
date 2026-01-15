package com.btwrobotics.WhatTime.frc.MotorManagers;

import java.util.List;

import com.ctre.phoenix6.signals.NeutralModeValue;

public class MotorPositionHandler {
    public double averagePositions(List<MotorWrapper> motors) {
        double addingValue = 0.0;
        for (MotorWrapper motor : motors) {
            addingValue += motor.getPosition();
        }

        // If motors is not empty
        if (motors.size() != 0.0) {
            return addingValue / motors.size();
        }
        // else return a default value
        else {
            return 3.141592;
        }
    }
}
