package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Employee implements WritableComparable<Employee> {

	String ename;
	Long eid;
	String dept;
	Long swipein;
	Long swipeout;

	@Override
	public void readFields(DataInput in) throws IOException {

		this.ename = in.readUTF();
		this.eid = in.readLong();
		this.dept = in.readUTF();
		this.swipein = in.readLong();
		this.swipeout = in.readLong();
	}

	@Override
	public void write(DataOutput out) throws IOException {

		out.writeUTF(ename);
		out.writeLong(eid);
		out.writeUTF(dept);
		out.writeLong(swipein);
		out.writeLong(swipeout);
	}

	@Override
	public int compareTo(Employee emp) {

		int temp = this.ename.compareTo(emp.ename);
		if (temp == 0) {
			temp = this.eid.compareTo(emp.eid);
		}

		return temp;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Long getSwipein() {
		return swipein;
	}

	public void setSwipein(Long swipein) {
		this.swipein = swipein;
	}

	public Long getSwipeout() {
		return swipeout;
	}

	public void setSwipeout(Long swipeout) {
		this.swipeout = swipeout;
	}

}
