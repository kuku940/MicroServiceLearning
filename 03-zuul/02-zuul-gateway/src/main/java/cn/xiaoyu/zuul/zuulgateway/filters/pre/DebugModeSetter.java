package cn.xiaoyu.zuul.zuulgateway.filters.pre;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 设置网关调试模式
 */
public class DebugModeSetter extends ZuulFilter {
    static final DynamicBooleanProperty couldSetDebug = DynamicPropertyFactory.getInstance().getBooleanProperty("zuul.config.set.debug", true);
    static final DynamicBooleanProperty debugRequest = DynamicPropertyFactory.getInstance().getBooleanProperty("zuul.debug.request", false);
    static final DynamicStringProperty debugParameter = DynamicPropertyFactory.getInstance().getStringProperty("zuul.debug.parameter", "debugRequest");

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -100;
    }

    @Override
    public boolean shouldFilter() {
        if (!couldSetDebug.get()) {
            return false;
        }
        if ("true".equals(RequestContext.getCurrentContext().getRequest().getParameter(debugParameter.get()))) {
            return true;
        }
        return debugRequest.get();
    }

    @Override
    public Object run() {
        RequestContext.getCurrentContext().setDebugRequest(true);
        RequestContext.getCurrentContext().setDebugRouting(true);
        return null;
    }
}
