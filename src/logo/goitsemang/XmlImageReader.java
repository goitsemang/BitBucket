package logo.goitsemang;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import logo.goitsemang.exception.SystemException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlImageReader {

	private XMLReader xmlReader;
	private XmlImage xmlImage;


	public XMLReader load(String filename) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		 spf.setNamespaceAware(true);
		try {

			SAXParser saxParser = spf.newSAXParser();
			xmlReader = saxParser.getXMLReader();

			xmlReader.setContentHandler(new XmlImageHandler());
			xmlReader.parse(new InputSource(filename));
		} catch (Exception e) {
			throw new SystemException("Cant parse xml: " + e.getMessage(), e);
		}

		return xmlReader;
	}

	public XmlImage getImage() {
		return xmlImage;
	}
	
	public Image createJavaImage()
	{
		Image image = new BufferedImage(xmlImage.getWidth(), xmlImage.getHeight(), Image.SCALE_SMOOTH);
		Graphics pg = image.getGraphics();
		for(Joint joint : xmlImage.getJoints())
		{
			pg.drawLine(joint.getPointA().getX(), joint.getPointA().getY(), joint.getPointB().getX(), joint.getPointB().getY());
		}
		return image;
	}

	private class XmlImageHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {			
			if ("img".equals(qName)) {
				xmlImage = new XmlImage();
				xmlImage.setWidth(Integer.parseInt(attributes.getValue("width")));
				xmlImage.setHeight(Integer.parseInt(attributes.getValue("height")));
				xmlImage.setDepth(Integer.parseInt(attributes.getValue("depth")));
			}else if("points".equals(qName))
			{
				xmlImage.setPoints(new ArrayList<Point>());
			}else if("point".equals(qName))
			{
				Point point = new Point();
				point.setX(Integer.parseInt(attributes.getValue("x")));
				point.setY(Integer.parseInt(attributes.getValue("y")));
				point.setZ(Integer.parseInt(attributes.getValue("z")));
				xmlImage.getPoints().add(point);
			}else if("joints".equals(qName))
			{
				xmlImage.setJoints(new ArrayList<Joint>());
			}else if("joint".equals(qName))
			{
				Joint joint = new Joint();
				String a = attributes.getValue("pointA");
				String b = attributes.getValue("pointB");
				joint.setPointA(xmlImage.getPoints().get(Integer.parseInt(a)));
				joint.setPointB(xmlImage.getPoints().get(Integer.parseInt(b)));
				xmlImage.getJoints().add(joint);
			}
		}
	}
}
