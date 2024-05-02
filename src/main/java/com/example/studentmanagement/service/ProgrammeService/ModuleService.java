package com.example.studentmanagement.service.ProgrammeService;

import com.example.studentmanagement.entity.Programme.ExamCoursework;
import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.StudentExamMapper;
import com.example.studentmanagement.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private ExamCourseworkMapper examCourseworkMapper;
    @Autowired
    private StudentExamMapper studentExamMapper;
    @Autowired
    private ProgrammeModuleMapper programmeModuleMapper;

}
