package team6.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    private static final String RESET = "\033[0m";
    private static final String BG_BLK = "\033[40m";
    private static final String BG_WHT = "\033[107m";
    private static final String FG_BLK = "\033[30m";
    private static final String FG_WHT = "\033[97m";

    public static String generateQRCode(String content) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(
                    content, BarcodeFormat.QR_CODE, 0, 0);

            int h = matrix.getHeight();
            int w = matrix.getWidth();
            StringBuilder sb = new StringBuilder();

            for (int y = 0; y < h; y += 2) {
                for (int x = 0; x < w; x++) {
                    boolean top = matrix.get(x, y);
                    boolean bot = (y + 1 < h) && matrix.get(x, y + 1);
                    sb.append(top ? FG_BLK : FG_WHT);
                    sb.append(bot ? BG_BLK : BG_WHT);
                    sb.append('▀');
                }
                sb.append(RESET).append('\n');
            }
            return sb.toString();

        } catch (WriterException e) {
            return "Errore: " + e.getMessage();
        }
    }
}
