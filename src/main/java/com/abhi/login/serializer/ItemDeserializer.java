package com.abhi.login.serializer;

import java.io.IOException;

import com.abhi.login.model.Item;
import com.abhi.login.model.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

public class ItemDeserializer extends StdDeserializer<Item>{

	public ItemDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Item deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
        
		int id = (node.get("id")!=null) ? (Integer) ((IntNode) node.get("id")).numberValue() : 0;
        String itemName = node.get("itemName").asText();
      //  int userId = (Integer) ((IntNode) node.get("userid").numberValue();
        String userName = node.get("owner").get("name").asText();
        
       
        int userId = (Integer) ((IntNode) node.get("owner").get("userid")).numberValue();
 
        return new Item(id, itemName, new User(userId, userName));
	}

}
