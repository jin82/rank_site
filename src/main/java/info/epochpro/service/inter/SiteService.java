package info.epochpro.service.inter;

import javax.servlet.http.HttpServletRequest;

/**
 * 网站主要服务层接口
 * Created by jin on 2016/12/10.
 */
public interface SiteService {
    void recordUser(HttpServletRequest request);
}
