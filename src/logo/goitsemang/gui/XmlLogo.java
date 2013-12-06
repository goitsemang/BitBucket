package logo.goitsemang.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logo.goitsemang.XmlImageReader;

public class XmlLogo extends JPanel{
	private XmlImageReader reader;
	
	public XmlLogo()
	{
		reader = new XmlImageReader();
		reader.load("resource\\logo.xml");
		
		setPreferredSize(new Dimension(500, 500));
	}
	
	@Override
	public void paintComponent(Graphics pg)
	{
		super.paintComponent(pg);
		Image image = reader.createJavaImage();
		pg.drawImage(image, 0, 0, this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("XmlLogo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		XmlLogo panel = new XmlLogo();
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

	}

}
