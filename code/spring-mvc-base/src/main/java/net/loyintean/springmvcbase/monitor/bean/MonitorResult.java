package net.loyintean.springmvcbase.monitor.bean;

import net.loyintean.springmvcbase.common.bean.BaseResult;

import java.util.Collection;
import java.util.LinkedList;

public class MonitorResult extends BaseResult<Collection<Object>> {

    public MonitorResult() {
        super(new LinkedList<>());
    }

    public void add(Object object) {
        data.add(object);
    }

    public void addAll(Collection<Object> other) {
        data.addAll(other);
    }
}