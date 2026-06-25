package team6.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {
    public static String generateSimpleASCIIQRCode(String content) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 3, 3);

            StringBuilder sb = new StringBuilder();
            sb.append("│      QR CODE     │");
            for (int y = 0; y < bitMatrix.getHeight(); y++) {
                for (int x = 0; x < bitMatrix.getWidth(); x++) {
                    boolean isBlack = bitMatrix.get(x, y);
                    sb.append(isBlack ? "█" : " ");
                }
                sb.append("\n");
            }


            return sb.toString();
        } catch (WriterException e) {
            return "Errore nella generazione del QR Code: " + e.getMessage();
        }
    }
}
