package bonjugi.model;

import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@ToString
public class VideoShop {

	private WareHouse wareHouse;                                // 비디오 창고
	private Set<RentedVideo> rentedList = new HashSet<>();      // 렌탈해간 비디오 목록
	private Bill bill;                                          // 빌지

	public VideoShop(WareHouse house) {
		wareHouse = house;
		bill = new DefaultBill();
	}


	/**
	 * 렌탈하기
	 *
	 * @param video    비디오
	 * @param customer 고객
	 * @param day      대여일자
	 * @return 렌탈된비디오
	 */
	public RentedVideo rental(Video video, Customer customer, int day) {

		Video getVideo = wareHouse.get(video);

		if (getVideo != null) {
			RentedVideo newRented = new RentedVideo(getVideo, customer, day);
			rentedList.add(newRented);
			return newRented;
		}
		return null;


	}

	/**
	 * 빌지 출력하기
	 *
	 * @param customer 고객
	 */
	public void printBill(Customer customer) {
		Set<RentedVideo> rentedVideoList = getRentedVideoList(customer);
		String bill = this.bill.getBill(rentedVideoList);
		System.out.println(bill);

	}


	/**
	 * 창고에 남아있는 비디오를 반환함. (다 렌탈해가면 없음)
	 *
	 * @return 렌탈가능한 비디오 목록
	 */
	public Set<Video> getRentalableList() {
		return wareHouse.remainVideos();
	}


	/**
	 * @param customer 고객
	 * @return 해당하는 고객의 렌탈비디오 목록
	 */
	public Set<RentedVideo> getRentedVideoList(Customer customer) {
		return rentedList.stream().filter(x -> x.getCustomer().equals(customer)).collect(Collectors.toSet());
	}
}
