package com.ai.bdex.dataexchange.sequence;

import com.ai.paas.config.AbstractConfigurationWatcher;
import com.ai.paas.util.JSONValidator;
import com.ai.paas.utils.CipherUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sql.DataSource;
import java.io.IOException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fangyunfeng on 2017/4/16.
 */
public class SimpleSequenceFactory extends AbstractConfigurationWatcher implements SequenceFactory{
    private static final Logger log = LoggerFactory.getLogger(SimpleSequenceFactory.class);

    private static final String DRIVER_KEY = "driver";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String URL_KEY = "url";
    private static final String MINIDLE_KEY = "minIdle";
    private static final String INITSIZE_KEY = "initSize";
    private static final String MAXACTIVE_KEY = "maxActive";
    private static final String MAXIDLE_KEY = "maxIdle";
    private static final String MAXWAIT_KEY = "maxWait";
    private static final String VALIDATIONQUERY_KEY = "validationQuery";
    private static final String TESTWHILEIDLE_KEY = "testWhileIdle";
    private static final String SEQUENCETABLE_KEY = "sequenceTable";

    private static final String confPath = "/com/ai/db/sequence/conf";
    private static DataSource db;

    private String driver = null;
    private String userName = null;
    private String password = null;
    private String url = null;
    private int minIdle = 3;
    private int initSize = 5;
    private int maxActive = 20;
    private int maxIdle = 10;
    private int maxWait = 10;
    private String validationQuery = null;
    private String testWhileIdle = null;

    private String sequenceTable = null;

    public SimpleSequenceFactory(){
        super(confPath);
    }

    public DataSource getDataSource(){
        return db;
    }

    @Override
    public void process(String conf) throws IOException {
        log.info("Received configuration of {}: {}", confPath, conf);

        JsonNode jsonObj;
        try {
            jsonObj = new ObjectMapper().readTree(conf);
        } catch (IOException e) {
            log.error("Cannot parse redis configuration", e);
            return;
        }
        boolean changed = false;
        if (JSONValidator.isChanged(jsonObj, DRIVER_KEY, driver)) {
            changed = true;
            driver = jsonObj.get(DRIVER_KEY).asText();
        }
        if (JSONValidator.isChanged(jsonObj, USERNAME_KEY, userName)) {
            changed = true;
            userName = jsonObj.get(USERNAME_KEY).asText();
        }
        if (JSONValidator.isChanged(jsonObj, PASSWORD_KEY, password)) {
            changed = true;
            password = jsonObj.get(PASSWORD_KEY).asText();
        }
        if (JSONValidator.isChanged(jsonObj, URL_KEY, url)) {
            changed = true;
            url = jsonObj.get(URL_KEY).asText();
        }
        if (JSONValidator.isChanged(jsonObj, MINIDLE_KEY, minIdle)) {
            changed = true;
            minIdle = jsonObj.get(MINIDLE_KEY).asInt();
        }
        if (JSONValidator.isChanged(jsonObj, INITSIZE_KEY, initSize)) {
            changed = true;
            initSize = jsonObj.get(INITSIZE_KEY).asInt();
        }
        if (JSONValidator.isChanged(jsonObj, MAXACTIVE_KEY, maxActive)) {
            changed = true;
            maxActive = jsonObj.get(MAXACTIVE_KEY).asInt();
        }
        if (JSONValidator.isChanged(jsonObj, MAXIDLE_KEY, maxIdle)) {
            changed = true;
            maxIdle = jsonObj.get(MAXIDLE_KEY).asInt();
        }
        if (JSONValidator.isChanged(jsonObj, MAXWAIT_KEY, maxWait)) {
            changed = true;
            maxWait = jsonObj.get(MAXWAIT_KEY).asInt();
        }
        if (JSONValidator.isChanged(jsonObj, MAXWAIT_KEY, "" + maxWait)) {
            maxWait = jsonObj.get(MAXWAIT_KEY).asInt();
        }
        if (JSONValidator.isChanged(jsonObj, VALIDATIONQUERY_KEY, validationQuery)) {
            validationQuery = jsonObj.get(VALIDATIONQUERY_KEY).asText();
        }
        if (JSONValidator.isChanged(jsonObj, TESTWHILEIDLE_KEY, testWhileIdle)) {
            testWhileIdle = jsonObj.get(TESTWHILEIDLE_KEY).asText();
        }

        if (changed) {
            if (db != null) {
                //释放资源
            }
            initDb();
            log.info("Configuration changed: {}", this.hashCode());
        }

        if (JSONValidator.isChanged(jsonObj, SEQUENCETABLE_KEY, sequenceTable)) {
            sequenceTable = jsonObj.get(SEQUENCETABLE_KEY).asText();
        }

    }

    private void initDb(){
        if(log.isDebugEnabled()) {
            log.debug("init Sequence DataSource");
        }
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUsername(userName);
        String pwd = password;
        if(password.startsWith("{ENC}")){
            pwd = pwd.replace("{ENC}","");
        }else{
            pwd = CipherUtil.decrypt(password);
        }
        ds.setPassword(pwd);
        ds.setUrl(url);
        ds.setMinIdle(minIdle);
        ds.setInitialSize(initSize);
        ds.setMaxTotal(maxActive);
        ds.setMaxIdle(maxIdle);
        ds.setMaxWaitMillis(maxWait);
        ds.setValidationQuery(validationQuery.replace("&nbsp;", " "));
        ds.setTestWhileIdle(Boolean.valueOf(testWhileIdle));

        db = ds;
    }

    @Override
    public Sequence createSequence(String sequenceName) {
        SimpleSequence sequence = new SimpleSequence();
        sequence.setSequenceName(sequenceName);
        sequence.setSequenceFactory(this);
        sequence.setSequenceTable(sequenceTable);
        return sequence;
    }
}
