public interface StringAligner {

	/**
	 * Centers a string within a given width.
	 * @param text The string to be centered.
	 * @param totalWidth The total width of the string including padding.
	 * @return The centered string.
	 */
	public static String centerAlignString(String text, int totalWidth) {
		if (text.length() >= totalWidth) return text;

		int padding = (totalWidth - text.length()) / 2;

		String centeredText = String.format("%" + (padding + text.length()) + "s", text);
		centeredText = String.format("%-" + totalWidth + "s", centeredText);
		
		return centeredText;
	}
	
	public static String rightAlignString(String text, int totalWidth) {
		if (text.length() >= totalWidth) return text;
		
		return String.format("%-" + totalWidth + "s", text);
	}
	
}
