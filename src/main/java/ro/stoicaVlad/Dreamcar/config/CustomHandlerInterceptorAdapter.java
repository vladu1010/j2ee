package ro.stoicaVlad.Dreamcar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ro.stoicaVlad.Dreamcar.service.IMenuItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomHandlerInterceptorAdapter extends HandlerInterceptorAdapter{

    @Autowired
    private IMenuItemService menuItemService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null)
            modelAndView.addObject("menu", menuItemService.findAll());
    }
}
