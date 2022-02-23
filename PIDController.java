package frc.molib;

import edu.wpi.first.util.sendable.SendableBuilder;

import java.util.Timer;

import edu.wpi.first.math.MathUtil;

/**
 * A simple class extending {@link edu.wpi.first.math.controller.PIDController}
 * that adds the ability to superficially enable/disable the controller, as well as clamp the output of the calculation.
 * <p><i>Upcoming: timer to ensure target is in range for an appropriate amount of time and not just passing through.</i></p>
 */
@SuppressWarnings("unused")
public class PIDController extends edu.wpi.first.math.controller.PIDController {
	private boolean mEnabled = false;

	private double mMinOutputLimit = Double.NEGATIVE_INFINITY;
	private double mMaxOutputLimit = Double.POSITIVE_INFINITY;

	private Timer tmrAtSetpoint = new Timer();

	/**
	 * Allocates a PIDController with the given constants for Kp, Ki, and Kd and a default period of 0.02 seconds.
	 * @param Kp The proportional coefficient
	 * @param Ki The integral coefficient
	 * @param Kd The derivative coefficient
	 */
	public PIDController(double Kp, double Ki, double Kd) { super(Kp, Ki, Kd); }

	/**
	 * Allocates a PIDController with the given constants for Kp, Ki, and Kd.
	 * @param Kp The proportional coefficient
	 * @param Ki The integral coefficient
	 * @param Kd The derivative coefficient
	 * @param period The period between controller updates in seconds.
	 */
	public PIDController(double Kp, double Ki, double Kd, double period) { super(Kp, Ki, Kd, period); }

	public boolean isEnabled() { return mEnabled; }
	public void enable() { mEnabled = true; }
	public void disable() { mEnabled = false; }

	public void configOutputRange(double min, double max) {
		mMinOutputLimit = min;
		mMaxOutputLimit = max;
	}

	@Override 
	public double calculate(double measurement) { return MathUtil.clamp(super.calculate(measurement), mMinOutputLimit, mMaxOutputLimit); }

	@Override
	public boolean atSetpoint() {
		return super.atSetpoint();
	}

	@Override 
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("MOLib PIDController");
		builder.setSafeState(() -> mEnabled = false);
		builder.addDoubleProperty("P", this::getP, this::setP);
		builder.addDoubleProperty("I", this::getI, this::setI);
		builder.addDoubleProperty("D", this::getD, this::setD);
		builder.addBooleanProperty("Enabled", this::isEnabled, value -> mEnabled = value);
		builder.addDoubleProperty("Setpoint", this::getSetpoint, this::setSetpoint);
		builder.addBooleanProperty("On Target", this::atSetpoint, null);
	}
}