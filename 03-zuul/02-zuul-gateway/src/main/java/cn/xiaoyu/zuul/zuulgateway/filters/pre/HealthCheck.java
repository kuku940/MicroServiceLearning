package cn.xiaoyu.zuul.zuulgateway.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 健康检查
 */
@Component
public class HealthCheck extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        String path = RequestContext.getCurrentContext().getRequest().getRequestURI();
        return path.equalsIgnoreCase(uri()) || path.toLowerCase().endsWith(uri());
    }

    private String uri() {
        return "/healthcheck";
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // Set the default response code for static filter to be 200
        ctx.getResponse().setStatus(HttpServletResponse.SC_OK);
        ctx.getResponse().setContentType("application/xml");

        // first StaticResponseFilter instance to match wins, others don't set body or status
        if (ctx.getResponse() == null) {
            ctx.setResponseBody("<health>ok</health>");
            ctx.setSendZuulResponse(false);
        }
        return null;
    }
}
