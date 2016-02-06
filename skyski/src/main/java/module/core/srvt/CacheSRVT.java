package module.core.srvt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="Cache", urlPatterns={"/content/registration.xhtml"})
public class CacheSRVT implements Filter
{
    @Override public void doFilter(
		ServletRequest _request, ServletResponse _response, FilterChain _filterChain
	) throws IOException, ServletException
	{
    		this.httpServletResponse = (HttpServletResponse)_response;
		this.httpServletResponse.setHeader("cache-control", "no-cache, no-store, must-revalidate");
		this.httpServletResponse.setHeader("pragma", "no-cache");
		this.httpServletResponse.setDateHeader("expires", 0);
		_filterChain.doFilter(_request, _response);
	}
    
    @Override public void init(FilterConfig _filtConfig) throws ServletException {}
	@Override public void destroy() {}

	private HttpServletResponse httpServletResponse;
}