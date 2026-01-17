package com.btwrobotics.WhatTime.frc.DriverStation;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class AllianceManager {
    /**
     * Gets the current alliance for the robot from DriverStation.
     * @return the current alliance as {@code Optional<Alliance>}
     */
    public static Optional<Alliance> getCurrentAlliance() {
        return DriverStation.getAlliance();
    }

    /**
     * Gets the opposing alliance for the robot from DriverStation.
     * @return the opposing alliance as {@code Optional<Alliance>}
     */
    public static Optional<Alliance> getOpposingAlliance() {
        return toggleAlliance(DriverStation.getAlliance());
    }

    /**
     * Changes between {@code Alliance.Red} and {@code Alliance.Blue}
     * @param alliance the alliance to toggle
     * @return the opposite alliance
     */
    public static Alliance toggleAlliance(Alliance alliance) {
        // Switch blue to red
        if (alliance.equals(Alliance.Blue)) {
            return Alliance.Red;
        }
        // Switch red to blue
        else if (alliance.equals(Alliance.Red)) {
            return Alliance.Blue;
        }
        // Fallback to blue
        else {
            return Alliance.Blue;
        }
    }

    /**
     * Changes between {@code Optional<Alliance.Red>} and {@code Optional<Alliance.Blue>}
     * @param alliance the alliance to toggle
     * @return the opposite alliance. Falls back to {@code Optional<Alliance.Blue>}
     */
    public static Optional<Alliance> toggleAlliance(Optional<Alliance> alliance) {
        // Switch blue to red
        if (alliance.get().equals(Alliance.Blue)) {
            return Optional.of(Alliance.Red);
        }
        // Switch red to blue
        else if (alliance.get().equals(Alliance.Red)) {
            return Optional.of(Alliance.Blue);
        }
        // Fallback to blue
        else {
            return Optional.of(Alliance.Blue);
        }
    }
}
