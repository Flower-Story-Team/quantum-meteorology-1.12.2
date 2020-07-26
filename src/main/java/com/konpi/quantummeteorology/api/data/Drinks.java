package com.konpi.quantummeteorology.api.data;

public enum Drinks {
	EXAMPLE("ylll:testdrink", 100),// 这是个例子

	PUREWATER("quantummereorology:purewater",30) ;
	/**
	 * @param name
	 *            饮料的注册名
	 * @param thirst
	 *            回复的口渴度
	 */
	private Drinks(String name, int thirst) {
		this.name = name;
		this.thirst = thirst;
	}

	public static Drinks getDrink(String s) {
		for (Drinks d : Drinks.values()) {
			if (s.equals(d.name))
				return d;
		}
		return null;
	}

	private String name;
	private int thirst;

	public String getName() {
		return name;
	}

	public int getThirst() {
		return thirst;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setThirst(int thirst) {
		this.thirst = thirst;
	}
}
