package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableId;

public class ProgrammeModule {
    @TableId(value = "programme_id")
    private String programmeId;
 //   @TableId(value = "module_id")
    private String moduleId;

    public  ProgrammeModule(){ }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}