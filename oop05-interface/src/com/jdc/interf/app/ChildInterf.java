package com.jdc.interf.app;

public interface ChildInterf extends ParentOneInterf, ParentTwoInterf {

	@Override
	default void doJob() {
		ParentTwoInterf.super.doJob();
	}
	
}
