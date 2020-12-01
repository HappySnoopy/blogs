package net.loyintean.test;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 使用mockMvc来测试Controller的基类
 *
 * @author Snoopy
 * @date 2020年11月19日
 */
public abstract class BaseMockMvcTest extends BaseMockitoTest {

    /**
     * mock web服务
     */
    protected MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllers())
                .build();
    }

    /**
     * 指定待测试的Controller类
     *
     * @return the object [ ]
     */
    protected abstract Object[] controllers();
}
