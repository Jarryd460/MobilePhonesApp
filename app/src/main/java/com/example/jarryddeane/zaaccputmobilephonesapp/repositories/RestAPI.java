package com.example.jarryddeane.zaaccputmobilephonesapp.repositories;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public interface RestAPI<S, ID> {

    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();

}
