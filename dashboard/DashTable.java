package frc.molib.dashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * A simple wrapper class providing a streamlined interface for {@link edu.wpi.first.networktables.NetworkTable}
 */
public class DashTable {
	private final NetworkTable mTable;
	/**
	 * Constructor
	 * @param key Identifier for the new DashTable
	 */
	public DashTable(String key) { mTable = NetworkTableInstance.getDefault().getTable(key); }

	/**
	 * Deletes all entries and subtables
	 */
	public void clear() { clear(mTable); }
	private void clear(NetworkTable table) { 
		for(String key : mTable.getKeys()) table.delete(key);
		for(String key : mTable.getSubTables()) clear(table.getSubTable(key));
	}

	/**
	 * Get or create a subtable with the specified key
	 * @param key	Identifier for the new DashTable
	 * @return		Subtable created from this DashTable
	 */
	public DashTable getSubTable(String key) { return new DashTable(mTable.getPath() + "/" + key); }
	
	/**
	 * Gets or creates a generic {@link NetworkTableEntry}
	 * @param key	Identifier for the NetworkTableEntry
	 * @return		NetworkTableEntry pulled from this DashTable
	 */
	protected NetworkTableEntry getEntry(String key) { return mTable.getEntry(key); }
}
