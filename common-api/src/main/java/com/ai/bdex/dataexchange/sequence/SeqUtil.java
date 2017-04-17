package com.ai.bdex.dataexchange.sequence;

import com.ai.paas.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangyunfeng on 2017/4/16.
 */
public class SeqUtil {

    private static SequenceFactory sequenceFactory = Utils.getInstance(SequenceFactory.class);
    private static Map<String,Sequence> seqMap = new HashMap<String,Sequence>();
    private static Object seqMapLock = new Object();

    public static long getNextValueLong(String sequenceName){
        sequenceName = sequenceName.toUpperCase();
        long nextValue = -1;
        if(seqMap.containsKey(sequenceName)){
            return seqMap.get(sequenceName).nextValue();
        }else{
            synchronized (seqMapLock){
                if(seqMap.containsKey(sequenceName)){
                    return seqMap.get(sequenceName).nextValue();
                }else{
                    Sequence sequence = sequenceFactory.createSequence(sequenceName);
                    seqMap.put(sequenceName,sequence);
                    return sequence.nextValue();
                }
            }
        }
    }

    public static long getLong(String sequenceName){
        return getNextValueLong(sequenceName);
    }

    public static int getInt(String sequenceName){
        return (int)getNextValueLong(sequenceName);
    }

    public static String getString(String sequenceName){
        return String.valueOf(getNextValueLong(sequenceName));
    }

    public static String getString(String sequenceName,int length){
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(getNextValueLong(sequenceName)));
        while(sb.length()<length){
            sb.insert(0,"0");
        }
        return sb.toString();
    }

}
