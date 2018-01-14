package net.homecredit.comp;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * @author vladut.stoica
 */
public class Start {

    public static void main(String[] args) throws Exception {
        String webappDirLocation = "application/src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());

        WebResourceRoot resources = new StandardRoot(ctx);
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }

}
