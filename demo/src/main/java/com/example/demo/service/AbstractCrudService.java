package com.example.demo.service;

import com.example.demo.exception.ResourceNotFound;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract  class AbstractCrudService<Q,RP extends JpaRepository<D,I>,D,I,R>  implements BaseCrudService<Q,I,R> {
//I : kieu cua ID
    //R:  Reponse
    //Q: Request

    protected final RP repository;
    protected abstract R toResponse(D domainEntity);
    protected abstract D CreateAndSave(Q request);
    protected abstract D SaveAndUpdate(I id, Q request);

    protected AbstractCrudService(RP repository) {
        this.repository = repository;
    }
    @Override
    public R create(Q request){
        D domainEntity = CreateAndSave(request);
        return toResponse(domainEntity);
    }

    @Override
    public R findById(I id) {
        D domainEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFound(" id " + id + " not found"));
        return toResponse(domainEntity);
    }

    @Override
    public void deleteById(I id) {
        D domainEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFound(" id " + id + " not found"));
        repository.delete(domainEntity);
    }

    @Override
    public R updateById(I id, Q request) {
        D domainEntity = SaveAndUpdate(id,request);
        return toResponse(domainEntity);
    }


}
