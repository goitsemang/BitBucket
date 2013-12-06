package logo.goitsemang;

public class Joint {

	private Point pointA;
	private Point pointB;
	
	public Point getPointA() {
		return pointA;
	}
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}
	public Point getPointB() {
		return pointB;
	}
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pointA == null) ? 0 : pointA.hashCode());
		result = prime * result + ((pointB == null) ? 0 : pointB.hashCode());
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
		Joint other = (Joint) obj;
		if (pointA == null) {
			if (other.pointA != null)
				return false;
		} else if (!pointA.equals(other.pointA))
			return false;
		if (pointB == null) {
			if (other.pointB != null)
				return false;
		} else if (!pointB.equals(other.pointB))
			return false;
		return true;
	}
	
	
	
	
}
