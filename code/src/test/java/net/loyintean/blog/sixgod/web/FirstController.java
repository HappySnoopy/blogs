/**
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author linjun
 */
@Controller
public class FirstController {

    @RequestMapping("/hello")
    public ResponseEntity<String> greeting() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
