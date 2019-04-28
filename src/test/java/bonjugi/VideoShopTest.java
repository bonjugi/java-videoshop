package bonjugi;

import bonjugi.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoShopTest {


	VideoShop shop;

	Video video1;
	Video video2;
	Video video3;

	WareHouse house;

	Customer bonjugi;

	@Before
	public void FIXTURE_샵() {

		// 비디오
		video1 = Video.of(Genre.영화, "보헤미안", 100, 10);
		video2 = Video.of(Genre.스포츠, "국가대표", 200, 11);
		video3 = Video.of(Genre.다큐멘터리, "남극의눈물", 300, 12);

		// 창고
		house = new WareHouse();
		house.adds(video1, video2, video3);

		// 샵
		shop = new VideoShop(house);

		// 고객
		bonjugi = Customer.of("bonjugi");
	}


	@Test
	public void 빌린비디오_동치성테스트() {
		RentedVideo rental = new RentedVideo(video1, bonjugi, 10);
		RentedVideo otherRental = new RentedVideo(video1, bonjugi, 10);

		RentedVideo differentVideo = new RentedVideo(video2, bonjugi, 10);
		RentedVideo differentDay = new RentedVideo(video1, bonjugi, 20);
		RentedVideo differentCustomer = new RentedVideo(video1, Customer.of("모르는사람?"), 10);


		// 비디오정보와 고객 날짜가 같으면 일치
		assertThat(rental).isEqualTo(otherRental);

		// 셋중 하나라도 다르면 불일치
		assertThat(rental).isNotEqualTo(differentVideo);
		assertThat(rental).isNotEqualTo(differentDay);
		assertThat(rental).isNotEqualTo(differentCustomer);
	}

	@Test
	public void 빌린비디오_가격측정() {
		RentedVideo rental = new RentedVideo(video1, bonjugi, 10);
		assertThat(rental.getAmount()).isEqualTo(video1.getPrice() * 10);

		RentedVideo rental2 = new RentedVideo(video2, bonjugi, 20);
		assertThat(rental2.getAmount()).isEqualTo(video2.getPrice() * 20);


	}

	@Test
	public void 샵에서_빌리기() {
		RentedVideo rental1 = shop.rental(video1, bonjugi, 3);

		// 빌린 정보 확인
		assertThat(rental1).isEqualTo(new RentedVideo(video1, bonjugi, 3));

		// 정보 다르면 불일치
		assertThat(rental1).isNotEqualTo(new RentedVideo(video2, bonjugi, 3));
	}

	@Test
	public void 빌리면_샵에서는_빌릴수있는목록이_사라진다() {

		// 원래는 있던 video1 이
		Set<Video> rentalList1 = shop.getRentalableList();
		assertThat(rentalList1).contains(video1);

		// 빌리면
		shop.rental(video1, bonjugi, 3);

		// 사라진다
		Set<Video> rentalList2 = shop.getRentalableList();
		assertThat(rentalList2).doesNotContain((video1));

	}

	@Test
	public void 렌탈정보를_고객이름으로_빌린비디오목록_조회가능() {


		// 1개 빌리면
		RentedVideo rented1 = shop.rental(video1, bonjugi, 3);

		// 1개 조회됨.
		Set<RentedVideo> rentedList1 = shop.getRentedVideoList(bonjugi);
		assertThat(rentedList1).hasSize(1);
		assertThat(rentedList1).contains(rented1);

		// 1개 더 빌리면
		RentedVideo rented2 = shop.rental(video2, bonjugi, 3);

		// 2개 조회됨.
		Set<RentedVideo> rentedList2 = shop.getRentedVideoList(bonjugi);
		assertThat(rentedList2).hasSize(2);
		assertThat(rentedList2).contains(rented1, rented2);

	}

	@Test
	public void 다른사람은_조회안됨() {
		// bonjugi가 1개 빌리면
		shop.rental(video1, bonjugi, 3);

		// 다른사람은 조회안됨
		Set<RentedVideo> rentedList = shop.getRentedVideoList(Customer.of("모르는사람"));
		assertThat(rentedList).hasSize(0);
	}


	@Test
	public void 빌지출력() {
		// 2개 빌리고,
		RentedVideo rented1 = shop.rental(video1, bonjugi, 3);
		RentedVideo rented2 = shop.rental(video2, bonjugi, 3);

		// 빌지 출력
		shop.printBill(bonjugi);

	}

	@Test(expected = IllegalArgumentException.class)
	public void 최대대여기간_이상은_빌릴수없음() {
		shop.rental(video1, bonjugi, 11);
	}

	@Test(expected = IllegalArgumentException.class)
	public void 없는비디오일때는() {
		RentedVideo rented1 = shop.rental(Video.of("모르는비디오"), bonjugi, 3);
		System.out.println(rented1);
	}


}
