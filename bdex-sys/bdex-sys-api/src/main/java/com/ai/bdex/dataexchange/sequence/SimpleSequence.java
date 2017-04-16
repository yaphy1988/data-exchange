package com.ai.bdex.dataexchange.sequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sequence实现类
 * @author zjy
 * @date 2014年6月23日 上午9:59:05 
 * @version V1.0
 */
public class SimpleSequence extends AbstractSequence implements Sequence {

	private static Logger log = LoggerFactory.getLogger(SimpleSequence.class);

	private SimpleSequenceFactory sequenceFactory;

	@Override
	public long nextValue() {
		long nexSeqValue = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = sequenceFactory.getDataSource().getConnection();
			conn.setAutoCommit(false);
			if(getSqlSelect()  == null || getSqlSelect().length() == 0) {
				setSqlSelect("select value from " + getSequenceTable() + " where name = ? for update");
			}
			ps = conn.prepareStatement(getSqlSelect());
			ps.setString(1, getSequenceName());
			rs = ps.executeQuery();
			if(rs != null && rs.next()) {
				long currVal = rs.getLong(1);
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(getSqlUpdate()  == null || getSqlUpdate().length() == 0) {
					setSqlUpdate("update " + getSequenceTable() + " set value=? where name = ?");
				}
				ps = conn.prepareStatement(getSqlUpdate());
				nexSeqValue = currVal + getStep();
				ps.setLong(1, nexSeqValue);
				ps.setString(2, getSequenceName());
				int ret = ps.executeUpdate();
				conn.commit();
				if(ret == 1) {
					if(log.isDebugEnabled()) {
						log.debug("get sequence "+getSequenceName()+" value:" + nexSeqValue);
					}
					return nexSeqValue;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} finally {
			try {
				if(rs != null)
					rs.close();
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			try {
				if(ps != null)
					ps.close();
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return nexSeqValue;
	}

	public SimpleSequenceFactory getSequenceFactory() {
		return sequenceFactory;
	}

	public void setSequenceFactory(SimpleSequenceFactory sequenceFactory) {
		this.sequenceFactory = sequenceFactory;
	}
}
