package team.qiruan.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import team.qiruan.domain.Result;
import team.qiruan.service.FileService;
import team.qiruan.utils.ConversionUtil;

@Controller
/**
 * FileController
 */
@RequestMapping("/file")
@Slf4j
public class FileController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;
    @Value("${qiruan.uppath}")
    private String upPath;
    

    @GetMapping("/up")
    String up() {
        return "file/up";
    }

    @PostMapping(value = "/up")
    @ResponseBody
    public Result upHandle(HttpServletRequest req, @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) Integer mode, @RequestParam(required = false) Integer x,
            @RequestParam(required = false) Integer y, Model m) throws IOException {
        Random ran = new Random();
        String fileName = ConversionUtil.encode(System.currentTimeMillis(), 11)
                + ConversionUtil.encode(Math.abs(ran.nextInt()), 6) + "."
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String regEx = "[\\\\\\\\/:*?\\\"<>|]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.matches()) {
            return new Result(6, "文件上传失败，文件名包含异常字符！");
        }

        String destFileName = upPath + fileName;

        // Thumbnails.of(file.getInputStream()).toFile(destFileName);
        if (mode == 1) {
            // 图片按比例压缩
            Thumbnails.of(file.getInputStream()).size(x, y).useOriginalFormat().toFile(destFileName);
        } else if (mode == 2) {
            // 图片强制压缩
            Thumbnails.of(file.getInputStream()).size(x, y).keepAspectRatio(false).useOriginalFormat()
                    .toFile(destFileName);
        } else {
            // 文件原样保存
            File destFile = new File(destFileName);
            try {
                file.transferTo(destFile);
            } catch (IllegalStateException | IOException e) {
                return new Result(7, "文件上传失败，读写异常！");
            }
        }

        fileService.addFile(fileName, fileName.substring(fileName.lastIndexOf(".") + 1));
        return new Result(0, "图片上传成功！", "/file/down/" + fileName);
    }

    @RequestMapping(value = "/down/{name}")
    public void downloadFile(@PathVariable(value = "name", required = true) String f_name, HttpServletRequest request,
            HttpServletResponse response, @RequestHeader(required = false) String range)
            throws UnsupportedEncodingException {
        // 防止用户下载目录以外的文件
        String regEx = "[\\\\\\\\/:*?\\\"<>|]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(f_name);

        if (matcher.matches()) {
            return;
        }
        // 文件目录
        File file = new File(upPath + f_name);

        // 开始下载位置
        long startByte = 0;
        // 结束下载位置
        long endByte = file.length() - 1;

        // 有range的话
        if (range != null && range.contains("bytes=") && range.contains("-")) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String ranges[] = range.split("-");
            try {
                // 判断range的类型
                if (ranges.length == 1) {
                    // 类型一：bytes=-2343
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    // 类型二：bytes=2343-
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                    }
                }
                // 类型三：bytes=22-2343
                else if (ranges.length == 2) {
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }

            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = file.length() - 1;
            }
        }
        // 要下载的长度（为啥要加一问小学数学老师去）
        long contentLength = endByte - startByte + 1;
        // 文件名
        String fileName = file.getName();
        // 文件类型
        // String contentType = request.getServletContext().getMimeType(fileName);

        // 各种响应头设置
        // 参考资料：https://www.ibm.com/developerworks/cn/java/joy-down/index.html
        // 坑爹地方一：看代码
        response.setHeader("Accept-Ranges", "bytes");

        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            //
            e1.printStackTrace();
        }

        response.setHeader("Content-Length", String.valueOf(contentLength));
        // 坑爹地方三：Content-Range，格式为
        // [要下载的开始位置]-[结束位置]/[文件总大小]
        response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());

        BufferedOutputStream outputStream = null;
        RandomAccessFile randomAccessFile = null;
        // 已传送数据大小
        long transmitted = 0;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            // 坑爹地方四：判断是否到了最后不足4096（buff的length）个byte这个逻辑（(transmitted + len) <=
            // contentLength）要放前面！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
            // 不然会会先读取randomAccessFile，造成后面读取位置出错，找了一天才发现问题所在
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
                // 停一下，方便测试，用的时候删了就行了
                Thread.sleep(10);
            }
            // 处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();
            System.out.println("下载完毕：" + startByte + "-" + endByte + "：" + transmitted);

        } catch (ClientAbortException e) {
            System.out.println("用户停止下载：" + startByte + "-" + endByte + "：" + transmitted);
            // 捕获此异常表示拥护停止下载
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 定时删除未被使用的文件
     * 开发过程中可以把@Scheduled注解注释掉
     */
    // @Scheduled(fixedRate = 1000 * 60 * 24)
    public void deleteUnusedFiles() {
        System.out.println("正在扫描未使用的文件……");
        List<team.qiruan.domain.File> unusedFiles = fileService.getUnusedFile();
        List<team.qiruan.domain.File> deletedFiles = new LinkedList<>();
        for (team.qiruan.domain.File i : unusedFiles) {
            System.out.println("未使用的文件：" + i);
            File file = new File(upPath + i.getFilename());
            if (file.delete()) {
                deletedFiles.add(i);
                System.out.println("删除" + i.getFilename() + "成功。");
            } else {
                System.out.println("删除" + i.getFilename() + "失败。");
            }
        }
        log.info("删除数据库中的记录共{}条……",deletedFiles.size());
        fileService.deleteFiles(deletedFiles);
    }
}