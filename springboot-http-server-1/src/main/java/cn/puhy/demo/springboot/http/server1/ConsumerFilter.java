package cn.puhy.demo.springboot.http.server1;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.Map;

@Activate(group = Constants.CONSUMER)
public class ConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("111111111111111111");
        Map<String, String> attachments = invocation.getAttachments();
        attachments.put("X-B3-TraceId", MDC.get("X-B3-TraceId"));
        return invoker.invoke(invocation);
    }
}
