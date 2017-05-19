package org.secbug.dao;

import java.util.List;

import org.secbug.vo.Result;

public interface ResultDAO {

	/**
	 * ��ѯ���
	 * 
	 * @return list
	 */
	public List<Result> findAll();
	
	/**
	 * ��ӽ�� 
	 * 
	 * @return
	 */
	public int insertResult(Result result);
	
	/**
	 * ����jobid��ѯ
	 * 
	 * @return 
	 */
	public List<Result> findResultById(int jobid);
	
	/**
	 * ����resultid��ѯ
	 * 
	 * @return
	 */
	public Result getResultById(int resultid);

}
