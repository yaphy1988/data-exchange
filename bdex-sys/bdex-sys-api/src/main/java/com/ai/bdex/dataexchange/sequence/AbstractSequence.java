package com.ai.bdex.dataexchange.sequence;

/**
 * Created by fangyunfeng on 2017/4/16.
 */
public abstract class AbstractSequence implements Sequence {
    private String sequenceName;
    private int step = 1;
    private String sqlSelect;
    private String sqlUpdate;
    private String sequenceTable = "t_sequence";

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getSqlSelect() {
        return sqlSelect;
    }

    public void setSqlSelect(String sqlSelect) {
        this.sqlSelect = sqlSelect;
    }

    public String getSqlUpdate() {
        return sqlUpdate;
    }

    public void setSqlUpdate(String sqlUpdate) {
        this.sqlUpdate = sqlUpdate;
    }

    public String getSequenceTable() {
        return sequenceTable;
    }

    public void setSequenceTable(String sequenceTable) {
        this.sequenceTable = sequenceTable;
    }
}
