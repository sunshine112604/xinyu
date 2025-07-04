package com.lqp.java.xinyu.agent.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqp.java.xinyu.agent.entity.Appointment;
import com.lqp.java.xinyu.agent.mapper.AppointmentMapper;
import com.lqp.java.xinyu.agent.service.AppointmentService;
import org.springframework.stereotype.Service;


@Service
public class AppointmentServiceImpl extends
        ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {

    @Override
    public  Appointment getOne(Appointment appointment){

        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUsername,appointment.getUsername());
        queryWrapper.eq(Appointment::getIdCard,appointment.getIdCard());
        queryWrapper.eq(Appointment::getDepartment,appointment.getDepartment());
        queryWrapper.eq(Appointment::getDate,appointment.getDate());
        queryWrapper.eq(Appointment::getTime,appointment.getTime());

        Appointment appointmentDB = baseMapper.selectOne(queryWrapper);
        return appointmentDB;
    }
}
