package com.hpe.muke.po;

public class Theme {
	private int theid;
	private String thename;
	private int count;
	private int state;
	public int getTheid() {
		return theid;
	}
	public void setTheid(int theid) {
		this.theid = theid;
	}
	public String getThename() {
		return thename;
	}
	public void setThename(String thename) {
		this.thename = thename;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Theme [theid=" + theid + ", thename=" + thename + ", count=" + count + ", state=" + state + "]";
	}

}
