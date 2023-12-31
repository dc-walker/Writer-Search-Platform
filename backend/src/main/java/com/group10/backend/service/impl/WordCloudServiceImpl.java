package com.group10.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group10.backend.entity.WordCloud;
import com.group10.backend.mapper.WordCloudMapper;
import com.group10.backend.service.WordCloudService;
import org.springframework.stereotype.Service;

@Service
public class WordCloudServiceImpl extends ServiceImpl<WordCloudMapper, WordCloud> implements WordCloudService {
}
