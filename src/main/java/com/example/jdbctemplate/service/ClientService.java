package com.example.jdbctemplate.service;

import com.example.jdbctemplate.dto.request.ClientRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final JdbcTemplate jdbcTemplate;

    public ClientService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Object> getClients() {
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcCall.withProcedureName("GetClients").withoutProcedureColumnMetaDataAccess();
            Map<String, Object> response = simpleJdbcCall.execute();
            logger.debug(response.toString());
            return (List<Object>) response.get("#result-set-1"); // Obtiene el primer record set (primer select);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void saveClient(ClientRequestDto clientRequestDto) {
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcCall.withProcedureName("SaveClient")
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(
                            new SqlParameter("_name", Types.VARCHAR),
                            new SqlParameter("_last_name", Types.VARCHAR)
                    );

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("_name", clientRequestDto.getName());
            parameters.put("_last_name", clientRequestDto.getLastName());

            Map<String, Object> response = simpleJdbcCall.execute(parameters);
            logger.debug(response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateClient(Integer id, ClientRequestDto clientRequestDto) {
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcCall.withProcedureName("UpdateClient")
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(
                            new SqlParameter("_id", Types.INTEGER),
                            new SqlParameter("_name", Types.VARCHAR),
                            new SqlParameter("_last_name", Types.VARCHAR)
                    );

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("_id", id);
            parameters.put("_name", clientRequestDto.getName());
            parameters.put("_last_name", clientRequestDto.getLastName());

            Map<String, Object> response = simpleJdbcCall.execute(parameters);
            logger.debug(response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteClient(Integer id) {
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcCall.withProcedureName("DeleteClient")
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(
                            new SqlParameter("_id", Types.INTEGER)
                    );

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("_id", id);

            Map<String, Object> response = simpleJdbcCall.execute(parameters);
            logger.debug(response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
