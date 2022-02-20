package frc.molib;

import frc.molib.utilities.MOMath;

/**
 * Wrapper class on the WPI XboxController class. Adds built-in deadzones, inverts the Y axis, provides simpler control over the rumbe feature, and can treat the Triggers as buttons.
 * 
 * @see edu.wpi.first.wpilibj.XboxController
 */
public class XboxController extends edu.wpi.first.wpilibj.XboxController {
	private double mDeadzoneThreshold = 0.1;
	private double mTriggerThreshold = 0.5;
	private boolean mIsYAxisInverted = true;
	
	/**
	 * Construct an instance of an Xbox Controller.
	 * @param port The port on the Driver Station that the controller is assigned to.
	 */
	public XboxController(int port) { super(port); }
	
	public void configDeadzoneThreshold(double value) { mDeadzoneThreshold = value; }
	public void configTriggerThreshold(double value) { mTriggerThreshold = value; }

	public boolean getYAxisInverted() { return mIsYAxisInverted; }
	public void setYAxisInverted(boolean isInverted) { mIsYAxisInverted = isInverted; }

	public void setRumble(double value) { setRumble(value, value); }
	public void setRumble(double leftValue, double rightValue) {
		super.setRumble(RumbleType.kLeftRumble, leftValue);
		super.setRumble(RumbleType.kRightRumble, rightValue);
	}
	
	public double getRawAxis(Axis axis) { return getRawAxis(axis.value); }
	@Override public double getRawAxis(int axis) { return MOMath.deaden(super.getRawAxis(axis), mDeadzoneThreshold); }

	public double getLeftY() {
		if(mIsYAxisInverted) return -super.getLeftY();
		else return super.getLeftY();
	}

	public double getRightY() {
		if(mIsYAxisInverted) return -super.getRightY();
		else return super.getRightY();
	}
	
	public boolean getLeftTrigger() { return super.getLeftTriggerAxis() > mTriggerThreshold; }
	public boolean getRightTrigger() { return super.getRightTriggerAxis() > mTriggerThreshold; }
	public double getTriggerAxis() { return getRightTriggerAxis() - getLeftTriggerAxis(); }
}