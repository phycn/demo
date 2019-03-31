package cn.puhy.demo.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author PUHY
 * 2019-03-30 18:18
 */
public class Agent {

    public static void premain(String arg, Instrumentation instrumentation) {
        instrumentation.addTransformer(new WebClassFileTransformer());
        instrumentation.addTransformer(new DubboConsumerClassFileTransformer());
    }
}
