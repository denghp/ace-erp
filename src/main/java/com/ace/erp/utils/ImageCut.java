package com.ace.erp.utils;

/**
 * Project_Name: ace
 * File: ImageCut
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 11/28/13
 * Time: 3:41 PM
 * Description:
 */
public class ImageCut {
    /**
     * 图片切割
     *
     * @param imagePath 原图地址
     * @param x         目标切片坐标 X轴起点
     * @param y         目标切片坐标 Y轴起点
     * @param w         目标切片 宽度
     * @param h         目标切片 高度
     */
    /**
    public void cutImage(String imagePath, int x, int y, int w, int h) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(imagePath));
            int srcWidth = bi.getWidth();      // 源图宽度
            int srcHeight = bi.getHeight();    // 源图高度

            //若原图大小大于切片大小，则进行切割
            if (srcWidth >= w && srcHeight >= h) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);

                int x1 = x * srcWidth / 400;
                int y1 = y * srcHeight / 270;
                int w1 = w * srcWidth / 400;
                int h1 = h * srcHeight / 270;

                cropFilter = new CropImageFilter(x1, y1, w1, h1);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", new File(imagePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      **/
}
