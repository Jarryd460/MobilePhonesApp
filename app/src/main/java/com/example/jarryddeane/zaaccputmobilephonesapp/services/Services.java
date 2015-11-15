package com.example.jarryddeane.zaaccputmobilephonesapp.services;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public interface Services <S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();

}
