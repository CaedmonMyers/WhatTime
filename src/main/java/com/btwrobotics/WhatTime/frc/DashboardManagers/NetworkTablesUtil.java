package com.btwrobotics.WhatTime.frc.DashboardManagers;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

// This class provides a simple way to put information directly to NetworkTables
public class NetworkTablesUtil {
    private static final NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private static final String DEFAULT_TABLE = "CustomDashboard";
    
    // Method for numbers
    public static void put(String key, double value) {
        inst.getTable(DEFAULT_TABLE).getEntry(key).setDouble(value);
    }

    // Method for strings
    public static void put(String key, String value) {
        inst.getTable(DEFAULT_TABLE).getEntry(key).setString(value);
    }


    // Method for booleans
    public static void put(String key, boolean value) {
        inst.getTable(DEFAULT_TABLE).getEntry(key).setBoolean(value);
    }

    // Method for generic objects
    public static void put(String key, Object value) {
        // Dont convert other general objects to strings, corrupts most datatypes
        inst.getTable(DEFAULT_TABLE).getEntry(key).setValue(value);
    }
    
    // Overloaded methods to specify a custom table
    public static void put(String table, String key, double value) {
        inst.getTable(table).getEntry(key).setDouble(value);
    }

    public static void put(String table, String key, String value) {
        inst.getTable(table).getEntry(key).setString(value);
    }

    public static void put(String table, String key, boolean value) {
        inst.getTable(table).getEntry(key).setBoolean(value);
    }
}