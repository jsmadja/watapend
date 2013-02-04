package com.watapend;

import com.watapend.domain.Resume;
import com.watapend.domain.Sources;
import org.joda.time.DateMidnight;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

public class WatapendServer extends AbstractHandler {

    public static final String QUERY_PARAMETER = "q";

    private Watapend watapend = new Watapend();

    public WatapendServer() throws MalformedURLException {
        watapend.ajouterLesSources(Sources.toutes());
    }

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
        String resp = request.getParameter(QUERY_PARAMETER);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        Resume resume;
        if ("all".equalsIgnoreCase(resp)) {
            resume = watapend.genereLeResumeComplet();
        } else if ("yesterday".equalsIgnoreCase(resp)) {
            resume = watapend.genereLeResumeAPartirDu(hier());
        } else {
            resume = watapend.genereLeResumeAPartirDu(aujourdhui());
        }
        writer.println(resume.toString());
        writer.close();
    }

    private DateMidnight hier() {
        return DateMidnight.now().minusDays(1);
    }

    private DateMidnight aujourdhui() {
        return DateMidnight.now();
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new WatapendServer());
        server.start();
        server.join();
    }

}
