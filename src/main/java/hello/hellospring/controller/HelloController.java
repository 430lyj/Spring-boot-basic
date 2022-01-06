package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //Spring의 Controller에서는 이걸 적어줘야 한대
public class HelloController {

    @GetMapping("hello") //Get, post, 등의 method 중에 get인 거임
    public String hello(Model model){
        model.addAttribute("data", "Yeon-ju!!"); // template 문법에서 추가한 data(키)에 hello!!(값)이 들어가는 것
        return "hello";  //resources\templates 아래의 hello를 찾음
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody //http의 body 부분에 이 내용을 직접 넣어주겠다 하는 것.
    public  String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name1") String name1){
        Hello hello = new Hello();
        hello.setZikata(name1);
        return hello;
    }

    static class Hello{
        private String zikata;

        public String getZikata() {
            return zikata;
        }

        public void setZikata(String zikata) {
            this.zikata = zikata;
        }
    }
}
