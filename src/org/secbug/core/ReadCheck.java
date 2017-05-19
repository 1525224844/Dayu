package org.secbug.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.secbug.conf.Context;
import org.secbug.dao.FingerPrintDAO;
import org.secbug.dao.impl.FingerPrintDAOImpl;
import org.secbug.vo.Fingerprint;

public class ReadCheck {
	
	private static Logger logger = Logger.getLogger(ReadCheck.class);
	
	private static String urlpath = "";

	/**
	 * ���׼�� ��ȡ�ļ���check.txt�� ��ȡƥ��ָ��
	 * (Ŀǰ��ʱ����Ҫ)
	 */
	public static List<Fingerprint> getCheckStr() {
		FingerPrintDAO dao = new FingerPrintDAOImpl();
		List<String> strList = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		List<Fingerprint> fingerprints = null;
		File file = new File(Context.currpath);
		if(!file.isFile()){
			return null;
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				strList.add(line);
			}
			int i = 0;
			for (String str : strList) {
				i++;
				if (i < strList.size()) {
					buffer.append("'" + str + "'" + ",");
				} else {
					buffer.append("'" + str + "'");
				}
			}
			urlpath = buffer.toString();
			fingerprints = dao.findPrintByDefault(urlpath);
			if (fingerprints == null) {
				logger.info("Ĭ��ָ���ļ���ȡָ��Ϊ�գ������ָ�ơ�");
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return fingerprints;
	}

	/**
	 * ������� ��ȡ��棨database�� ��ȡƥ��ָ��
	 * (����ģʽ)
	 */
	public static List<Fingerprint> getCheckData() {
		FingerPrintDAO dao = new FingerPrintDAOImpl();
		List<Fingerprint> fingerprints = dao.findAll(urlpath);
		if (fingerprints == null) {
			logger.info("ָ�����ݿ��ȡָ��Ϊ�գ������ָ�ơ�");
		}
		return fingerprints;
	}
}
