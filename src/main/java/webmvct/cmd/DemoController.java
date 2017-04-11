package webmvct.cmd;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class DemoController {

    
    @ResponseBody
    @RequestMapping(value = "/index", produces = "application/json; charset=utf-8")
    public String index(){
        return "{\"name\":\"中国\"}";
    }
    
    @ResponseBody
    @RequestMapping("/index2")
    public String index2(){
        return "{\"name\":\"中国\"}";
    }
    
    
    @Value("${mq.queue.ip}")
    private String value;
    
    
    @ResponseBody
    @RequestMapping("/value")
    public String value(){
        return value;
    }
    
   
}