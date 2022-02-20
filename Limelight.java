package frc.molib;

import frc.molib.dashboard.DashTable;
import frc.molib.dashboard.DashEntry;

/**
 * Interface for the Limelight table on NetworkTables
 */
public final class Limelight {
	private static final Limelight INSTANCE = new Limelight();
	public static Limelight getInstance() { return INSTANCE; }

	public enum LEDMode {
		kDefault(0),
		kOff(1),
		kBlink(2),
		kOn(3);

		public final int value;
		private LEDMode(int value) { this.value = value; }
	}

	public enum CamMode {
		kVisionProcessor(0),
		kDriverCam(1);
		
		public final int value;
		private CamMode(int value) { this.value = value; }
	}

	public enum StreamMode {
		kStandard(0),
		kPrimaryPiP(1),
		kSecondaryPiP(2);

		public final int value;
		private StreamMode(int value){ this.value = value; }
	}
	
	private static final DashTable tblLimelight = new DashTable("limelight");

	public static final DashEntry<Double>	dshHasTarget = new DashEntry<Double>(tblLimelight, "tv");
	public static final DashEntry<Double> 	dshPosX = new DashEntry<Double>(tblLimelight, "tx");
	public static final DashEntry<Double> 	dshPosY = new DashEntry<Double>(tblLimelight, "ty");
	public static final DashEntry<Double> 	dshWidth = new DashEntry<Double>(tblLimelight, "thor");
	public static final DashEntry<Double> 	dshHeight = new DashEntry<Double>(tblLimelight, "tver");
	public static final DashEntry<Double> 	dshArea = new DashEntry<Double>(tblLimelight, "ta");
	
	public static final DashEntry<Integer>	dshLEDMode = new DashEntry<Integer>(tblLimelight, "ledMode");
	public static final DashEntry<Integer>	dshCamMode = new DashEntry<Integer>(tblLimelight, "camMode");
	public static final DashEntry<Integer>	dshPipeline = new DashEntry<Integer>(tblLimelight, "pipeline");
	public static final DashEntry<Integer>	dshStreamMode = new DashEntry<Integer>(tblLimelight, "stream");

	private Limelight() {}

	public static boolean hasTarget() { return dshHasTarget.get() == 1; }
	public static double getPosX() { return dshPosX.get(); }
	public static double getPosY() { return dshPosY.get(); }
	public static double getWidth() { return dshWidth.get(); }
	public static double getHeight() { return dshHeight.get(); }
	public static double getArea() { return dshArea.get(); }
	
	public static void setLEDMode(LEDMode mode) { dshLEDMode.set(mode.value); }
	public static void setCamMode(CamMode mode) { dshCamMode.set(mode.value); }
	public static void setPipeline(int pipeline) { dshPipeline.set(pipeline); }
	public static void setStream(StreamMode mode) { dshStreamMode.set(mode.value); }

	public static LEDMode getLEDMode() { return LEDMode.values()[dshLEDMode.get()]; }
	public static CamMode getCamMode() { return CamMode.values()[dshCamMode.get()]; }
	public static int getPipeline() { return dshPipeline.get(); }
	public static StreamMode getStreamMode() { return StreamMode.values()[dshStreamMode.get()]; }	
}