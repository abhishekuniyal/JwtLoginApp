package com.abhi.login.serializer;

import java.io.IOException;

import com.abhi.login.model.Item;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ItemSerializer extends StdSerializer<Item> {

	private static final long serialVersionUID = 1L;

	public ItemSerializer(Class<Item> t) {
		super(t);
	}

	/**
	 * 
	 */
	public void serialize(Item item, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		// TODO Auto-generated method stub
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("itemId", item.getId());
		jsonGenerator.writeStringField("itemname", item.getItemName());
		jsonGenerator.writeObjectFieldStart("user");
		jsonGenerator.writeNumberField("UserId1", item.getOwner().id);
		jsonGenerator.writeStringField("userName", item.getOwner().name);
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
	}

}
