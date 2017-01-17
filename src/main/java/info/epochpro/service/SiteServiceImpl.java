package info.epochpro.service;

import info.epochpro.common.util.IPUtils;
import info.epochpro.service.inter.SiteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 网站主要服务层实现
 * Created by jin on 2016/12/10.
 */
@Service
public class SiteServiceImpl implements SiteService{

    private final Log log = LogFactory.getLog(SiteServiceImpl.class);

    @Override
    public void recordUser(HttpServletRequest request) {
        String ip = IPUtils.getClientIp(request);
        log.info("This client's ip is " + ip);
    }

}
