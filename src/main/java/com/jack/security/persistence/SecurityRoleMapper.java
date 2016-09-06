package com.jack.security.persistence;

import com.jack.security.pojo.SecurityRole;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by wajiangk on 9/5/2016.
 */
@Repository
public interface SecurityRoleMapper extends BaseMapper<SecurityRole,Serializable> {



}
