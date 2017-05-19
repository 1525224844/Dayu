package org.secbug.model;

import org.apache.log4j.Logger;
import org.secbug.conf.Context;

public class UrlCheckType implements CheckType {

	private Logger logger = Logger.getLogger(UrlCheckType.class);

	@Override
	public boolean check(String url, String recogninfo, String responseInfo) {
		boolean falg = false;
		if (responseInfo.equals("true")) {
			falg = true;
			return falg;
		}
		logger.info("��ǰjobid��" + Context.jobid + " ����״̬��" + falg + " ʶ�����ݣ�" + recogninfo + " URL��" + url);
		return falg;
	}

}
