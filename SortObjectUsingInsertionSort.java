import java.util.*;

class Student{
	private int id;
	private String fname;
	private double cgpa;
	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public double getCgpa() {
		return cgpa;
  }
    public boolean compare(Student other) {
        if(this.cgpa > other.cgpa) return true;
        else if(this.cgpa == other.cgpa) {
            if(this.fname.compareTo(other.fname) < 0) return true;
            else if(this.fname.compareTo(other.fname) == 0) {
                if(this.id < other.id) return true;
                else return false;
            } else return false;
        } else return false;
    }
}

//Complete the code
public class SortObjectUsingInsertionSort
{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		
		List<Student> studentList = new ArrayList<Student>();
		while(testCases>0){
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();
			
			Student st = new Student(id, fname, cgpa);
			studentList.add(st);
			
			testCases--;
		}
      
        for(int i = 0; i < studentList.size(); i++) {
            for(int j = i; j > 0; j--) {
                if(studentList.get(j).compare(studentList.get(j - 1))){
                    Student tmp = studentList.get(j);
                    studentList.set(j, studentList.get(j - 1));
                    studentList.set(j - 1, tmp);
                } else break;
            }
        }
      
      	for(Student st: studentList){
			System.out.println(st.getFname());
		}
	}
}
