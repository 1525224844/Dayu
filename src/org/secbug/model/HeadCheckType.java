package org.secbug.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.secbug.conf.Context;

public class HeadCheckType implements CheckType {

	private Logger logger = Logger.getLogger(HeadCheckType.class);

	@Override
	public boolean check(String url, String recogninfo, String responseInfo) {
		boolean falg = false;
		// �ؼ���Ѱ��
		try {
			recogninfo = recogninfo.replace("&lt;", "<").replace("&gt;", ">");
			if (responseInfo.indexOf(recogninfo) != -1 || responseInfo.equals(recogninfo)) {
				falg = true;
			} else { // ������ʽ
				Pattern pattern = Pattern.compile(recogninfo);
				Matcher matcher = pattern.matcher(responseInfo);
				if (matcher.find()) {
					falg = true;
				}
			}
			logger.info("��ǰjobid��" + Context.jobid + " ����״̬��" + falg + " ʶ�����ݣ�" + recogninfo + " URL��" + url);
		} catch (Exception e) {
			logger.error("��ǰjobid��" + Context.jobid + " ������Ϣ��" + e.toString());
		}
		return falg;
	}

}
