package org.secbug.model;

public interface CheckType {

	/**
	 * ʶ��ʽ
	 */
	public abstract boolean check(String url,String recogninfo,String responseInfo);

}
