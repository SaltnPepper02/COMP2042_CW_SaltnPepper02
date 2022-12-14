package com.example.demo;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class is for tile design
 *
 * @author Richard Gan Soon Ching
 */
public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    /**
     * setter for modify
     *
     * @param modify
     */
    void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * getter for modify
     *
     * @return modify
     */
    boolean getModify() {
        return modify;
    }

    /**
     * Set cell design
     *
     * @param x
     * @param y
     * @param scale
     * @param root
     */
    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        this.root = root;
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    /**
     * setter for textClass
     *
     * @param textClass
     */
    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Change the cell
     *
     * @param cell
     */
    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * adder for cell
     *
     * @param cell
     */
    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * set tile color when merge happens
     *
     * @param number
     */
    void setColorByNumber(int number) {
        switch (number) {
            case 0:
                rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
                break;
            case 2:
                rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
                break;
            case 4:
                rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
                break;
            case 8:
                rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
                break;
            case 16:
                rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
                break;
            case 32:
                rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
                break;
            case 64:
                rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
                break;
            case 128:
                rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
                break;
            case 256:
                rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
                break;
            case 512:
                rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
                break;
            case 1024:
                rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
                break;
            case 2048:
                rectangle.setFill(Color.rgb(250,0,0,1));


        }

    }

    /**
     * getter for X
     *
     * @return rectangle.getX()
     */
    double getX() {
        return rectangle.getX();
    }

    /**
     * getter for Y
     *
     * @return rectangle.getY()
     */
    double getY() {
        return rectangle.getY();
    }

    /**
     * getter for number in tile
     *
     * @return
     */
    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * Write number into tile
     *
     * @return
     */
    private Text getTextClass() {
        return textClass;
    }

    /*abstract class cellColor{ // switch to polymorphism
    abstract void setColorByNumber(int number);
}

class color0 extends cellColor{
    private Shape rectangle;
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
    }
}

class color2 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
    }
}

class color4 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
    }
}

class color8 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
    }
}

class color16 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
    }
}

class color32 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
    }
}

class color64 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
    }
}

class color128 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
    }
}

class color256 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
    }
}

class color516 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
    }
}

class color1024 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
    }
}

class color2048 extends cellColor{
    private Shape rectangle;
    @Override
	void setColorByNumber(int number){
		rectangle.setFill(Color.rgb(250,0,0,1));
    }
}*/

}
