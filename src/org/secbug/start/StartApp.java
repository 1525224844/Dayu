package org.secbug.start;

import java.io.IOException;

import org.secbug.conf.Context;
import org.secbug.core.RecognManager;
import org.secbug.util.GetResultUtil;

public class StartApp {

	public static void main(String[] args) throws IOException {

		// ��ȡ�������
		Context.args = args;

		// ϵͳ��ʼ��
		Context.INIT();

		// ������ʶ����
		RecognManager.recognPrint();

		// ��ȡָ��ʶ����
		GetResultUtil.getResult();
	}

}
