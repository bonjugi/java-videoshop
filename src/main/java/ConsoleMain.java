import bonjugi.model.*;

import java.util.Scanner;

public class ConsoleMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 비디오샵 생성
		WareHouse warehouse = new WareHouse();
		VideoShop shop = new VideoShop(warehouse);


		// 비디오 등록
		while (true) {
			System.out.println("창고에 넣을 비디오 정보들을 입력하시오. (예 : 스포츠 월드컵 10000 10) // 종료:q");
			String line = sc.nextLine();

			if (isQ(line)) {
				break;
			}

			try {
				Video video = createVideo(line);
				warehouse.adds(video);
			} catch (Exception e) {
				System.out.println("잘못입력하셨습니다. " + e.getMessage());
			}
		}
		System.out.println("비디오가 입고되었습니다. " + shop.getRentalableList().size() + "개");
		System.out.println(shop.getRentalableList());
		System.out.println("============================");


		// 고객 이름 입력
		System.out.println("당신의 이름을 입력하세요.");
		Customer customer = createCustomer(sc.nextLine());


		// 비디오 렌탈
		while (true) {
			System.out.println("빌릴 비디오 이름과 대여기간을 입력하세요. (예 : 월드컵 10) // 종료:q");
			String line = sc.nextLine();
			if (isQ(line)) {
				break;
			}

			try {
				rentalFromShop(line, shop, customer);
			} catch (Exception e) {
				System.out.println("잘못입력하셨습니다. " + e.getMessage());
			}
		}


		// 출력
		shop.printBill(customer);
	}


	/**
	 * 종료 키워드
	 *
	 * @param q 종료문자열
	 * @return 종료문자열이 맞는지 여부
	 */
	public static boolean isQ(String q) {
		return q.equals("ㅂ") || q.toLowerCase().equals("q");
	}

	/**
	 * 문자열정보로 렌탈하기
	 *
	 * @param line     입력값
	 * @param shop     샵
	 * @param customer 고객
	 * @return 렌탈된 비디오
	 */
	public static RentedVideo rentalFromShop(String line, VideoShop shop, Customer customer) {

		String[] s = line.split(" ");
		String videoName = s[0];
		int day = Integer.parseInt(s[1]);

		return shop.rental(Video.of(videoName), customer, day);
	}

	/**
	 * 문자열로 고객 생성
	 *
	 * @param line System.in
	 * @return Customer
	 */
	public static Customer createCustomer(String line) {

		return Customer.of(line);
	}

	/**
	 * 문자열로 비디오 생성
	 *
	 * @param line System.in
	 * @return Video
	 */
	public static Video createVideo(String line) {

		String[] s = line.split(" ");
		Genre genre = Genre.valueOf(s[0]);
		String title = s[1];
		int price = Integer.parseInt(s[2]);
		int maxRentalDay = Integer.parseInt(s[3]);

		return Video.of(genre, title, price, maxRentalDay);
	}


}
