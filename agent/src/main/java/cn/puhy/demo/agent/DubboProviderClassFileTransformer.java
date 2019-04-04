package cn.puhy.demo.agent;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcInvocation;
import javassist.*;
import org.slf4j.MDC;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class DubboProviderClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!"com/alibaba/dubbo/rpc/filter/ClassLoaderFilter".equals(className)) {
            return null;
        }
        try {
            return buildBytes(loader, className.replaceAll("/", "."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] buildBytes(ClassLoader loader, String target) throws Exception {
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass ctClass = pool.get(target);
        CtMethod method = ctClass.getDeclaredMethod("invoke");
        CtMethod copyMethod = CtNewMethod.copy(method, ctClass, new ClassMap());
        method.setName(method.getName() + "$agent");
        copyMethod.setBody("{\n" +
                "               Object trace= cn.puhy.demo.agent.DubboProviderClassFileTransformer.begin($args);\n" +
                "                try {\n" +
                "                     return " + copyMethod.getName() + "$agent($$);\n" +
                "                } finally {\n" +
                "                   cn.puhy.demo.agent.DubboProviderClassFileTransformer.end(trace);\n" +
                "                }\n" +
                "            }");

        ctClass.addMethod(copyMethod);
        return ctClass.toBytecode();
    }

    public static Object begin(Object[] args) {
        Invoker i = (Invoker) args[0];
        RpcInvocation rpcInvocation = (RpcInvocation) args[1];
        String traceId = rpcInvocation.getAttachment("traceId");
        MDC.put("traceId", traceId);
        System.out.println("服务接收 traceId=" + traceId);
        return new Object();
    }

    public static void end(Object arg) {
        // 关闭会话
    }
}
