package com.cwitter.dao;

import com.cwitter.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public interface LogonDao extends CrudRepository<User, Long> {
    User findByUserNameAndPassword(String userName, String password);
}
