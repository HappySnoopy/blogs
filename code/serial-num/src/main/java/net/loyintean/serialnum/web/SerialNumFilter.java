package net.loyintean.serialnum.web;

import net.loyintean.serialnum.utils.SerialNumUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 在Web入口处理流水号。
 * <p>
 * 这个Filter应当放在所有业务Filter的最外层，以确保所有业务处理的日志中都有流水号。
 *
 * @author Snoopy
 * @date 2020年11月4日
 */
public class SerialNumFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 尝试从header中获取一个对方传递过来的流水号。如果没有（或者获取不到），则初始化一个。
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            SerialNumUtils.setSerialNum(httpServletRequest.getHeader(SerialNumUtils.SERIAL_NUM_KEY));
        } else {
            SerialNumUtils.initSerialNum();
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 执行完毕之后，清掉上下文
            SerialNumUtils.clearSerialNum();
        }

    }

    @Override
    public void destroy() {

    }
}
