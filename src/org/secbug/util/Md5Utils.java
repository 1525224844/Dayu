package org.secbug.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public class Md5Utils {

	/** 16���Ƶ��ַ����� */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 
	 * 
	 * @param source
	 *            ��Ҫ���ܵ�ԭ�ַ���
	 * @param encoding
	 *            ָ����������
	 * @param uppercase
	 *            �Ƿ�תΪ��д�ַ���
	 * @return
	 */
	public static String MD5Encode(String source, String encoding, boolean uppercase) {
		String result = null;
		try {
			result = source;
			// ���MD5ժҪ����
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽ��������ժҪ��Ϣ
			messageDigest.update(result.getBytes(encoding));
			// messageDigest.digest()���16λ����
			result = byteArrayToHexString(messageDigest.digest());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uppercase ? result.toUpperCase() : result;
	}

	/**
	 * ת���ֽ�����Ϊ16�����ַ���
	 * 
	 * @param bytes
	 *            �ֽ�����
	 * @return
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte tem : bytes) {
			stringBuilder.append(byteToHexString(tem));
		}
		return stringBuilder.toString();
	}

	/**
	 * ת��byte��16����
	 * 
	 * @param b
	 *            Ҫת����byte
	 * @return 16���ƶ�Ӧ���ַ�
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * �ļ�����md5ֵ
	 * 
	 * @return value
	 */
	public static String getMd5ByFile(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

}
