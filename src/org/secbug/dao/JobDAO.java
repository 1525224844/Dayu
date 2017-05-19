package org.secbug.dao;

import org.secbug.vo.Job;

public interface JobDAO {

	/**
	 * �޸�Job
	 * 
	 * @return
	 */
	public int updateJobById(Job job);

	/**
	 * ��� Job
	 * 
	 * @return
	 */
	public int insertJob(Job job);
	
	/**
	 * ����jobid��ѯ�Ѿ�������
	 * 
	 * @return 
	 */
	public Job findJobByIdWithStatus(int jobid);
	
	/**
	 * �޸Ĵ���ģʽ���� 
	 * 
	 * @return
	 */
	public int updateJobWithOne(Job job);
	
	/**
	 * �޸ľ�׼ģʽ����
	 * 
	 * @return
	 */
	public int updateJobWithTwo(Job job);
	
	/**
	 * ����id��ѯ 
	 */
	public Job findJobById(int jobid);

}
