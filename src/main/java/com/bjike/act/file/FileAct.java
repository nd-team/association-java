package com.bjike.act.file;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.util.file.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 文件
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-28 16:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("file")
public class FileAct {
    private static final String[] IMAGE_SUFFIX = new String[]{"jpg", "bmp", "gif", "png", "jpeg", "svg"};

    /**
     * 获取缩略图
     * @return {name:'data',type:'bytes',defaultValue:'',description:'图片流.'}
     * @param path 图片路径
     * @throws ActException
     * @version v1
     */
    @GetMapping("thumbnails")
    public void thumbnails(HttpServletResponse response, String path) throws ActException {
        try {
            String fileName = StringUtils.substringAfterLast(path, "/");
            String suffix = StringUtils.substringAfterLast(path, ".");
            boolean exist = false;
            for (String sx : IMAGE_SUFFIX) {
                if (sx.equalsIgnoreCase(suffix)) {
                    exist = true;
                }
            }
            if (exist) {
                try {
                    Thumbnails.Builder<java.io.File> fileBuilder = Thumbnails.of(FileUtil.getRealPath(path))
                            .forceSize(200, 160)
                            .outputQuality(0.35f)
                            .outputFormat(suffix);
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    fileBuilder.toOutputStream(os);
                    response.setContentType("text/html; charset=UTF-8");
                    response.setContentType("image/jpeg");

                    FileUtil.writeOutFile(response, os.toByteArray(), fileName);
                } catch (IOException e) {
                    throw new SerException("缩略图获取错误");
                }
            } else {
                throw new SerException("不支持" + suffix + "该文件类型缩略图");
            }

        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取原图
     * @return {name:'data',type:'bytes',defaultValue:'',description:'图片流.'}
     * @param path 图片路径
     * @throws ActException
     * @version v1
     */
    @GetMapping("original/pic")
    public void original(HttpServletResponse response, String path) throws ActException {
            String fileName = StringUtils.substringAfterLast(path, "/");
            String suffix = StringUtils.substringAfterLast(path, ".");
            boolean exist = false;
            for (String sx : IMAGE_SUFFIX) {
                if (sx.equalsIgnoreCase(suffix)) {
                    exist = true;
                }
            }
        try {
            if (exist) {
                try {
                    byte[] bytes = FileUtil.FileToByte(FileUtil.getRealPath(path));
                    FileUtil.writeOutFile(response, bytes, fileName);
                } catch (IOException e) {
                    throw new SerException("获取原图错误");
                }
            } else {
                throw new SerException("不支持" + suffix + "该文件类型");
            }

        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下载
     * @return {name:'data',type:'bytes',defaultValue:'',description:'文件流.'}
     * @param path 文件路径
     * @version v1
     * @throws ActException
     */
    @GetMapping("download")
    public void download(HttpServletResponse response, String path) throws ActException {
        try {
            String fileName = StringUtils.substringAfterLast(path, "/");
            try {
                byte[] bytes = FileUtil.FileToByte(FileUtil.getRealPath(path));
                FileUtil.writeOutFile(response, bytes, fileName);
            } catch (IOException e) {
                throw new SerException("下载错误");
            }
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

}
