package frc.molib.dashboard;

import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * A wrapper on the WPI NetworkTableEntry class.
 * <p>Provides a simplified and streamlined interface for interacting with Entries on the NetworkTables Designed to work with DashTables. Also provides direct typing to avoid manual casting at every stage.</p>
 * 
 * @param <EntryType> Data type the entry is configured to hold.
 * 
 * @see frc.molib.dashboard.DashTable
 * @see edu.wpi.first.networktables.NetworkTableEntry
 */
@SuppressWarnings("unchecked")
public class DashEntry<EntryType> {
	private final NetworkTableEntry mEntry;

	/**
	 * Construct an instance of a DashEntry.
	 * 
	 * @param parentTable	The table from which to get the Entry.
	 * @param key			The name used to identify the Entry.
	 */
	public DashEntry(DashTable parentTable, String key) { mEntry = parentTable.getEntry(key); }
	
	public EntryType get() { return (EntryType) mEntry.getValue().getValue(); }
	public void set(EntryType value) { mEntry.setValue(value); }
	public void delete() { mEntry.delete(); }
}
