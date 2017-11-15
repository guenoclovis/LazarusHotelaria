package br.org.ufpr.tcc.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = newImage.createGraphics();
		g2d.drawImage(image, 0, 0, Color.WHITE, null);
		g2d.drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		g2d.dispose();
		return newImage;
	}

	public static byte[] resize(byte[] imagem, int width, int height, String extensao) throws IOException {
		InputStream in = new ByteArrayInputStream(imagem);
		BufferedImage image = ImageIO.read(in);
		if (extensao.isEmpty())
			extensao = "jpg";
		float perc = 100;
		if (image.getWidth() > width || image.getHeight() > height) {

			if ((width * 100 / image.getWidth()) < (height * 100 / image.getHeight())) {
				perc = width * 100 / image.getWidth();
			} else {
				perc = height * 100 / image.getHeight();
			}
			int largura = Math.round(image.getWidth() * perc / 100);
			int altura = Math.round(image.getHeight() * perc / 100);
			image = resize(image, largura, altura);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, extensao, baos);
			baos.flush();
			byte[] byteArray = baos.toByteArray();
			baos.close();
			return byteArray;
		} else {
			return imagem;
		}
	}

	public static byte[] serialize(Image image, String extensao) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ImageIO.write((BufferedImage) image, extensao, baos);
		baos.flush();
		byte[] byteArray = baos.toByteArray();
		baos.close();

		return byteArray;
	}

	public static Image deserialize(byte[] byteArray) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
		return ImageIO.read(in);
	}
}
