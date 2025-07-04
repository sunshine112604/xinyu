package com.lqp.java.xinyu.agent.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lqp.java.xinyu.agent.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
