package com.yonyou.justoask.favorite.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class Collect extends DomainBase implements Serializable{

	private static final long serialVersionUID = 1L;

	private String collectId;
	private String problemId;
	private String userId;
	private String collectTime;

	public Collect() {
		super();
	}

	public String getCollectId() {
		return this.collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getProblemId() {
		return this.problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}