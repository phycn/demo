package cn.puhy.demo.agent;

import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker;
import javassist.*;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.Serializable;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * @author PUHY
 * 2019-03-31 22:38
 */
public class DubboConsumerClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!"com/alibaba/dubbo/rpc/cluster/support/wrapper/MockClusterInvoker".equals(className)) {
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
        CtMethod method = ctClass.getDeclaredMethod("invoke");
        CtMethod copyMethod = CtNewMethod.copy(method, ctClass, new ClassMap());
        method.setName(method.getName() + "$agent");
        copyMethod.setBody("{\n" +
                "               Object trace= cn.puhy.demo.agent.DubboConsumerClassFileTransformer.begin($args,$0);\n" +
                "                try {\n" +
                "                     return " + copyMethod.getName() + "$agent($$);\n" +
                "                } finally {\n" +
                "                    cn.puhy.demo.agent.DubboConsumerClassFileTransformer.end(trace);\n" +
                "                }\n" +
                "            }");

        ctClass.addMethod(copyMethod);
        return ctClass.toBytecode();
    }

    public static Object begin(Object args[], Object invoker) {
        RpcInvocation
                invocation = (RpcInvocation) args[0];
        MockClusterInvoker mockClusterInvoker = (MockClusterInvoker) invoker;
        DubboInfo dubboInfo = new DubboInfo();
        dubboInfo.begin = System.currentTimeMillis();
        dubboInfo.params = invocation.getArguments();
        dubboInfo.methodName = invocation.getMethodName();
        dubboInfo.interfaceName = mockClusterInvoker.getInterface().getName();
        dubboInfo.url = mockClusterInvoker.getUrl().toFullString();

        invocation.setAttachment("traceId", MDC.get("traceId"));

        return dubboInfo;
    }

    public static void end(Object dubboInfo) {
        System.out.println(dubboInfo);
    }

    public static class DubboInfo implements Serializable {
        private String traceId;
        private String eventId;
        private String interfaceName;
        private String methodName;
        private Long begin;
        private String url;
        private Object params[];

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getInterfaceName() {
            return interfaceName;
        }

        public void setInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

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

        public Object[] getParams() {
            return params;
        }

        public void setParams(Object[] params) {
            this.params = params;
        }

        @Override
        public String toString() {
            return "DubboInfo{" +
                    "traceId='" + traceId + '\'' +
                    ", eventId='" + eventId + '\'' +
                    ", interfaceName='" + interfaceName + '\'' +
                    ", methodName='" + methodName + '\'' +
                    ", begin=" + begin +
                    ", url='" + url + '\'' +
                    ", params=" + Arrays.toString(params) +
                    '}';
        }
    }
}
