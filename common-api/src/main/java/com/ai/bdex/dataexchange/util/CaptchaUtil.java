package com.ai.bdex.dataexchange.util;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.captcha.CaptchaFactory;
import com.ai.paas.queue.CodeImage;
import com.ai.paas.queue.Consumer;
import com.ai.paas.queue.Producer;

public class CaptchaUtil extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(CaptchaUtil.class);

    private final static String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";

    private static Producer<CodeImage> captchaProducer = null;
    private Consumer<CodeImage> captchaConsumer = null;

    @Override
    public void init() throws ServletException {
        // 初始化方法
        log.info("Start to init the Captcha Consumer ...");
        captchaProducer = new Producer<>(new CaptchaFactory(78, 32, 4, 4096));
        captchaProducer.start();
        captchaConsumer = new Consumer<>(captchaProducer.getQueue());
    }

    /**
     * 根据sessionid 获取目前的验证码
     */
    public static String getCaptchaCode(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session || null == session.getAttribute(CAPTCHA_SESSION_KEY))
            return null;
        return (String) session.getAttribute(CAPTCHA_SESSION_KEY);
    }

    /**
     * 验证验证码
     */
    public static boolean verifyCaptcha(HttpServletRequest request, String captchaCode) {
        return verify(request, captchaCode, CAPTCHA_SESSION_KEY);
    }

    static boolean verify(HttpServletRequest request, String code, String key) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        String initCode = (String) session.getAttribute(key);
        if (null != initCode && initCode.equalsIgnoreCase(code)) {
            session.removeAttribute(key);
            return true;
        }
        return false;
    }

    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // 开始消费图片
        CodeImage coder = captchaConsumer.consume();
        if (null != coder) {
            // 开始输出图片
            writeImageToRespone(response, coder.getImage());
            session.setAttribute(CAPTCHA_SESSION_KEY, coder.getCode());
        }
    }

    private static void writeImageToRespone(HttpServletResponse response, BufferedImage buffImg) {
        ServletOutputStream sos = null;
        ImageWriter writer = null;
        ImageOutputStream ios = null;
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            sos = response.getOutputStream();
            // 使用新的生成图片方式
            // 声明一个虚拟图片源
            IIOImage ioImage = new IIOImage(buffImg, null, null);
            // 获取输出流助手
            writer = ImageIO.getImageWritersByFormatName("jpeg").next();
            // 设置参数
            ImageWriteParam param = writer.getDefaultWriteParam();
            // 显式压缩
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.2f);
            // 生成图片输出流
            ios = ImageIO.createImageOutputStream(sos);
            // 设置输出流
            writer.setOutput(ios);
            // 输出图片
            writer.write(ioImage);
            sos.flush();
        } catch (Exception e) {
            log.error("write image error:" + e);
        } finally {
            if (null != writer) {
                writer.dispose();
            }
            // 关闭图片输出流
            if (null != ios) {
                try {
                    ios.close();
                } catch (SocketException ignored) {
                } catch (IOException e) {
                    log.error("write image error:" + e);
                }
            }
            if (null != sos) {
                try {
                    sos.close();
                } catch (IOException e) {
                    log.error("write image error:" + e);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        genCaptcha(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        genCaptcha(request, response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        genCaptcha(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (null != captchaProducer) {
            captchaProducer.close();
        }
    }
}
