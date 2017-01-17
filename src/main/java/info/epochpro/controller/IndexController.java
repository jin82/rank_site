package info.epochpro.controller;

import info.epochpro.service.inter.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 主页控制器
 * Created by jin on 2016/12/10.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private SiteService siteService;

    @RequestMapping
    public String msg(HttpServletRequest request){
        siteService.recordUser(request);
        return "index";
    }

    @RequestMapping(value = "/msg/{msg}",method = RequestMethod.GET)
    public String msg(Model model, @PathVariable String msg) {
        model.addAttribute("msg", msg);
        return "msg";
    }
}
