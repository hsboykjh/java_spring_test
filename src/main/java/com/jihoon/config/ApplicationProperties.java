package com.jihoon.config;

import com.jihoon.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final String AuthId;
    private final String AuthPassword;
    private final String AuthRole;
    private String RowDataFilePath;

    /**
     * Read application.properties file and create ApplicationProperties class with the config info.
     *
     * @param AuthId
     * @param AuthPassword
     * @param AuthRole
     * @param RowDataFilePath
     */
    @Autowired
    public ApplicationProperties(
            @Value("${Authentication.Id}") String AuthId,
            @Value("${Authentication.Password}") String AuthPassword,
            @Value("${Authentication.Role}") String AuthRole,
            @Value("${RowDataFile}") String RowDataFilePath
    ) {
        logger.info("ApplicationProperties AuthId : " + AuthId);
        logger.info("ApplicationProperties AuthPassword : " + AuthPassword);
        logger.info("ApplicationProperties AuthRole : " + AuthRole);
        logger.info("ApplicationProperties RowDataFilePath : " + RowDataFilePath);

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

