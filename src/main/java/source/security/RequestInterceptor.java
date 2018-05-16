package source.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import source.constant.Constants;
import source.service.TokenService;
import source.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        Constants.LOGGER.info(request.getRequestURI());
        if(request.getRequestURI().equals("/api")){

            String token = request.getHeader("Authorization");
            Constants.LOGGER.info(token);

            if(token == null){
                Constants.LOGGER.info("token null");
                response.sendRedirect("/error/unauthorized");
                return false;
            }

            String validationResult = tokenService.checkByToken(token);
            Constants.LOGGER.info(validationResult);

            if(validationResult == null){
                Constants.LOGGER.info("validation null" );
                response.sendRedirect("/error/all");
                return false;
            }

            return true;
        }else{
            return true;
        }
    }
}
