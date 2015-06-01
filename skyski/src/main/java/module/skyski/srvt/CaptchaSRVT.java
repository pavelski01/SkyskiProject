package module.skyski.srvt;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet(name="Captcha", urlPatterns={"/captcha.jpg"}) 
public class CaptchaSRVT extends HttpServlet
{
    @Override protected void doGet(
        HttpServletRequest _request, HttpServletResponse _response
    ) throws IOException, ServletException
    {
    	_response.setContentType("image/jpeg");
    	_response.setDateHeader("Expires", 0);
        _response.setDateHeader("Max-Age", 0);
        _response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        _response.setHeader("Pragma", "no-cache");
        this.bufferedImage = new BufferedImage(126, 26, BufferedImage.TYPE_INT_RGB);
        this.graphics2D = this.bufferedImage.createGraphics();
        this.random = new Random();
        this.token = Long.toString(Math.abs(this.random.nextLong()), 36);
        this.characters = this.token.substring(0, 6);
        this.gradientPaint = new GradientPaint(50, 30, Color.RED, 15, 25, Color.DARK_GRAY, true);
        this.graphics2D.setPaint(this.gradientPaint);
        this.font = new Font("Verdana", Font.CENTER_BASELINE, 26);
        this.graphics2D.setFont(this.font);
        this.graphics2D.drawString(this.characters, 2, 20);
        this.graphics2D.dispose();
        this.session = _request.getSession(true);
        this.session.setAttribute(CAPTCHA_KEY, this.characters);
        this.outputStream = _response.getOutputStream();
        ImageIO.write(this.bufferedImage, "jpg", this.outputStream);
        this.outputStream.close();
    }
	
    public static final String CAPTCHA_KEY = "captcha_key";
    private BufferedImage bufferedImage;
    private Font font;
    private GradientPaint gradientPaint;
    private Graphics2D graphics2D;
    private HttpSession session;
    private OutputStream outputStream;
    private Random random;
    private String characters;
    private String token;
    private static final long serialVersionUID = 1L;
}