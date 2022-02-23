package frc.molib;

/**
 * A simple wrapper class on {@link edu.wpi.first.wpilibj.XboxController} that provides additional functionality such as:
 * built-in deadzones on axes, option to invert the Y-axis of the joysticks, simple control over the rumble feature, 
 * and the abililty to read the triggers as individual buttons.
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

	protected static double deadenAxis(double value, double deadzoneThreshold) {
		if (Math.abs(value) < deadzoneThreshold)
			return 0.0;
		else if (value < 0.0)
			return (value + deadzoneThreshold) / (1.0 - deadzoneThreshold);
		else
			return (value - deadzoneThreshold) / (1.0 - deadzoneThreshold);
	}
	
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
	@Override public double getRawAxis(int axis) { return deadenAxis(super.getRawAxis(axis), mDeadzoneThreshold); }

	@Override
	public double getLeftY() {
		if(mIsYAxisInverted) return -super.getLeftY();
		else return super.getLeftY();
	}

	@Override
	public double getRightY() {
		if(mIsYAxisInverted) return -super.getRightY();
		else return super.getRightY();
	}
	
	public boolean getLeftTrigger() { return super.getLeftTriggerAxis() > mTriggerThreshold; }
	public boolean getRightTrigger() { return super.getRightTriggerAxis() > mTriggerThreshold; }
	public double getTriggerAxis() { return getRightTriggerAxis() - getLeftTriggerAxis(); }
}