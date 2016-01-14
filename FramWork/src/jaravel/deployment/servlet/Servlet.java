package jaravel.deployment.servlet;

import jaravel.framework.Jaravel;
import jaravel.framework.web.Headers;
import jaravel.framework.web.Request;
import jaravel.framework.web.RequestType;
import jaravel.framework.web.Response;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by Sinius on 14-1-2016.
 */
public class Servlet extends HttpServlet {

    private Jaravel framework;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String jaravelFrameworkClass = config.getInitParameter("FrameworkClass");

        if(jaravelFrameworkClass == null)
            throw new ServletException("Argument FrameworkClass must exist");

        try {
            Class<?> aClass = Class.forName(jaravelFrameworkClass);

            if(aClass == null)
                throw new ServletException("Argument FrameworkClass class could not be found.");

            if(!Jaravel.class.isAssignableFrom(aClass))
                throw new ServletException("Argument "+ aClass.getName() +" must extend jaravel.framework.Jaravel");

            Object object = aClass.newInstance();

            if(object == null)
                throw new ServletException("New instance of " + jaravelFrameworkClass + " could not be initialized.");

            framework = (Jaravel) object;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String domain = req.getServerName() + (req.getServerPort() != 80 ? ":" + req.getServerPort() : "");

        HashMap<String, String> headers = new HashMap<>();
        Enumeration<String> j = req.getHeaderNames();
        while(j.hasMoreElements()){
            String name = j.nextElement();
            headers.put(name, req.getHeader(name));
        }

        URI uri;
        try {
            uri = new URI(req.getRequestURI());
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI.");
        }


        Request request = new Request(domain, RequestType.GET, uri, new Headers(headers));

        Response response = framework.handle(request);

        String content = response.render();

        resp.setStatus(response.statusCode);
        resp.setContentLength(content.length());
        resp.getWriter().print(content);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }
}
