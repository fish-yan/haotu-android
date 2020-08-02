package com.dmeyc.dmestoreyfm.utils.photoselector;


import java.io.Serializable;

public class ImageSize implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SEPARATOR = "x";

	private final int width;
	private final int height;

	public ImageSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public ImageSize(int width, int height, int rotation) {
		if (rotation % 180 == 0) {
			this.width = width;
			this.height = height;
		} else {
			this.width = height;
			this.height = width;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/** Scales down dimensions in <b>sampleSize</b> times. Returns new object. */
	public ImageSize scaleDown(int sampleSize) {
		return new ImageSize(width / sampleSize, height / sampleSize);
	}

	/** Scales dimensions according to incoming scale. Returns new object. */
	public ImageSize scale(float scale) {
		return new ImageSize((int) (width * scale), (int) (height * scale));
	}

	@Override
	public String toString() {
		return new StringBuilder(width).append(SEPARATOR).append(height).toString();
	}
}
