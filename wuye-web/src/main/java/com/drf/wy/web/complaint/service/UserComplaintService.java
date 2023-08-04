package com.drf.wy.web.complaint.service;


import com.drf.wy.web.complaint.po.UserComplaint;
import com.drf.wy.web.complaint.po.UserComplaintParam;

import java.util.Map;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/27 15:00
 * @Project_Name:wuyeSys-parent
 */
public interface UserComplaintService {

    Map<String,Object> list(UserComplaintParam param);

    Map<String,Object> myList(UserComplaintParam param);

    void save(UserComplaint userComplaint);

    void update(UserComplaint userComplaint);

    void delete(String id);
}
