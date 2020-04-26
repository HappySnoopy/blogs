package net.loyintean.blog.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

public class ApplyConfimBeanTest {

    @Test
    public void test(){
        ApplyConfimBean bean = new ApplyConfimBean();
        bean.setUserId("userId");
        bean.setPhone("phone");

        Gson gson;
        String json;

        gson=new Gson();
        json = gson.toJson(bean);
        System.out.println(json);

        gson = new GsonBuilder().create();
        json = gson.toJson(bean);
        System.out.println(json);

        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        json = gson.toJson(bean);
        System.out.println(json);

    }

}