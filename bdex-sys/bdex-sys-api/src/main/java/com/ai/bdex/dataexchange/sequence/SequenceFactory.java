package com.ai.bdex.dataexchange.sequence;

/**
 * Created by fangyunfeng on 2017/4/16.
 */
public interface SequenceFactory {
    Sequence createSequence(String sequenceName);
}
