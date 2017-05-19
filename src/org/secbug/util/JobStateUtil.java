package org.secbug.util;

import org.secbug.conf.Context;
import org.secbug.dao.JobDAO;
import org.secbug.dao.impl.JobDAOImpl;
import org.secbug.vo.Job;

public class JobStateUtil {
	
	private static JobDAO dao = new JobDAOImpl();

	// ���������  ����Job
	public static void EndJobState() {
		int status = 0;

		Job job1 = new Job();
		job1.setId(Context.jobid);
		status = 4;
		job1.setStatus(status);
		dao.updateJobById(job1);

		Job job2 = new Job();
		job2.setId(Context.jobid);
		job2.setTwoperent("100.00%");
		dao.updateJobWithTwo(job2);
	}
	
	// ѡ��ģʽ״̬ ����Job
	public static void ChangeJobState(int state){
		Job job = new Job();
		job.setId(Context.jobid);
		job.setStatus(state);
		dao.updateJobById(job);
	}
	
	// ����������Ч ����״̬
	public synchronized static void InvalidJobState(){
		Job jobCheck = new Job();
		jobCheck.setId(Context.jobid);
		jobCheck.setOneperent("100.00%");
		dao.updateJobWithOne(jobCheck);
		jobCheck.setTwoperent("100.00%");
		dao.updateJobWithTwo(jobCheck);
		jobCheck.setStatus(4);
		dao.updateJobById(jobCheck);
	}

}
