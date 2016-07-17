package com.cwitter.dao;

import com.cwitter.model.Tweet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TweetDao extends CrudRepository<Tweet, Long> {

    List<Tweet> findAllByOrderByCreatedOnDesc();
}
