package dsw.concessionaria.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMsg = "";
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
        
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMsg = "Página não encontrada.";
                model.addAttribute("errorCode", "404");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMsg = "Ocorreu um erro interno no servidor.";
                 model.addAttribute("errorCode", "500");
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "redirect:/acesso-negado";
            } else {
                 errorMsg = "Ocorreu um erro inesperado.";
                 model.addAttribute("errorCode", statusCode);
            }
        }
        
        model.addAttribute("errorMessage", errorMsg);
        return "error";
    }
}