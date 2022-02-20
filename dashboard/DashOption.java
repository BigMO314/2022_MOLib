package frc.molib.dashboard;

/**
 * A wrapper class on the WPI SendableChooser class.
 * <p>Provides a simpler and streamlined interface designed to work with DashTables.</p>
 * <b>Still a work in progress!</b>
 * 
 * @param <OptionType> Enumeration tied to the list of options.
 * 
 * @see edu.wpi.first.wpilibj.smartdashboard.SendableChooser
 * @see frc.molib.dashboard.DashTable
 */
@Deprecated
public class DashOption<OptionType extends Enum<OptionType>> {
	public DashOption(DashTable parentTable, String key) {
	}
}
