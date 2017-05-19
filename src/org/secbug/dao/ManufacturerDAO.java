package org.secbug.dao;

import java.util.List;

import org.secbug.vo.Manufacturer;

public interface ManufacturerDAO {

	/**
	 * ����id��ѯ
	 * 
	 * @return
	 */
	public Manufacturer findManufacturerById(int manuid);

	/**
	 * ��ѯ���г���
	 * 
	 * @return
	 */
	public List<Manufacturer> findAll();
	

	/**
	 * ����name��ѯ
	 */
	public Manufacturer findManufacturerByName(String name);
	
	/**
	 * ����url��ѯ 
	 * 
	 */
	public Manufacturer findManufacturerByUrl(String manuUrl);
	
	/**
	 * ��ӳ���
	 * 
	 */
	public int insertManufacturer(Manufacturer manufacturer);

}
