package br.org.ufpr.tcc.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

	public static void copiarArquivos(File src, File dst) throws IOException {

		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
		src.delete();

	}

	public static byte[] lerFotoParaByteArray(String pathFoto) throws IOException {

		BufferedImage img = null;
		File file = null;
		byte[] imageData = null;

		try {

			file = new File(pathFoto);
			String extensao = obterExtensaoArquivo(pathFoto);
			img = ImageIO.read(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, extensao, baos);
			imageData = baos.toByteArray();
		} catch (IOException e) {
			throw e;
		}

		return imageData;
	}

	public static String obterExtensaoArquivo(String nomeArquivo) {
		String extensao = null;
		if (nomeArquivo != null) {
			String[] parts = nomeArquivo.split("[.]");
			if (parts.length > 0) {
				extensao = parts[parts.length - 1].toLowerCase();
			}
		}
		
		return extensao;
	}

	public static void main(String[] args) {
		String nomeArquivo = "teste123_2017-11-15_215038.min.jpg";

		File src = new File("C:\\wildfly-10.1.0.Final\\bin\\fotos" + File.separator + Constantes.NOME_PASTA_TMP_FOTOS
				+ File.separator + nomeArquivo);
		File dst = new File("C:\\wildfly-10.1.0.Final\\bin\\fotos" + File.separator + Constantes.NOME_PASTA_DEF_FOTOS
				+ File.separator + nomeArquivo);

		try {
			copiarArquivos(src, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
