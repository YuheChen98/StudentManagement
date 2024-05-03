package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Represents the association between a programme and its academic modules within the student management system.
 * This entity links programmes with their respective modules.
 */
public class ProgrammeModule {

    @TableId
    private String programmeId;  // Unique identifier for the programme
    private String moduleId;     // Unique identifier for the module

    /**
     * Default constructor for ProgrammeModule.
     */
    public ProgrammeModule() {}

    /**
     * Gets the programme ID associated with this module.
     * @return the unique identifier for the programme
     */
    public String getProgrammeId() {
        return programmeId;
    }

    /**
     * Sets the programme ID for this module association.
     * @param programmeId the unique identifier for the programme to set
     */
    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * Gets the module ID associated with this programme.
     * @return the unique identifier for the module
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Sets the module ID for this programme association.
     * @param moduleId the unique identifier for the module to set
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
