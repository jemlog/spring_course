package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
       model.addAttribute("data","안녕하세요"); // model에 데이터를 실어서 템플릿으로 넘길 수 있다.
       return "hello";  // hello.html
    }
}
