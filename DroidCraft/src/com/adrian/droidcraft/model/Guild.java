package com.adrian.droidcraft.model;

/**
 * Class representing a World of Warcraft Guild in the REST request for guild information under 
 * battle.net API
 * @author adrian
 *
 */
public class Guild {
	private String name;
	private String realm;
	private int level;
	private Member[] members;

	public Guild(String name, String realm, int level, Member[] members) {
		super();
		this.name = name;
		this.realm = realm;
		this.level = level;
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Member[] getMembers() {
		return members;
	}

	public void setMembers(Member[] members) {
		this.members = members;
	}

}
