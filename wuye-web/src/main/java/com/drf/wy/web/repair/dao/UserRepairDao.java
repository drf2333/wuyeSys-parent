package com.drf.wy.web.repair.dao;


import com.drf.wy.web.repair.po.UserRepair;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther：杜瑞丰
 * @Date：2023/8/4 9:46
 * @Project_Name:wuyeSys-parent
 */
public interface UserRepairDao extends MongoRepository<UserRepair,String> {
}
