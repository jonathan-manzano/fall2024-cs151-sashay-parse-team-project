import java.awt.*;

public abstract class TextFieldListener<T> extends TextField implements DataListener{
	DataModel<T> model;
	
	public TextFieldListener (int cols, DataModel<T> model) {
		super(cols);
		this.model = model;
	}

	public abstract void dataChanged();
}
