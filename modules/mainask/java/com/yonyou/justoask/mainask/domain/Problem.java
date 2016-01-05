package com.yonyou.justoask.mainask.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class Problem extends DomainBase implements Serializable{

	private static final long serialVersionUID = 1L;

	private String problemId;
	private String problem;
	private String answer;

	public Problem() {
		super();
	}

	public String getProblemId() {
		return this.problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}