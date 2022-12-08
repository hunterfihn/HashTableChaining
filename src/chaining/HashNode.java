package chaining;

public class HashNode
{
	public String key;
	public String value;
	public int code;
	public HashNode next;
	
	public HashNode(String key, String value, int code)
	{
		this.key = key;
		this.value = value;
		this.code = code;
		this.next = null;
	}
}
