package mappers;

import dto.RightDto;
import entities.Right;

public class RightMapper {

	public static Right mapRightDtoToRight(RightDto rightDto) {
		Right right = new Right();

		right.setRightId(rightDto.getRightId());
		right.setName(rightDto.getName());

		return right;
	}

	public static RightDto mapRightToRightDto(Right right) {
		RightDto rightDto = new RightDto();

		rightDto.setRightId(right.getRightId());
		rightDto.setName(right.getName());

		return rightDto;
	}
}
