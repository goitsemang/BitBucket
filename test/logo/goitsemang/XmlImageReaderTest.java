package logo.goitsemang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.XMLReader;

public class XmlImageReaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void loadTest() {
		XmlImageReader reader = new XmlImageReader();
		XMLReader doc = reader.load("C:\\Users\\Mr G\\Documents\\Projects\\MultichoiceProj\\test.xml");
		
		assertNotNull(doc);
		assertNotNull(doc.getContentHandler());
	}
	
	@Test
	public void xmlToPointsTest()
	{
		XmlImageReader reader = new XmlImageReader();
		reader.load("C:\\Users\\Mr G\\Documents\\Projects\\MultichoiceProj\\test.xml");
		
		List<XmlImage> images = reader.getImages();
		assertNotNull(images);
		assertEquals(1, images.size());
		
		XmlImage image = images.get(0);
		assertNotNull(image.getPoints());
		assertEquals(20, image.getWidth());
		assertEquals(40, image.getHeight());
		assertEquals(30, image.getDepth());
		assertEquals(2, image.getPoints().size());
		
		Point controlA = new Point();
		controlA.setX(1);
		controlA.setY(2);
		controlA.setZ(3);
		
		Point pointA = image.getPoints().get(0);
		assertEquals(controlA.getX(), pointA.getX());
		assertEquals(controlA.getY(), pointA.getY());
		assertEquals(controlA.getZ(), pointA.getZ());
		assertEquals(controlA, pointA);
		

		Point controlB = new Point();
		controlB.setX(5);
		controlB.setY(6);
		controlB.setZ(7);
		
		Point pointB = image.getPoints().get(1);
		assertEquals(controlB.getX(), pointB.getX());
		assertEquals(controlB.getY(), pointB.getY());
		assertEquals(controlB.getZ(), pointB.getZ());
		assertEquals(controlB, pointB);
		
		assertNotNull(image.getJoints());
		assertEquals(1, image.getJoints().size());
		
		Joint controllJoint = new Joint();
		controllJoint.setPointA(controlA);
		controllJoint.setPointB(controlB);
		
		Joint joint = image.getJoints().get(0);
		assertEquals(controllJoint.getPointA(), joint.getPointA());
		assertEquals(controllJoint.getPointB(), joint.getPointB());
		assertEquals(controllJoint, joint);
	}
	
	@Test
	public void createJavaImagesTest()
	{
		XmlImageReader reader = new XmlImageReader();
		reader.load("C:\\Users\\Mr G\\Documents\\Projects\\MultichoiceProj\\test.xml");
		
		
		List<Image> images = reader.createJavaImages();
		assertNotNull(images);
		assertEquals(1, images.size());
		
		
		Point controlA = new Point();
		controlA.setX(1);
		controlA.setY(2);
		controlA.setZ(3);
		Point controlB = new Point();
		controlB.setX(5);
		controlB.setY(6);
		controlB.setZ(7);
		Joint controllJoint = new Joint();
		controllJoint.setPointA(controlA);
		controllJoint.setPointB(controlB);
		XmlImage controlXmlImage = new XmlImage();
		controlXmlImage.setWidth(20);
		controlXmlImage.setHeight(40);
		controlXmlImage.setDepth(30);
		controlXmlImage.setPoints(new ArrayList<Point>());
		controlXmlImage.getPoints().add(controlA);
		controlXmlImage.getPoints().add(controlB);
		controlXmlImage.setJoints(new ArrayList<Joint>());
		controlXmlImage.getJoints().add(controllJoint);
		
		Image controlImage = new BufferedImage(controlXmlImage.getWidth(), controlXmlImage.getHeight(), Image.SCALE_SMOOTH);
		Graphics pg = controlImage.getGraphics();
		for(Joint joint : controlXmlImage.getJoints())
		{
			pg.drawLine(joint.getPointA().getX(), joint.getPointA().getY(), joint.getPointB().getX(), joint.getPointB().getY());
		}
		
		//equals method in BufferedImage has not been overridden from object
		String controlToString = controlImage.toString();
		controlToString = controlToString.substring(controlToString.indexOf(":"));
		String imageToString = images.get(0).toString();
		imageToString = imageToString.substring(imageToString.indexOf(":"));
		
		assertEquals(controlToString, imageToString);
	}
}
