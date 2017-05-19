package org.secbug.dao;

import java.util.List;

import org.secbug.vo.Fingerprint;

public interface FingerPrintDAO {

	/**
	 * ��ѯ����ָ��
	 * 
	 * @return
	 */
	public List<Fingerprint> findAll(String urlPath);

	/**
	 * ��ѯĬ��ָ��
	 * 
	 * @return
	 */
	public List<Fingerprint> findPrintByDefault(String def);

	/**
	 * ����id��ѯ
	 * 
	 * @return
	 */
	public Fingerprint findPrintById(int id);

	/**
	 * ���ָ��
	 * 
	 * @return
	 */
	public int insertPrint(Fingerprint fingerprint);

	/**
	 * ����url��ʶ�����ݲ�ѯ
	 * 
	 * @return
	 */
	public Fingerprint findPrintByUrlAndContext(String url, String context);

	/**
	 * ����userid��ѯָ�Ƶ�״̬
	 * 
	 * @return
	 */
	public List<Fingerprint> findPrintByUserid(int userid);

}
