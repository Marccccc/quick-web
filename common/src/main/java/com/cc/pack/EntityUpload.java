package com.cc.pack;

import java.util.Collections;
import java.util.List;

/**
 * 含上传文件实体接收类
 *
 * @author cc
 */
public class EntityUpload<T>{

    private T entity;

    private List<String> upLoadFileId = Collections.emptyList();

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public List<String> getUpLoadFileId() {
		return upLoadFileId;
	}

	public void setUpLoadFileId(List<String> upLoadFileId) {
		this.upLoadFileId = upLoadFileId;
	}

}
