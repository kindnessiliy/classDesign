package com.classdesign.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

/**
 * @author:zyh
 * @Time:2021-06-08-19:50
 * @email:1269231889@qq.com
 */
@Slf4j
@Component
public class QrCodeUtil {
    private static final int CODE_WIDTH = 400;
    private static final int CODE_HEIGHT = 400;
    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    public static void createCodeToFile(String codeContent, File codeImgFileSave) {
        log.info(codeImgFileSave.getName());
        try {
            if (codeContent == null || "".equals(codeContent)) {
                log.info("content is null");
                return;
            }
            codeContent = codeContent.trim();
            if (codeImgFileSave == null || codeImgFileSave.isFile()) {
                codeImgFileSave = new File(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()+"//"+ UUID.randomUUID()+".jpg");
                log.info("二维码目录为空，默认放在桌面");
            }
            if (!codeImgFileSave.exists()) {
                codeImgFileSave.mkdir();
            }
            BufferedImage bufferedImage = getBufferedImage(codeContent);
            //以jpg格式保存bufferedImage
            ImageIO.write(bufferedImage,"jpg",codeImgFileSave);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private static BufferedImage getBufferedImage(String codeContent) throws WriterException {
        HashMap<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(codeContent, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }
        return bufferedImage;
    }

    public static String parseQRCodeByFile(File file) {
        String result = null;
        if (file == null || file.isDirectory() || !file.exists()) {
            return result;
        }
        try {
            BufferedImage read = ImageIO.read(file);
            BufferedImageLuminanceSource sou = new BufferedImageLuminanceSource(read);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(sou));
            Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result decode = new MultiFormatReader().decode(bitmap, hints);
            result = decode.getText();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
