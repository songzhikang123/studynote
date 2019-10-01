package Mycollection;
hahaha
/**
 * �Զ���һ��HashMap
 * ������toStirng����������ʾ
 * �����˷���
 * @author ����
 *
 */
public class szkHashMap04<K,V> {
	Node3[] table;       //λͰ���飬bucked array
	int size;            //��ż�ֵ�Եĸ���
	
	public szkHashMap04() {
			table = new Node3[16]; //һ�㶨��Ϊ2����������
	}
	
	
	
	public 	V get(K key){
		int hash = myHash(key.hashCode(), table.length);
		Object value = null;
		
		if(table[hash]!=null){            //���key��Ӧ������λ�ò�Ϊ����Ա�key
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
		//�������µĽڵ����
		Node3 newNode = new Node3();
		newNode.hash = myHash(key.hashCode(),table.length);
		newNode.key = key;
		newNode.value = value;
		
		Node3 temp = table[newNode.hash];  //���½ڵ��Ӧ�����������λ�ø���temp
		Node3 last = null;             //lastΪ���ڱ��������һ��Ԫ��
		boolean keyrepeat =false;
		
	
		if(temp == null){              //���˴�����Ϊ�գ���ֱ�ӽ��½ڵ�Ž�ȥ
			table[newNode.hash]  = newNode;
			size++;
		}else{
		                               //�粻Ϊ�գ��������Ӧ�����ҵ���Ԫ��
			while(temp!=null){
				                       //�ж�key�Ƿ��ظ�
				if(temp.key.equals(key)){
					keyrepeat = true;
					                   //���ظ��򸲸�value  ��Ϊkey,hashֵ����ͬ������ֻ����value����	
					temp.value = value;
					break;
				}else{                 //�����ظ�����ӵ������
					last = temp;      
					temp = temp.next;  //����������һ��Ԫ���Ƿ�Ϊ��
				}
			}
			if(!keyrepeat){                //���û�з���key�ظ������������ӵ��������
				last.next = newNode;
				size++;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		
		for(int i=0;i<table.length;i++){   //�ȱ�������
			Node3 temp = table[i];         //�������ֵ����temp
			
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
		//System.out.println("hash is MyHash " + (v&(length-1)));//ֱ��λ����Ч�ʸ�
		//System.out.println("hash is MyHash " + (v%(length-1)));//ȡģ����Ч�ʵ�

		return v&(length-1);
	}

}
