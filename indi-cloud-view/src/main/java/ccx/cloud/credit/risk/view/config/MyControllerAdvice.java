package ccx.cloud.credit.risk.view.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {

    @Value("${script-version:0.00}")
    private String scriptVersion;

    @ModelAttribute
    public void addVersion(Model model) {

        model.addAttribute("ver", scriptVersion);
    }
}
