package RRPSS;

public class Table {
	
	public static enum Status{
		Occupied("Occupied"),
		Vacated("Vacated");
		
		private final String value;
		  
		private Status(String value) {
			this.value = value;
		}
		
		public String toStrValue() {
			return value;
		}
	}
	
	private int tableId;
	private Status status;
	private int seatingCapacity;
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	
	
}
