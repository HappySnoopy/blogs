/**
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.web;

import net.loyintean.blog.sixgod.web.springmvc.BasicDbController4SpringMvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Snoopy
 */
@Controller
@RequestMapping("/test1")
public class TestController
        extends BasicDbController4SpringMvc<String> {
}
