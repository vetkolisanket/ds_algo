import java.util.*;

class ABCD {

	private static int n = 100;

	public static void main(String[] args){
		Map<Integer, List<Tuple>> map = new HashMap();
		for(int i=1;i<=n;i++){
			for(int j=i+1;j<=n;j++){
				Tuple tuple = new Tuple(i, j);
				int val = (int) (Math.pow(i,3) + Math.pow(j,3));
				if(!map.containsKey(val)){
					List<Tuple> list = new ArrayList();
					map.put(val, list);
				}
				map.get(val).add(tuple);
			}
		}
		for(Map.Entry<Integer, List<Tuple>> entry : map.entrySet()){
			int key = entry.getKey();
			List<Tuple> list = entry.getValue();
			int size = list.size();
			if(size > 1) {
				System.out.print(key + " -> ");
				for(int i=0;i<size;i++){
					System.out.print(list.get(i)+" ");
				}
				System.out.println();
			}
		}
	}
}

class Tuple {

	int a;
        int b;

        public Tuple(int a, int b){
        	this.a = a;
                this.b = b;
        }

        public String toString(){
                return "<" + a + ", " + b + ">";
        }
}
