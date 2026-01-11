package com.WhatTime.frc.DashboardManagers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// This class provides a simple way to put information to Shuffleboard
public class ShuffleboardUtil {
    // Method for numbers
    public static void put(String key, double value) {
        SmartDashboard.putNumber(key, value);
    }

    // Method for strings
    public static void put(String key, String value) {
        SmartDashboard.putString(key, value);
    }

    // Method for booleans
    public static void put(String key, boolean value) {
        SmartDashboard.putBoolean(key, value);
    }

    // Method for generic objects (if needed)
    public static void put(String key, Object value) {
        if (value instanceof Number) {
            SmartDashboard.putNumber(key, ((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            SmartDashboard.putBoolean(key, (Boolean) value);
        } else {
            SmartDashboard.putString(key, value.toString());
        }
    }
}