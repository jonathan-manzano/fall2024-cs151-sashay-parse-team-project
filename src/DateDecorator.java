import java.util.ArrayList;

public interface DateDecorator {

	public static String readableFormat(String dateTime) {
		String[] dateTimeSplit = dateTime.split("[-T:.]");
		
		String[] months = {
				"January", "February", "March", "April", "May", "June", 
				"July", "August", "September", "October", "November", "December"
		};
		
		String decoratedDateTime = "";
		
		
		int hour = Integer.valueOf(dateTimeSplit[3]);
		String meridiem = (hour/12 > 0) ? "PM" : "AM";
		
		decoratedDateTime += months[Integer.valueOf(dateTimeSplit[1])-1] + " " + dateTimeSplit[2] + ", " + dateTimeSplit[0] + " ";
		decoratedDateTime += (hour % 12) + ":" + dateTimeSplit[4] + " " + meridiem;
		
		return decoratedDateTime;
	}
	
}
