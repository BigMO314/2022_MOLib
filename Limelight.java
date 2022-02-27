package frc.molib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.molib.dashboard.Entry;

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
	
	private static final NetworkTable tblLimelight = NetworkTableInstance.getDefault().getTable("limelight");

	public static final Entry<Double>	dshHasTarget = new Entry<Double>(tblLimelight, "tv");
	public static final Entry<Double> 	dshPosX = new Entry<Double>(tblLimelight, "tx");
	public static final Entry<Double> 	dshPosY = new Entry<Double>(tblLimelight, "ty");
	public static final Entry<Double> 	dshWidth = new Entry<Double>(tblLimelight, "thor");
	public static final Entry<Double> 	dshHeight = new Entry<Double>(tblLimelight, "tver");
	public static final Entry<Double> 	dshArea = new Entry<Double>(tblLimelight, "ta");
	
	public static final Entry<Integer>	dshLEDMode = new Entry<Integer>(tblLimelight, "ledMode");
	public static final Entry<Integer>	dshCamMode = new Entry<Integer>(tblLimelight, "camMode");
	public static final Entry<Integer>	dshPipeline = new Entry<Integer>(tblLimelight, "pipeline");
	public static final Entry<Integer>	dshStreamMode = new Entry<Integer>(tblLimelight, "stream");

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