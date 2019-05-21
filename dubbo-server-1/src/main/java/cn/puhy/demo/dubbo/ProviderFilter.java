package cn.puhy.demo.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.MDC;

@Activate(group = Constants.PROVIDER)
public class ProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("2222222222222222222222222222");
        String traceId = invocation.getAttachment("X-B3-TraceId");
        MDC.put("X-B3-TraceId", traceId);
        return invoker.invoke(invocation);
    }
}
