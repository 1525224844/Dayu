package org.secbug.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.secbug.conf.Context;
import org.secbug.vo.Feature;

import net.sf.json.JSONArray;

public class ReadCheck {

	private static Logger logger = Logger.getLogger(ReadCheck.class);

	/**
	 * ������� ��ȡ��棨json�� ��ȡƥ��ָ�� (����ģʽ)
	 */
	public static List<Feature> getCheckData() {
		List<Feature> features = new ArrayList<Feature>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Context.currpath));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			JSONArray array = JSONArray.fromObject(buffer.toString());
			features = array.toList(array, Feature.class);
			if (features == null) {
				logger.info("ָ��json�ļ���ȡָ��Ϊ�գ������ָ�ơ�");
			}
			reader.close();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return features;
	}
}
