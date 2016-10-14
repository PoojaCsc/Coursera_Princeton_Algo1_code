package week1;

public class QuickFindUF_Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickFindUF obj = new QuickFindUF(8);
		System.out.println(obj.connected(3, 4));
		obj.union(1, 3);
		obj.union(4, 5);
		obj.union(3, 6);
		System.out.println(obj.connected(1, 6));
	}

}
