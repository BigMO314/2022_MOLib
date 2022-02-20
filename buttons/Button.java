package frc.molib.buttons;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;

/**
 * A non-command based Button class. 
 * <p>Sub-class this and override the {@link #get()} method to determine how the button value is read.</p>
 * @see edu.wpi.first.wpilibj.buttons.Button
 */
public class Button implements Sendable {
	protected volatile boolean mSendableValue;
	private static int mInstanceCount;

	protected boolean mLastPressed = grab();
	protected boolean mLastReleased = !grab();

	protected boolean mPressable = !grab();
	protected boolean mReleasable = grab();

	protected boolean mLastGrab = grab();
	protected boolean mPressed = false;
	protected boolean mReleased = false;

	/**
	 * Constructor
	 * <p>Uses the default method of naming the Button</p>
	 */
	public Button() { 
		mInstanceCount++;
		SendableRegistry.addLW(this, "Button[" + mInstanceCount + "]");
		ButtonScheduler.getInstance().addButton(this);
	}

	/**
	 * Constructor
	 * @param name Button name as it appears on the table.
	 */
	public Button(String name) {
		SendableRegistry.addLW(this, "Button[" + name + "]");
		ButtonScheduler.getInstance().addButton(this);
	}

	/**
	 * Constructor
	 * @param subsystem Subtable name containing the button.
	 * @param name Button name as it appears in the table.
	 */
	public Button(String subsystem, String name) {
		SendableRegistry.addLW(this, subsystem, "Button[" + name + "]");
		ButtonScheduler.getInstance().addButton(this);
	}
	
	/**
	 * Default implementation simply returns the LiveWindow 'value' property. Override this method to change how the value is read.
	 * @return True when the LiveWindow 'value' reads true.
	 */
	public boolean get() { return mSendableValue; }

	/**
	 * Whether the button was pressed since the last check.
	 * @return True if the button has been pressed.
	 */
	public final boolean getPressed() {
		if (mPressed) {
			mPressed = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Whether the button has been released since the last check.
	 * @return True if the button has been released.
	 */
	public final boolean getReleased() {
		if (mReleased) {
			mReleased = false;
			return true;
		} else {
			return false;
		}
	}

	private boolean grab() { return get() || mSendableValue; }

	protected void periodic() {
		boolean currentGrab = grab();

		if (currentGrab && !mLastGrab) mPressed = true;
        if (!currentGrab && mLastGrab) mReleased = true;
        
        mLastGrab = grab();
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSafeState(() -> mSendableValue = false);
		builder.addBooleanProperty("value", this::grab, value -> mSendableValue = value);
		builder.addBooleanProperty("pressed", () -> mLastPressed, null);
		builder.addBooleanProperty("released", () -> mLastReleased, null);
	}
}