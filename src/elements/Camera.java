package elements;

import primitives.*;

public class Camera {

	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;

	public Camera(Point3D p0, Vector vTo, Vector vUp) {

		if (!Util.isZero(vUp.dotProduct(vTo))) {
			throw new IllegalArgumentException("the vectors vUp and vTo must be orthogonals");
		}
		this.p0 = p0;
		this.vUp = vUp.normalized();
		this.vTo = vTo.normalized();
		this.vRight = this.vTo.crossProduct(this.vUp).normalized();
	}

	public Camera setViewPlaneSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public Camera setDistance(double distance) {

		this.distance = distance;
		return this;
	}

	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		try {
			Point3D pCenter = p0.add(vTo.scale(distance));
			double rY = height / nY;
			double rX = width / nX;
			double yI = ((nY - 1) / 2d - i) * rY;

			// amount of pixels to move in x axis from pc to j
			double xJ = (-(nX - 1) / 2d + j) * rX;

			Point3D pIJ = pCenter;
			if (Util.isZero(yI) && Util.isZero((xJ))) {
				// don't change Pij
			} else if (Util.isZero(yI))
				pIJ = pIJ.add(vRight.scale(xJ));
			else if (Util.isZero(xJ))
				pIJ = pIJ.add(vUp.scale(yI));
			else {
				// move on both axes
				pIJ = pIJ.add(vRight.scale(xJ).add(vUp.scale(yI)));
			}
			return new Ray(p0, pIJ.subtract(p0));
		} catch (Exception e) {
			return null;
		}
	}

	public Vector getvUp() {
		return vUp;
	}

	public Vector getvTo() {
		return vTo;
	}

	public Vector getvRight() {
		return vRight;
	}

}
