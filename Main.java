// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

/**
 *  Two views with integrated controllers.  Uses java.util.Observ{er, able} instead
 *  of custom IView.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Graphics;

public class Main{

	public static void main(String[] args){
		JFrame frame = new JFrame("HelloMVC4");

		// create Model and initialize it
		Model model = new Model();

		// create View, tell it about model (and controller)
		View view = new View(model);
		// tell Model about View.
		model.addObserver(view);

		// create second view ...
		View2 view2 = new View2(model);
		model.addObserver(view2);

		// let all the views know that they're connected to the model
		model.notifyObservers();

		// create the window
		JPanel p = new JPanel(new BorderLayout());
		frame.getContentPane().add(p);
		p.add(view, BorderLayout.WEST);
		p.add(view2, BorderLayout.CENTER);

		frame.setPreferredSize(new Dimension(800,800));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}
	public void paint(Graphics g) {
						System.out.println("RESIZING");
					}

}
