package com.btwrobotics.WhatTime.frc.YearlyMethods.Rebuilt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class InactiveHubManager {
    private final Supplier<Double> currentValueSupplier;
    Map<String, Integer> timesMap = new HashMap<>();
    
    public InactiveHubManager(Supplier<Double> currentValueSupplier) {
        this.currentValueSupplier = currentValueSupplier;
        
        Optional<Alliance> currentAlliance = DriverStation.getAlliance();

        String allianceString = DriverStation.getGameSpecificMessage();

        if (allianceString == "R") {
            
        }
    }

    public Optional<Alliance> inactiveAlliance;

    public void updateTime() {

    }

    public void updateInactiveAlliance() {
        
    }

    private Alliance toggleAlliance(Alliance alliance) {
        if (alliance.equals(Alliance.Blue)) {
            return Alliance.Red;
        }
        else if (alliance.equals(Alliance.Red)) {
            return Alliance.Blue;
        }
        else {
            return Alliance.Blue;
        }
    }
}
