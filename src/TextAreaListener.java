import java.awt.*;

public abstract class TextAreaListener<T> extends TextArea implements DataListener{
	DataModel<T> model;
	
	public TextAreaListener (int rows, int cols, DataModel<T> model) {
		super(rows, cols);
		this.model = model;
	}

	public abstract void dataChanged();
}
