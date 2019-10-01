package Mycollection;
hahaha
/**
 * 自定义一个HashMap
 * 增加了toStirng方法便于显示
 * 增加了泛型
 * @author 若晴
 *
 */
public class szkHashMap04<K,V> {
	Node3[] table;       //位桶数组，bucked array
	int size;            //存放键值对的个数
	
	public szkHashMap04() {
			table = new Node3[16]; //一般定义为2的整数次幂
	}
	
	
	
	public 	V get(K key){
		int hash = myHash(key.hashCode(), table.length);
		Object value = null;
		
		if(table[hash]!=null){            //如果key对应的数组位置不为空则对比key
			Node3 temp = table[hash];
			while(temp!=null){
				
				if(temp.key.equals(key)){ 
					value = (V)temp.value;
					break;
				}else{
					temp = temp.next;
				}
			}
			
		}
		
		
		return (V) value;
	}
	
	
	
	public void put(K key,V value){
		//定义了新的节点对象
		Node3 newNode = new Node3();
		newNode.hash = myHash(key.hashCode(),table.length);
		newNode.key = key;
		newNode.value = value;
		
		Node3 temp = table[newNode.hash];  //将新节点对应的在数组里的位置赋给temp
		Node3 last = null;             //last为正在遍历的最后一个元素
		boolean keyrepeat =false;
		
	
		if(temp == null){              //若此处数组为空，则直接将新节点放进去
			table[newNode.hash]  = newNode;
			size++;
		}else{
		                               //如不为空，则遍历对应链表，找到空元素
			while(temp!=null){
				                       //判断key是否重复
				if(temp.key.equals(key)){
					keyrepeat = true;
					                   //若重复则覆盖value  因为key,hash值都相同，所以只覆盖value即可	
					temp.value = value;
					break;
				}else{                 //若不重复则添加到链表后方
					last = temp;      
					temp = temp.next;  //继续遍历下一个元素是否为空
				}
			}
			if(!keyrepeat){                //如果没有发生key重复的情况，则添加到链表最后
				last.next = newNode;
				size++;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		
		for(int i=0;i<table.length;i++){   //先遍历数组
			Node3 temp = table[i];         //将数组的值赋给temp
			
			while(temp!=null){
				sb.append("key: "+temp.key+" value: "+temp.value+",");
				temp = temp.next;
			}
		}
			sb.setCharAt(sb.length()-1, '}');
			return sb.toString();
	}

	
	
	public static void main(String[] args) {
		szkHashMap04<Integer,String> m = new szkHashMap04<>();
		m.put(10, "ljd");
		m.put(20, "szk");
		m.put(30, "wjj");
		m.put(85, "kk");
		System.out.println(m);
		System.out.println(m.get(10));
	}
	
	
	
	public int myHash(int v,int length){
		//System.out.println("hash is MyHash " + (v&(length-1)));//直接位运算效率高
		//System.out.println("hash is MyHash " + (v%(length-1)));//取模运算效率低

		return v&(length-1);
	}

}
