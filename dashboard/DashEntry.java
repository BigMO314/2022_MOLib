package frc.molib.dashboard;

import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * A simple wrapper class providing a streamlined interface for {@link edu.wpi.first.networktables.NetworkTableEntry}, 
 * designed to work with MOLib {@link DashTable}. Supports direct typing to avoid manual casting every use.
 * @param <DataType> Data type the entry is configured to hold.
 */
@SuppressWarnings("unchecked")
public class DashEntry<DataType> {
	private final NetworkTableEntry mEntry;

	/**
	 * Constructor
	 * @param parentTable	The DashTable 
	 * @param key			The name used to identify the Entry
	 */
	public DashEntry(DashTable parentTable, String key) { mEntry = parentTable.getEntry(key); }
	
	/**
	 * Retrieves the entry's value
	 * @return Current stored value
	 */
	public DataType get() { return (DataType) mEntry.getValue().getValue(); }

	/**
	 * Set the value of the entry
	 * @param value New value
	 */
	public void set(DataType value) { mEntry.setValue(value); }

	/**
	 * Removes this entry from its parent DashTable
	 */
	public void delete() { mEntry.delete(); }
}
