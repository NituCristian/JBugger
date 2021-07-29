package dto;

import java.io.Serializable;

public class RightDto implements Serializable {

	private static final long serialVersionUID = 303620040657504658L;

	private int rightId;
	private String name;

	public RightDto() {
	}

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
