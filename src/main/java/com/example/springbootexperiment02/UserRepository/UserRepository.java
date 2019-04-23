package com.example.springbootexperiment02.UserRepository;

import com.example.springbootexperiment02.entity.Address;
import com.example.springbootexperiment02.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    /*添加用户，并返回包括数据库时间戳的用户对象*/
    public User addUser(User user){
        em.persist(user);
        em.refresh(user);
        return user;
    }
    /*添加地址与地址对应用户*/
    public Address addAddress(Address address,int uid){
        User user =em.find(User.class,uid);
        address.setUser(user);
        em.persist(address);
        return address;
    }
    /*更新指定ID的用户名*/
    public User updateUser(int uid,String newName){
        User user =new User();
        user.setId(uid);
        User newUser = em.merge(user);
        em.refresh(newUser);
        newUser.setName(newName);
        return newUser;
    }
    /*使用merge和find两种方法实现更新指定地址为指定用户*/
    public Address updateAddress(int aid,int uid){
       /* Address address = new Address();
        address.setId(aid);
        Address newAddress = em.merge(address);
        em.refresh(newAddress);
        User user = em.find(User.class, uid);
        newAddress.setUser(user);
        return newAddress;*/
        /*方法2：find*/
        Address address1 = em.find(Address.class,aid);
        User user = em.find(User.class, uid);
        address1.setUser(user);
        return address1;

    }
    /*返回指定用户全部地址，没有返回空集合*/
    public List<Address> listAddresses(int uid){
        User user = em.find(User.class,uid);
        List<Address> list = user.getAddresses();
        List.of(list);
        return list;
    }
    public void removeAddress(int aid){
        Address address = em.find(Address.class,aid);
        em.remove(address);
        return;
    }
    public void removeUser(int uid){
        User user = em.find(User.class,uid);
        em.remove(user);
        return;
    }
}
