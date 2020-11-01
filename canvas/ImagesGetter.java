package com.imagewinnner.canvas;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Stack;

import javax.imageio.ImageIO;

public class ImagesGetter {
	
	private static final List<String> imagesURL = List.of(
		"https://i.pinimg.com/564x/9e/56/43/9e56438baaf44c0fa49bd12c166c59bf.jpg",		
		"https://i.pinimg.com/564x/c4/b8/7a/c4b87a02273ba1989ed00d8523f279bf.jpg",
		"https://i.pinimg.com/564x/dd/90/30/dd9030237f01f4a15d69fbc175a8cdf1.jpg",
		"https://i.pinimg.com/564x/b1/61/e3/b161e373f1903ce87add44376da29cf4.jpg",
		"https://i.pinimg.com/564x/6e/c7/ce/6ec7ce4b186c9a0e98f31b1189cc6067.jpg",
		"https://i.pinimg.com/564x/89/da/88/89da889f411175f0e34b1670fead4de9.jpg",
		"https://i.pinimg.com/564x/98/84/49/988449d676df72276cd950a9fa2ebd71.jpg",
		"https://i.pinimg.com/564x/02/04/67/02046744964436c7fdb36099e1c495c9.jpg",
		"https://i.pinimg.com/564x/f9/bd/77/f9bd7764c47e809581ae0a3086c970fc.jpg",
		"https://i.pinimg.com/564x/14/1f/f8/141ff8b4ee9d6fc1132d1df951c051c7.jpg",
		"https://i.pinimg.com/564x/7e/43/fa/7e43fa7d2cd0d07eb7e96a48b1a07683.jpg",
		"https://i.pinimg.com/564x/63/bc/69/63bc690d671010f20a97aab8994e705f.jpg",
		"https://i.pinimg.com/564x/f5/64/12/f564127500e2a03807bfcb419c5b369d.jpg",
		"https://i.pinimg.com/564x/32/dd/0e/32dd0e0d4e0dc6a0cb1051a311a0aa2b.jpg",
		"https://i.pinimg.com/564x/19/21/31/192131de75cba1fd04f0a335e5ee77fc.jpg",
		"https://i.pinimg.com/564x/32/33/01/323301e48b19a954c6b9263e69670e01.jpg",
		"https://i.pinimg.com/564x/4a/7c/5a/4a7c5a63fe4d2a6c7f8d140c0d2b45b1.jpg",
		"https://i.pinimg.com/564x/82/b4/ca/82b4cab290df7eb5f3fbaa1c4aa7e793.jpg"
		);
	

	public static Stack<BufferedImage> getImages() throws IOException {
		Stack<BufferedImage> imagesStack = new Stack<>();
		
		for (String imageURL : imagesURL) {
	        System.out.println("Getting Image from " + imageURL);
	        URL url = new URL(imageURL);
	        imagesStack.push(ImageIO.read(url));
		}
		
		return imagesStack;
	}

}
