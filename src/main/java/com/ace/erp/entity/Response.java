/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.entity;

import com.ace.erp.entity.sys.User;
import com.ace.erp.utils.JsonUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/27/13
 * Time: 10:45 PM
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Response implements Serializable {
    private static final long serialVersionUID = 3056471023678709706l;
    private Integer numFound;
    private Integer start;
    private List<User> userList;
    private User user;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumFound() {
        return numFound;
    }

    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    @Override
    public String toString() {
        try {

            return JsonUtils.mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
