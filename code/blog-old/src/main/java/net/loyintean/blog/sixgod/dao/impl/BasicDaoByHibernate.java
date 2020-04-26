/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dao.impl;

import net.loyintean.blog.sixgod.dto.IdDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

/**
 * @author winters1224@163.com
 * @param <I>
 */
public class BasicDaoByHibernate<I extends IdDto<Serializable>>
        extends BasicDaoAdapter<I> {

    /**
     * hibernate的session工厂
     */
    private SessionFactory sessionFactory;

    /**
     * 根据id进行update
     *
     * @see net.loyintean.blog.sixgod.dao.BasicUpdateDao#update(java.lang.Object)
     */
    @Override
    public Integer update(I param) {
         this.getCurrentSession().update(param);
         // hibernate默认的update是按id更新的，这里默认为1
         return 1;
    }

    /**
     * 根据id，直接查询一个param数据
     *
     * @see net.loyintean.blog.sixgod.dao.BasicSelectDao#select(java.lang.Object)
     */
    @Override
    public I select(I param) {
        return (I) this.getCurrentSession().get(param.getClass(),
            param.getId());
    }

    /**
     * 如果入参中有id，则merge。否则，直接save。
     *
     * @see net.loyintean.blog.sixgod.dao.BasicInsertDao#insert(java.lang.Object)
     */
    @Override
    public Integer insert(I param) {
        if (param.getId() == null) {
            this.getCurrentSession().save(param);
        } else {
            this.getCurrentSession().merge(param);
        }
        return 1;
    }

    /**
     * 根据id删除param。
     *
     * @see net.loyintean.blog.sixgod.dao.BasicDeleteDao#delete(java.lang.Object)
     */
    @Override
    public Integer delete(I param) {
        this.getCurrentSession().delete(param);
        return 1;
    }

    /**
     * 获取当前session。
     *
     * @return
     */
    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
