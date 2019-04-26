package bonjugi.model;

import java.util.Set;


public interface Bill {

	/**
	 * 렌탈된 정보를 빌지로 바꿔줌
	 *
	 * @param rentedList 렌탈비디오 목록
	 * @return 빌지 문자열
	 */
	String getBill(Set<RentedVideo> rentedList);
}
