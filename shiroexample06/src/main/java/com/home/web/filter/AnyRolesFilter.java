package com.home.web.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * AccessControlFilter提供了访问控制的基础功能；比如是否允许访问/当访问拒绝时如何处理等
 *
 * @author guxc
 * @date 2020/2/17
 */
public class AnyRolesFilter extends AccessControlFilter {

    private String unauthorizedUrl = "/unauthorized.jsp";
    private String loginUrl = "/login.jsp";

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String[] roles = (String[]) mappedValue;
        if (null == roles) {
            return true; // 没有设置角色则默认成功
        }
        for (String role : roles) {
            if (getSubject(request, response).hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (null == subject.getPrincipal()) {
            // 没有账号
            saveRequest(request);
            // 重新转到登陆页
            WebUtils.issueRedirect(request, response, loginUrl);
        } else {
            if (StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }
}
