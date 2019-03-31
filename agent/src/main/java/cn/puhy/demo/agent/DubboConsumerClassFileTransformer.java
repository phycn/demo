package cn.puhy.demo.agent;

import javassist.*;
import org.slf4j.MDC;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author PUHY
 * 2019-03-31 22:38
 */
public class DubboConsumerClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (!"com/alibaba/dubbo/rpc/cluster/support/wrapper/MockClusterInvoker".equals(className)) {
            return null;
        }
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        pool.importPackage("org.slf4j.MDC");

        CtClass ctClass;
        try {
            ctClass = pool.get(className.replaceAll("/", "."));
            CtMethod method = ctClass.getDeclaredMethod("invoke");
            method.insertBefore("System.out.println(\"拦截......\");");
            method.insertBefore("MDC.get(\"traceId\");");
            method.insertAfter("MDC.remove(\"traceId\");", true);
            return ctClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
