package com.jqmkj.WaterResponseSystem.iot.prorocol;
 

/**
 * 说明：协议消息头
 *
 */
public class ProtocolHeader{

	//public static int READ_HEAD_SIZE = 2;

	private CharSequence head;//固定为2位##
	private int contentSize;//数据段长度-4位

	public CharSequence getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int getContentSize() {
		return contentSize;
	}

	public void setContentSize(int contentSize) {
		this.contentSize = contentSize;
	}

	public ProtocolHeader() { }

	public ProtocolHeader(CharSequence head, int contentSize) {
		this.head = head;
		this.contentSize = contentSize;
	}

	@Override
	public String toString() {
		return "ProtocolHeader{" +
				"head=" + head +
				", contentSize=" + contentSize +
				'}';
	}
}
