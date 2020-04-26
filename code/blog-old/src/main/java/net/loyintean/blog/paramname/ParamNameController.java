package net.loyintean.blog.paramname;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamNameController {

    @GetMapping("paramname")
    public Param paranName(Param param, String name, int age) {
        System.out.println(param + ":" + name + ":" + age);

        return param;
    }
}
