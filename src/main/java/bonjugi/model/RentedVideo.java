package bonjugi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * 렌탈된 비디오.
 * 비디오샵에서 비디오를 빌리면(꺼내면) RentedVideo 를 반환한다.
 */
@Getter
@ToString
@EqualsAndHashCode
public class RentedVideo {

	private int day;                // 대여일
	private Video video;            // 비디오
	private Customer customer;      // 대여자

	public RentedVideo(Video video, Customer customer, int day) {
		this.video = video;
		this.day = day;
		this.customer = customer;
	}

	public int getAmount() {
		return video.getPrice() * day;
	}
}
