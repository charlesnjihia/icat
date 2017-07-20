package objects;

public class ObjectUpdate {
	String tableName,date;
	long lastId;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public long getLastId() {
		return lastId;
	}
	public void setLastId(long lastId) {
		this.lastId = lastId;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date= date;
	}

}
