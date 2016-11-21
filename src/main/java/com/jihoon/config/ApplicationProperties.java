package com.jihoon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    private final String AuthId;
    private final String AuthPassword;
    private final String AuthRole;
    private String RowDataFilePath;

    @Autowired
    public ApplicationProperties(
            @Value("${Authentication.Id}") String AuthId,
            @Value("${Authentication.Password}") String AuthPassword,
            @Value("${Authentication.Role}") String AuthRole,
            @Value("${RowDataFile}") String RowDataFilePath
    ) {
        System.out.println("ApplicationProperties constructor");
        System.out.println("ApplicationProperties : " + AuthId);
        System.out.println("ApplicationProperties : " + AuthPassword);
        System.out.println("ApplicationProperties : " + AuthRole);
        System.out.println("ApplicationProperties : " + RowDataFilePath);

        this.AuthId = AuthId;
        this.AuthPassword = AuthPassword;
        this.AuthRole = AuthRole;
        this.RowDataFilePath = RowDataFilePath;
    }

    public String getAuthId() {
        return AuthId;
    }

    public String getAuthPassword() {
        return AuthPassword;
    }

    public String getAuthRole() {
        return AuthRole;
    }

    public String getRowDataFilePath() {
        return RowDataFilePath;
    }

    public void setRowDataFilePath(String rowDataFile) {
        RowDataFilePath = rowDataFile;
    }
}

