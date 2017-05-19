package org.secbug.inter;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.secbug.conf.Context;
import org.secbug.crack.PrintCrack;
import org.secbug.model.CheckType;
import org.secbug.model.ContextCheckType;
import org.secbug.model.HeadCheckType;
import org.secbug.model.MD5CheckType;
import org.secbug.model.UrlCheckType;
import org.secbug.util.ResultSaveUtil;

public class ProCrack implements Callable<String> {

	private Logger logger = Logger.getLogger(ProCrack.class);

	private String url;
	private String urlPath;
	private int recognid;
	private String recogncontext;
	private int jobid;
	private int fingerPrintId;

	public ProCrack(String url, String urlPath, int recognid, String recogncontext, int jobid, int fingerPrintId) {
		this.url = url;
		this.urlPath = urlPath;
		this.recognid = recognid;
		this.recogncontext = recogncontext;
		this.jobid = jobid;
		this.fingerPrintId = fingerPrintId;
	}

	public void run() {
		if (Context.model == 1) {
			if (Context.fastProbeList.size() != 0 && Context.fastProbeList != null) {
				for (String currUrl : Context.fastProbeList) {
					if (url.equals(currUrl)) {
						return;
					}
				}
			}
			String responseInfo = PrintCrack.getResponseInfo(recognid, urlPath);
			if (responseInfo.equals("")) {
				// ��ֵ
			} else {
				boolean falg = ProCrack.getRecognType(urlPath, recognid, recogncontext, responseInfo);
				if (falg) { // �޸ĳɱ�����
					if (Context.fastProbeList.size() != 0 && Context.fastProbeList != null) {
						for (String currUrl : Context.fastProbeList) {
							if (url.equals(currUrl)) {
								return;
							}
						}
					}
					Context.fastProbeList.add(url);
					int currid = ResultSaveUtil.SaveResult(jobid, fingerPrintId, urlPath);
					if (currid == -1) {
						logger.info("��ǰjobid��" + Context.jobid + " ��Ϣ��ʾ��������ʧ�ܣ�");
					} else {
						logger.info("��ǰjobid��" + Context.jobid + " ��Ϣ��ʾ���������ɹ���");
					}
					Context.resultIds.add(currid);
					Context.resultHashMap.put(currid, recogncontext);
				}
			}
		} else if (Context.model == 2) {
			String responseInfo = PrintCrack.getResponseInfo(recognid, urlPath);
			if (responseInfo.equals("")) {
				// ��ֵ
			} else {
				boolean falg = ProCrack.getRecognType(urlPath, recognid, recogncontext, responseInfo);
				if (falg) { // �޸ĳɱ�����
					int currid = ResultSaveUtil.SaveResult(jobid, fingerPrintId, urlPath);
					if (currid == -1) {
						logger.info("��ǰjobid��" + Context.jobid + " ��Ϣ��ʾ��������ʧ�ܣ�");
					} else {
						logger.info("��ǰjobid��" + Context.jobid + " ��Ϣ��ʾ���������ɹ���");
					}
					Context.resultIds.add(currid);
				}
			}
		} else if (Context.model == 3) {
			String responseInfo = PrintCrack.getResponseInfo(recognid, urlPath);
			if (responseInfo.equals("")) {
				// ��ֵ
			} else {
				boolean falg = ProCrack.getRecognType(urlPath, recognid, recogncontext, responseInfo);
				if (falg) { // �޸ĳɱ�����
					Context.i++;
					int currid = ResultSaveUtil.SaveResult(jobid, fingerPrintId, urlPath);
					if (currid == -1) {
						logger.info("��ǰjobid��" + Context.jobid + " ��Ϣ��ʾ��������ʧ�ܣ�");
					} else {
						logger.info("��ǰjobid��" + Context.jobid + " ��Ϣ��ʾ���������ɹ���");
					}
					Context.resultIds.add(currid);
				}
			}
		}

	}

	@Override
	public String call() throws Exception {
		this.run();
		Context.updatePercent(); // ����������
		return "";
	}

	// ���ݲ�ͬʶ�������ж�
	private static boolean getRecognType(String urlPath, int recognid, String recogncontext, String responseInfo) {
		boolean falg = false;
		CheckType checkType = null;
		// �����Ժ����Ӵ���ʽ
		if (recognid == 1) { // md5ʶ��
			checkType = new MD5CheckType();
			falg = checkType.check(urlPath, recogncontext, responseInfo);
		} else if (recognid == 2) { // response contextʶ��
			checkType = new ContextCheckType();
			falg = checkType.check(urlPath, recogncontext, responseInfo);
		} else if (recognid == 3) { // response headʶ��
			checkType = new HeadCheckType();
			falg = checkType.check(urlPath, recogncontext, responseInfo);
		} else if (recognid == 4) { // url ʶ��
			checkType = new UrlCheckType();
			falg = checkType.check(urlPath, recogncontext, responseInfo);
		}
		return falg;
	}

}
