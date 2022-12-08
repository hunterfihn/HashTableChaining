package chaining;

//import java.util.ArrayList();

public class Table 
{
	public HashNode buckets[];
	public int size; //capacity of table
	public int count; //# of items in table
	public final float threshold = 0.7f;
	public final int initSize = 10000;

	public Table()
	{
		this.size = initSize;
		this.count = 0;
		this.buckets = new HashNode[this.size];
		
	}
	
	public int getHash(String key)
	{
		int hash = key.hashCode();
		return hash % this.size;
	}
	
	public void add(String key, String value)
	{
		//use get hash to find bucket index
		int code = this.getHash(key);
		HashNode curr = this.buckets[code];
		if(curr == null) {
			HashNode newNode = new HashNode(key, value, code);
			this.buckets[code] = newNode;
		}
		else {
			while(curr != null)
			{
				if(curr.key.equals(key))
				{
					curr.value = value;
					return;
				} else {
					if(curr.next == null)
					{
						HashNode newNode = new HashNode(key, value, code);
						curr.next = newNode;
						this.count++;
						this.resize();
						return;
					}
					curr = curr.next;
				}
			}
		}
		//append it to tail of link on the bucket
	}
	
	public HashNode get(String key)
	{
		int code = this.getHash(key);
		HashNode target = this.buckets[code];
		if(target == null)
		{
			return null;
		}
		else
		{
			HashNode curr = target;
			while(curr != null)
			{
				if(curr.key.equals(key))
				{
					return curr;
				}
				else {
					curr = curr.next;
				}
			}
			return null;
		}
	}
	
	public void remove(String key)
	{
		int code = this.getHash(key);
		HashNode curr = this.buckets[code];
		if(curr == null)
		{
			return;
		}
		else {
			if(curr.key.equals(key))
			{
				HashNode next = curr.next;
				this.buckets[code] = next;
				curr.next = null;
			}
			else {
				HashNode pre = curr;
				curr = curr.next;
				while(curr != null) {
					if(curr.key.equals(key))
					{
						pre.next = curr.next;
						curr.next = null;
						return;
					}
					else {
						pre = curr;
						curr = curr.next;
					}
				}
			}
		}
	}
	
	private void resize()
	{
		if(this.tooBig())
		{
			this.size = this.size * 2;
			this.count = 0;
			HashNode[] original = this.buckets;
			this.buckets = new HashNode[this.size];
			for(int i =0; i < original.length; i++) {
				HashNode curr = original[i];
				while(curr != null) {
					this.add(curr.key, curr.value);
					curr = curr.next;
				}
			}
		}
	}
	
	private boolean tooBig()
	{
		return (float) this.count / (float) this.size > this.threshold;
	}
	
}
