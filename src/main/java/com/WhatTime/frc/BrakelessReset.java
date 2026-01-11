package com.WhatTime.frc;

import java.util.List;

import com.WhatTime.frc.MotorManagers.MotorWrapper;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

// This class disables brake mode for a set period of time.
// This allows motors to fall to their resting position naturally.
// It can be used to recalibrate encoders on the robot
public class BrakelessReset extends Command {
    private final double duration;
    private final List<MotorResetPair> pairs;
    private final Timer timer = new Timer();

    /** Bundle a motor with the action you want run after the timeout. */
    public static class MotorResetPair {
        public final MotorWrapper motor;
        public final Runnable resetAction;
        public MotorResetPair(MotorWrapper motor, Runnable resetAction) {
            this.motor = motor;
            this.resetAction = resetAction;
        }
    }

    public BrakelessReset(double duration, List<MotorResetPair> pairs) {
        this.duration = duration;
        this.pairs = pairs;
        if (pairs == null || pairs.isEmpty()) {
            throw new IllegalArgumentException("pairs cannot be null or empty");
        }
    }

    @Override
    public void initialize() {
        // Set all motors to coast
        for (MotorResetPair pair : pairs) {
            pair.motor.getMotor().setNeutralMode(NeutralModeValue.Coast);
        }
        // Reset time to 0 and then start
        timer.reset();
        timer.start();
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(duration);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        // Run each reset action
        for (MotorResetPair pair : pairs) {
            pair.resetAction.run();
        }
    }
}
