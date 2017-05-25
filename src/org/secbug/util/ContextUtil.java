package org.secbug.util;

import org.secbug.conf.Context;

public class ContextUtil {

	/**
	 * ������˳�ʱ����
	 */
	public static void doShutDownWork() {
		Runtime run = Runtime.getRuntime();// ��ǰ Java Ӧ�ó�����ص�����ʱ����
		run.addShutdownHook(new Thread() { // ע���µ���������رչ���
			@Override
			public void run() {

				System.out.println("ָ��ʶ��������ʱ�䣺" + ContextUtil.getRunTime());

			}
		});
	}

	/**
	 * ��ȡ��������ʱ��
	 * 
	 * @return
	 */
	public static String getRunTime() {
		long startTime = Context.STARTTIME; // ��ȡ��ʼʱ��
		long endTime = System.currentTimeMillis(); // ��ȡ����ʱ��
		double time = ((double) (endTime - startTime)) / 1000;
		return time + "��";
	}

	public static void exitPrintln(Object str) {
		System.out.println(str);
		System.exit(-1);
	}

	/**
	 * ����ȫ�ֵ�����������Ϣ������url�������
	 */
	public static void setJobOption() {
		JobCommand job = new JobCommand();
		job.init();
	}

}
