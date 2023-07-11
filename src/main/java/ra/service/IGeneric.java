package ra.service;

import ra.model.user.Roles;

import java.util.List;

public interface IGeneric <T, E>{
    List<T> findAll();
    void save(T t);
    void deleteById(E id);
    T findById(E id);
}
