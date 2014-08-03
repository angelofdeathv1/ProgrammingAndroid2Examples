package com.adrian.droidcraft.model;

/**
 * Class representing a member from a guild, wich has a character information and a guild rank.
 * @author adrian
 *
 */
public class Member {
	private Character character;
	private int rank;

	public Member(Character character, int rank) {
		super();
		this.character = character;
		this.rank = rank;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
