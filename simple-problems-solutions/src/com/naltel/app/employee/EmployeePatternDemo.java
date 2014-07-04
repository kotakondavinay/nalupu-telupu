/**
 * 
 */
package com.naltel.app.employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.naltel.app.empoyee.model.Employee;

/**
 * @author Vinay.
 *
 */
public class EmployeePatternDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericTreeNode<Employee> treeEmployee = new GenericTreeNode<Employee>();
		Employee employee = new Employee(1l,"CEO",3,1.0,true,null);
		Employee employee1 = new Employee(2l,"Mgr",3,1.0,true,1l);
		Employee employee2 = new Employee(3l,"Mgr2",2,1.0,true,1l);
		Employee employee3 = new Employee(4l,"Architect",4,1.0,true,1l);
		Employee employee4 = new Employee(5l,"Emp1",3,1.0,false,2l);
		Employee employee5 = new Employee(6l,"Emp2",2,1.0,false,2l);
		Employee employee6 = new Employee(7l,"Emp3",2,1.0,false,3l);
		
		GenericTreeNode<Employee> treeEmployee1 = new GenericTreeNode<Employee>(employee1);
		GenericTreeNode<Employee> treeEmployee2 = new GenericTreeNode<Employee>(employee2);
		GenericTreeNode<Employee> treeEmployee3 = new GenericTreeNode<Employee>(employee3);
		GenericTreeNode<Employee> treeEmployee4 = new GenericTreeNode<Employee>(employee4);
		GenericTreeNode<Employee> treeEmployee5 = new GenericTreeNode<Employee>(employee5);
		GenericTreeNode<Employee> treeEmployee6 = new GenericTreeNode<Employee>(employee6);
		treeEmployee3.addChild(treeEmployee6);
		treeEmployee2.addChild(treeEmployee5);
		treeEmployee2.addChild(treeEmployee4);
		treeEmployee.addChild(treeEmployee1);
		treeEmployee.addChild(treeEmployee2);
		treeEmployee.addChild(treeEmployee3);
		treeEmployee.data = employee;
		
		EmployeePatternDemo employeeDemo = new EmployeePatternDemo();
		employeeDemo.printTopKPerformers(treeEmployee, 2);


	}
	
	private void printTopKPerformers(GenericTreeNode<Employee> employee,Integer k) {
		if(employee.hasChildren()) {
			printOnlyChildrenPerformance(employee, k);
			List<GenericTreeNode<Employee>> lstEmployees = employee.getChildren();
			for(GenericTreeNode<Employee> emp:lstEmployees){
				printTopKPerformers(emp, k);
			}
		}
	}
	
	private void printOnlyChildrenPerformance(GenericTreeNode<Employee> employee, Integer k) {
		if(employee.hasChildren()) {
			List<GenericTreeNode<Employee>> lstEmployees = employee.getChildren();
			Collections.sort(lstEmployees, new Comparator<GenericTreeNode<Employee>>(){

				public int compare(GenericTreeNode<Employee> o1,
						GenericTreeNode<Employee> o2) {
					return o2.data.getRank().compareTo(o1.data.getRank());
				}
			});
			int count = 0;
			for(GenericTreeNode<Employee> emp:lstEmployees){
				if(count < k) {
					System.out.println(emp.data.getName());	
				}
				count +=1;
			}
		}
	}

}
