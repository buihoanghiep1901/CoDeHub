package shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Square extends Rectangle implements Comparable<Square> {
	private Integer number;
	private Text text;
	private Integer xx;// vi tri xuat hien tren truc Ox
	private Integer yy;// vi tri xuat hien tren truc Oy

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Integer getXx() {
		return xx;
	}

	public void setXx(Integer xx) {
		this.xx = xx;
	}

	public Integer getYy() {
		return yy;
	}

	public void setYy(Integer yy) {
		this.yy = yy;
	}

	public Square() {
		super();
	}

//	public Square(Integer number) {
//		super();
//		this.number = number;
//		this.setFill(Color.web("#A7FF71"));// set ben trong
//		this.setStroke(Color.web("#ff5050"));// set vien
//		this.setStrokeWidth(3);// set do rong cua vien
//
//		// set chieu dai, chieu rong
//		this.setWidth(60);
//		this.setHeight(60);
//		this.setArcHeight(150);
//		this.setArcWidth(150);
//
//		this.text = new Text(number.toString());
//		text.setFont(Font.font(20));
//		text.setStroke(Color.BLACK);
//	}

	public Square(Integer number, Integer layoutX, Integer layoutY) {
		super();
		this.number = number;
		this.xx = layoutX;
		this.yy = layoutY;
		this.setFill(Color.web("#B9FC90"));
		this.setStroke(Color.web("#ff5050"));
		this.setStrokeWidth(3);

		// set chieu dai, chieu rong
		this.setWidth(60);
		this.setHeight(60);
		this.setArcHeight(15);
		this.setArcWidth(15);

		this.text = new Text(number.toString());
		text.setFont(Font.font(20));
		text.setStroke(Color.BLACK);
	}

	public void changeBackGround(Color color) {
		this.setFill(color);
	}

	public void changeBorder(Color color) {
		this.setStroke(color);
	}

	public void delete() {
		this.setFill(null);
		this.setStroke(null);
		this.text.setVisible(false);
	}

	@Override
	public int compareTo(Square square) {
		return this.getNumber() < square.getNumber() ? 1 : -1;
	}

}
