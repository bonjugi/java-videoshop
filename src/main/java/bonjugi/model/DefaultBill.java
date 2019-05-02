package bonjugi.model;

import lombok.ToString;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@ToString
public class DefaultBill implements Bill {


	private String format = "장르 : %s, 제목 : %s, 일일 대여요금 : %d, 대여기간 : %d | 가격 : %d";
	private String totalFormat = "총 %d 개  %d원";

	/**
	 * 렌탈 비디오 목록을 빌지 문자열 정보로 변환해줌.
	 *
	 * @param rentedList 렌탈비디오 목록
	 * @return 빌지 문자열
	 */
	@Override
	public String getBill(Set<RentedVideo> rentedList) {

		AtomicInteger totalCount = new AtomicInteger();
		AtomicInteger totalAmount = new AtomicInteger();

		StringBuffer buffer = new StringBuffer();

		buffer.append("================================ Created By DefaultBill ================================ \r\n");

		rentedList.forEach(rented -> {

			Video video = rented.getVideo();
			int day = rented.getDay();
			int amount = rented.getAmount();

			String line = String.format(format, video.getGenre(), video.getTitle(), video.getPrice(), day, amount);
			buffer.append(line + "\r\n");

			totalAmount.addAndGet(amount);
			totalCount.getAndIncrement();
		});


		buffer.append("======================================================================================= \r\n");
		buffer.append(String.format(totalFormat, totalCount.get(), totalAmount.get()) + "\r\n");

		return buffer.toString();
	}
}
