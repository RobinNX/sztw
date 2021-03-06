package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.model.UserInfo;
import betahouse.service.form.FormManagerService;
import betahouse.service.information.AnnouncementService;
import betahouse.service.information.MessageBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by x1654 on 2017/7/3.
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private FormManagerService formManagerService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private MessageBoxService messageBoxService;

    @RequestMapping(value = {"/index","/"})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        if(this.getCurrentUser(request)!=null){
            UserInfo userInfoDTO = this.getCurrentUser(request);
            model.addAttribute("user",userInfoDTO);
            model.addAttribute("licence", this.getLicence(request));
            model.addAttribute("announcement",announcementService.listAll());
            model.addAttribute("message", messageBoxService.listAllByUserId(userInfoDTO.getId()));
            return "user/index";
        }
        model.addAttribute("announcement",announcementService.listAll());
        return "index/login";
    }
}