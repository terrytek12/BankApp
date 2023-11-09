package myTesting;

import java.io.Serializable;

public class Message implements Serializable{
		
		private static int count = 0;
		private final int id; 
		private final String text; 
		private final String type; 
		
		public Message(String text, String type) {
			this.text = text;
			this.type = type;
			this.id = ++count;
		}
		
		
		public String getText() {
			return text;
		}
		
		public int getID() {
			return id;
		}
		
		public String getType() {
			return type;
		}
		
		
		
}
