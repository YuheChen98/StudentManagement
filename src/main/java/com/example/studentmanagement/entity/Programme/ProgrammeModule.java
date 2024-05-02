package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableId;

public class ProgrammeModule {
    @TableId
    private String programmeId;
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
