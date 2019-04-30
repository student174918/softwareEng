package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Example of how to take single picture.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class QR_code_scanner {

	public static void main(String[] args) throws IOException {

		// get default webcam and open it
		Webcam webcam = Webcam.getDefault();
		webcam.open();

		// get image
		BufferedImage image = webcam.getImage();

		// save image to PNG file
		ImageIO.write(image, "PNG", new File("test.png"));
		
        try {
            String filePath = "C:\\Users\\Cex\\eclipse-workspace\\QR_Code\\test.png";
            String charset = "UTF-8";
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, hintMap));
        } catch (Exception e) {
            // TODO: handle exception
        }
	}


public static String readQRCode(String filePath, String charset, Map hintMap)
	    throws FileNotFoundException, IOException, NotFoundException {
	        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
	            new BufferedImageLuminanceSource(
	                ImageIO.read(new FileInputStream(filePath)))));
	        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
	        return qrCodeResult.getText();
	    }
	}
