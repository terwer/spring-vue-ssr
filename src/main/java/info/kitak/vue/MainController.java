package info.kitak.vue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {
    private VueRenderer vueRenderer;

    public MainController() {
        this.vueRenderer = new VueRenderer();
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        String app = vueRenderer.renderContent();
        model.put("content", app);
        model.put("rnd", System.currentTimeMillis());
        return "index";
    }
}
