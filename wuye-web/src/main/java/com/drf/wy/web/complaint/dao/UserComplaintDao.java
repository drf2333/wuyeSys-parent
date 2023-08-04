package com.drf.wy.web.complaint.dao;


import com.drf.wy.web.complaint.po.UserComplaint;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/27 14:49
 * @Project_Name:wuyeSys-parent
 */
public interface UserComplaintDao extends MongoRepository<UserComplaint,String> {
}
