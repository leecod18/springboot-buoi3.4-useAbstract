package com.example.demo.service;

public interface BaseCrudService<Q,I,R> {

    R create(Q request);

          R findById(I id);

    R updateById(I id, Q request);

          void  deleteById(I id);

}
