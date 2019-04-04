package cn.puhy.demo.agent;

import javassist.*;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Optional;
import java.util.UUID;

/**
 * @author PUHY
 * 2019-03-31 22:34
 */
public class WebClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        String agentClassName = "javax/servlet/http/HttpServlet";
        if (!agentClassName.equals(className)) {
            return null;
        }
        System.out.println("拦截javax/servlet/http/HttpServlet");
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        pool.importPackage("org.slf4j.MDC");
        pool.importPackage("java.util.UUID");
        CtClass ctClass;
        try {
            ctClass = pool.get(agentClassName.replaceAll("/", "."));
            CtMethod method = ctClass.getDeclaredMethod("service", pool.get(new String[]{"javax.servlet.http.HttpServletRequest", "javax.servlet.http.HttpServletResponse"}));
            method.insertBefore("System.out.println(\"拦截......\");");
            method.insertBefore("MDC.put(\"traceId\", UUID.randomUUID().toString());");
            method.insertAfter("MDC.remove(\"traceId\");", true);
            return ctClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void begin(Object args[]) {
        HttpServletRequest request = (HttpServletRequest) args[0];
        String traceId = request.getHeader("traceId");
        String eventId;
        if (traceId == null || traceId.equals("")) {
            traceId = UUID.randomUUID().toString();
            eventId = traceId;
        } else {
            eventId = UUID.randomUUID().toString();
        }
        String parentEventId = Optional.of(request.getHeader("eventId")).orElse(traceId);
        TraceSession traceSession = new TraceSession(traceId, eventId, parentEventId);

        MDC.put("traceId", traceId);
        MDC.put("eventId", eventId);
    }
}
