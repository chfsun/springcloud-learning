package cn.chfsun.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.micrometer.core.instrument.util.StringUtils;

@Component
public class PreLogFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(PreLogFilter.class);

	/**
	 * 指定过滤器类型 可选pre、routing、post、error四种。
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 过滤器执行顺序，数值越小优先级越高。
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 是否进行过滤，返回true会执行过滤。
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 自定义的过滤器逻辑，当shouldFilter()返回true时会执行。 模拟一个登录过滤
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();

		String remoteHost = request.getRemoteHost();
		String method = request.getMethod();
		String requestURI = request.getRequestURI();

		LOGGER.info("Remote host:{},method:{},uri:{}", remoteHost, method, requestURI);
		String name = request.getParameter("name");

		if (StringUtils.isNotEmpty(name) && name.equals("chfsun")) {
			currentContext.setSendZuulResponse(true);
		} else {
			currentContext.setSendZuulResponse(false);
			try {
				HttpServletResponse res = currentContext.getResponse();
				res.setContentType("text/html;charset=UTF-8");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write("用户校验不通过！！！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
