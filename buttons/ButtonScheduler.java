package frc.molib.buttons;

import java.util.Vector;

//TODO: Move into Button class?
public final class ButtonScheduler {
	private static ButtonScheduler mInstance = new ButtonScheduler();
	public static ButtonScheduler getInstance() { return mInstance; }

	private Vector<Button> mButtonList = new Vector<Button>();
	private boolean mEnabled = true;

	private ButtonScheduler() {}

	public void enable() { mEnabled = true; }
	public void disable() { mEnabled = false; }

	protected void addButton(Button button) { mButtonList.addElement(button); }
	protected void removeButton(Button button) { mButtonList.remove(button); }
	public void removeAll() { mButtonList.clear(); }

	public void periodic() {
		if (!mEnabled) return;
		for (Button btnTemp : mButtonList)
			btnTemp.periodic();
	}
}