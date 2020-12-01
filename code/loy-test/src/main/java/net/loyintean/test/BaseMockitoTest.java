package net.loyintean.test;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Mockito来测试的基类
 *
 * @author Snoopy
 * @date 2020年11月19日
 */
public abstract class BaseMockitoTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public final void before() {
        MockitoAnnotations.initMocks(this);
    }
}
