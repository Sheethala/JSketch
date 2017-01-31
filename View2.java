// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.Point2D;
import javax.vecmath.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;


class View2 extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	public Point2D pointStart = null;
	public Point2D pointEnd = null;
	ArrayList<MyShape> shapes = new ArrayList<MyShape>();

	View2(Model model_) {
		// create UI
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// set the model
		model = model_;
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)r
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
					if(model.selection){
						checkIfObjectSelected(e.getX(), e.getY());
					}
						pointStart = new Point2D.Double(e.getX(), e.getY());
			}

			public void checkIfObjectSelected(double x, double y){
				for(int i =0; i < shapes.size(); i++){
					boolean inShape = shapes.get(i).hittest(x,y);
					if(inShape){
						//The point is in the shape
						shapes.get(i).isSelected = true;
						repaint();
						break;
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
					if(model.selection){
						pointStart = null;
						return;
					}
					MyShape createShape = new MyShape();
					createShape.shape = getShapeType();
					createShape.addPoint(pointStart.getX(), pointStart.getY());
					createShape.addPoint(pointEnd.getX(), pointEnd.getY());
					shapes.add(createShape);
					if(createShape.strokeThickness != model.chosenStrokeThickness){
						createShape.setStrokeThickness(model.chosenStrokeThickness);
					}
					if(createShape.colour != model.chosenColour){
						createShape.setColour(model.chosenColour);
					}
					//System.out.print("The point has been added!");
					//System.out.println("The start coordinates are:");
					//System.out.println(shapes.get(shapes.size()-1).points.get(0).x + "," + shapes.get(shapes.size()-1).points.get(0).y);
					//System.out.println("The end coordinates are:");
					//System.out.println(shapes.get(shapes.size()-1).points.get(1).x + "," + shapes.get(shapes.size()-1).points.get(1).y);
					pointStart = null;
			}
		});

    addMouseMotionListener(new MouseMotionAdapter() {
    	public void mouseMoved(MouseEvent e) {
      //	pointEnd = e.getPoint();
      }

      public void mouseDragged(MouseEvent e) {
        pointEnd = new Point2D.Double(e.getX(), e.getY());
        repaint();
      }
   });
	}

	public String getShapeType(){
		if(model.line){
			return "line";
		}
		else if(model.square){
			return "square";
		}
		else if(model.circle){
			return "circle";
		}
		return "none";
	}

	public void paint(Graphics g) {
            super.paint(g);
						Graphics2D g2 = (Graphics2D) g;
            if (pointStart != null ) {
							if(!model.selection){
								g2.setColor(model.chosenColour);
								g2.setStroke(new BasicStroke(model.chosenStrokeThickness));
								if(model.line){
									g2.draw(new Line2D.Double(pointStart.getX(), pointStart.getY(), pointEnd.getX(), pointEnd.getY()));
								}
								else if(model.square){
									double height = pointEnd.getY() - pointStart.getY();
									double width =  Math.abs(pointEnd.getX() - pointStart.getX());
									g2.draw(new Rectangle2D.Double(pointStart.getX(), pointStart.getY(), width, height));
								}
								else if(model.circle){
									double radius = Math.abs(pointEnd.getX() - pointStart.getX());
									g2.draw(new Ellipse2D.Double(pointStart.getX(), pointStart.getY(), radius, radius));
								}
							}
            }
						for(int i =0; i < shapes.size(); i++){
							shapes.get(i).paint(g2);
						}
        }

	// Observer interface
	@Override
	public void update(Observable o, Object arg) {
		// just displays an 'X' for each counter value
		if (model.getCounterValue() > 0){
			this.add(new JLabel("X"));
		}
	}
}
