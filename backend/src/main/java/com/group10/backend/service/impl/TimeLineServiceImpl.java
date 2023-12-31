package com.group10.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group10.backend.entity.TimeLine;
import com.group10.backend.mapper.TimeLineMapper;
import com.group10.backend.service.TimeLineService;
import org.springframework.stereotype.Service;

@Service
public class TimeLineServiceImpl extends ServiceImpl<TimeLineMapper, TimeLine> implements TimeLineService {
}
