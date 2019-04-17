package cn.puhy.demo.agent;

import javassist.*;
import org.slf4j.MDC;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.*;

/**
 * @author PUHY
 * 2019-03-31 22:34
 */
public class WebClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (!"javax/servlet/http/HttpServlet".equals(className)) {
            return null;
        }
        try {
            return build(loader, className.replaceAll("/", "."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] build(ClassLoader loader, String name) throws Exception {
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass ctClass = pool.get(name);
        CtMethod method = ctClass.getDeclaredMethod("service",
                pool.get(new String[]{"javax.servlet.http.HttpServletRequest",
                        "javax.servlet.http.HttpServletResponse"}));
        CtMethod copyMethod = CtNewMethod.copy(method, ctClass, new ClassMap());
        method.setName(method.getName() + "$agent");
        copyMethod.setBody("{\n" +
                "               Object trace= cn.puhy.demo.agent.WebClassFileTransformer.begin($args);\n" +
                "                try {\n" +
                "                     " + copyMethod.getName() + "$agent($$);\n" +
                "                } finally {\n" +
                "                    cn.puhy.demo.agent.WebClassFileTransformer.end(trace);\n" +
                "                }\n" +
                "            }");

        ctClass.addMethod(copyMethod);
        return ctClass.toBytecode();
    }

    public static Object begin(Object args[]) {
        HttpServletRequest request = (HttpServletRequest) args[0];
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("http header: " + headerName + " = " + request.getHeader(headerName));
        }
        WebTraceInfo trace = new WebTraceInfo();
        trace.setParams(request.getParameterMap());
        trace.setCookie(request.getCookies());
        trace.setUrl(request.getRequestURI());
        trace.setBegin(System.currentTimeMillis());
        return trace;
    }

    // 插入到 service 方法的最后一行
    public static void end(Object webTraceInfo) {
        WebTraceInfo trace = (WebTraceInfo) webTraceInfo;
        trace.setUseTime(System.currentTimeMillis() - trace.getBegin());
        System.out.println(trace);
    }

    public static class WebTraceInfo {
        private String traceId;
        private String eventId;
        private Long begin;
        private String url;
        private Map<String, String[]> params;
        private Cookie[] cookie;
        private String handler;
        private Long useTime;

        public Long getBegin() {
            return begin;
        }

        public void setBegin(Long begin) {
            this.begin = begin;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Map<String, String[]> getParams() {
            return params;
        }

        public void setParams(Map<String, String[]> params) {
            this.params = params;
        }

        public Cookie[] getCookie() {
            return cookie;
        }

        public void setCookie(Cookie[] cookie) {
            this.cookie = cookie;
        }

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public Long getUseTime() {
            return useTime;
        }

        public void setUseTime(Long useTime) {
            this.useTime = useTime;
        }

        @Override
        public String toString() {
            return "WebTraceInfo{" +
                    "traceId='" + traceId + '\'' +
                    ", eventId='" + eventId + '\'' +
                    ", begin=" + begin +
                    ", url='" + url + '\'' +
                    ", params=" + params +
                    ", cookie=" + Arrays.toString(cookie) +
                    ", handler='" + handler + '\'' +
                    ", useTime=" + useTime +
                    '}';
        }
    }
}
