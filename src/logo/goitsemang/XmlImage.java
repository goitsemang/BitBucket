package logo.goitsemang;

import java.util.List;

public class XmlImage {
	private int width;
	private int height;
	private int depth;
	private List<Point> points;
	private List<Joint> joints;
	
	public List<Point> getPoints() {
		return points;
	}
	public void setPoints(List<Point> points) {
		this.points = points;
	}
	public List<Joint> getJoints() {
		return joints;
	}
	public void setJoints(List<Joint> joints) {
		this.joints = joints;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + depth;
		result = prime * result + height;
		result = prime * result + ((joints == null) ? 0 : joints.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + width;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XmlImage other = (XmlImage) obj;
		if (depth != other.depth)
			return false;
		if (height != other.height)
			return false;
		if (joints == null) {
			if (other.joints != null)
				return false;
		} else if (!joints.equals(other.joints))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	
}
