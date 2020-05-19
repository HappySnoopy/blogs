package net.loyintean.springmvcbase.monitor.bean;

import lombok.experimental.Accessors;
import net.loyintean.springmvcbase.common.bean.BaseResult;

import java.util.Collection;
import java.util.LinkedList;

@Accessors
public class MonitorResult extends BaseResult {

    private Collection<Object> data = new LinkedList<>();

    public void add(Object object) {
        data.add(object);
    }
}