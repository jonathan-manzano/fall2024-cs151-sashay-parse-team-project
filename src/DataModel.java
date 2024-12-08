import java.util.ArrayList;

/**
 * A model that contains the data and notifies its view
 * @param <T>
 */
public class DataModel<T> {
	
	ArrayList<T> data;
	ArrayList<DataListener> listeners;
	
	/**
	 * Constructs the DataModel using data from an array
	 * @param initialData the array of data to be used
	 */
	public DataModel() {
		data = new ArrayList<T>();
		listeners = new ArrayList<DataListener>();
	}
	
	/**
	 * Stores the data listener
	 * @param listener the data listener for the model
	 */
	public void addListener(DataListener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Changes the data at index i by the parameter value
	 * @param i index of the data to be changed
	 * @param value to replace existing data at index i
	 */
	public void setData(int i, T value) {
		this.data.set(i, value);
		notifyListeners();
	}
	
	/**
	 * Returns the data stored in index i of ArrayList data
	 * @param i the index of the data to be retrieved
	 * @return data stored in index i
	 */
	public T getData(int i) {
		return this.data.get(i);
	}
	
	/**
	 * Returns the total number of data
	 * @return data ArrayList size
	 */
	public int getSize() {
		return this.data.size();
	}
	
	/**
	 * Notifies all its listeners that some data in the model had changed
	 */
	public void notifyListeners() {
		this.listeners.forEach(l -> l.dataChanged());
	}
}
