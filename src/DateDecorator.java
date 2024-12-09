/**
 * DateDecorator is an interface that decorates a given dateTime string
 */
public interface DateDecorator {

	/**
	 * Converts the given dateTime string to a readable format
	 * @param dateTime the string to be processed
	 * @return a date and time string formated as [month day, year hour:minutes:seconds meridiem]
	 */
	public static String readableFormat(String dateTime) {
		String[] dateTimeSplit = dateTime.split("[-T:.]");
		
		String[] months = {
				"January", "February", "March", "April", "May", "June", 
				"July", "August", "September", "October", "November", "December"
		};
		
		String decoratedDateTime = "";
		
		int hour = Integer.valueOf(dateTimeSplit[3]);
		String meridiem = (hour/12 > 0) ? "PM" : "AM";
		hour = (hour % 12 == 0) ? 12 : hour % 12; 
		
		decoratedDateTime += months[Integer.valueOf(dateTimeSplit[1])-1] + " " + dateTimeSplit[2] + ", " + dateTimeSplit[0] + " ";
		decoratedDateTime += hour + ":" + dateTimeSplit[4] + ":" + dateTimeSplit[5] + " " + meridiem;
		
		return decoratedDateTime;
	}
	
}
