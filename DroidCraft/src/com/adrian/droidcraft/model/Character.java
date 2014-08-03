package com.adrian.droidcraft.model;

import com.google.gson.annotations.SerializedName;

/**
 * World of Warcraft character representation.
 * @author adrian
 *
 */
public class Character {

	private String name;
	private int level;
	@SerializedName("class")private int classNumber;
	
	public Character(String name, int level, int classNumber) {
		super();
		this.name = name;
		this.level = level;
		this.classNumber = classNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
}
