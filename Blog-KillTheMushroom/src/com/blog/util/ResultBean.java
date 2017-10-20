package com.blog.util;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Ĭ�ϳɹ���code-0,msg-�����ɹ�
 * @author Administrator
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)  //ΪNULLʱ���������л�
public class ResultBean {

	private Integer code;
	private String msg;
	private Object object;
	
	
	
	/**
	 * �ɹ���
	 * 	-��Я������
	 */
	public ResultBean() {
		this.code=0;
		this.msg="�����ɹ���";
	}
	
	/**
	 * ͨ�ú���
	 * @param code 101-����δ��¼�����ȵ�¼
	 * @param code 0-�����ɹ�
	 * @param code 10011-¼��ʧ�ܣ��������룡
	 * @param code 10012-����ʧ�ܣ��������룡
	 * @param code 10021-���������쳣
	 * @param code 10031-ɾ��ʧ�ܣ���ˢ����������ԣ�
	 * @param code default-����ϵ����Ա��
	 */
	public ResultBean(Integer code) {
		this.code=code;
		switch (code) {
		case 101:
			this.msg="����δ��¼�����ȵ�¼";
			break;
		case 0:
			this.msg="�����ɹ���";
			break;
		case 10011:
			this.msg="¼��ʧ�ܣ��������룡";
			break;
		case 10012:
			this.msg="����ʧ�ܣ��������룡";
			break;
		case 10013:
			this.msg="�û������������";
			break;
		case 10021:
			this.msg="���������쳣";
			break;
		case 10031:
			this.msg="ɾ��ʧ�ܣ���ˢ����������ԣ�";
			break;
		default:
			this.msg="����ϵ����Ա��";
			break;
		}
	}
	
	/**
	 * ͨ�ú�����
	 * 	-��Я��object����
	 * @param code
	 * @param msg
	 */
	public ResultBean(Integer code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	/**
	 * ͨ�ú���
	 * @param code
	 * @param msg
	 * @param object
	 */
	public ResultBean(Integer code,String msg,Object object) {
		this.code=code;
		this.msg=msg;
		this.object=object;
	}
	
	/**
	 * �ɹ���
	 * 	-Я��object
	 * @param object
	 */
	public ResultBean(Object object) {
		this.code=0;
		this.msg="�����ɹ���";
		this.object=object;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
