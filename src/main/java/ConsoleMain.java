import bonjugi.model.*;

import java.util.Scanner;

public class ConsoleMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);


		// 비디오 등록. shop 생성
		VideoShop shop = standbyVideoInput(sc);

		System.out.println("==================== 입고 완료! 대여 가능 목록 ====================");
		System.out.println(shop.getRentalableList());
		System.out.println("        총 " + shop.getRentalableList().size() + "개");
		System.out.println("===================================================================");

		// 고객 이름 입력. Customer 생성
		Customer customer = standbyCustomerInput(sc);

		// 비디오 렌탈. 렌탈정보 샵에 기록
		standbyRentalInput(sc,shop,customer);

		// 출력
		shop.printBill(customer);

	}

	private static VideoShop standbyVideoInput(Scanner sc) {

		WareHouse wareHouse = new WareHouse();

		while (true) {
			System.out.println("창고에 넣을 비디오 정보들을 입력하시오. (예 : 스포츠 월드컵 10000 10) // 종료:q");
			String line = sc.nextLine();

			if (isQ(line)) {
				break;
			}

			try {
				String[] s = line.split(" ");
				Genre genre = Genre.valueOf(s[0]);
				String title = s[1];
				int price = Integer.parseInt(s[2]);
				int maxRentalDay = Integer.parseInt(s[3]);

				Video video = Video.of(genre, title, price, maxRentalDay);

				wareHouse.adds(video);
			} catch (Exception e) {
				System.out.println("잘못입력하셨습니다. " + e.toString());
			}
		}

		return new VideoShop(wareHouse);
	}

	private static Customer standbyCustomerInput(Scanner sc) {

		System.out.println("당신의 이름을 입력하세요.");
		return Customer.of(sc.nextLine());
	}

	private static void standbyRentalInput(Scanner sc, VideoShop shop, Customer customer) {

		while (true) {
			System.out.println("빌릴 비디오 이름과 대여기간을 입력하세요. (예 : 월드컵 10) // 종료:q");
			String line = sc.nextLine();
			if (isQ(line)) {
				break;
			}

			try {
				String[] s = line.split(" ");
				String videoName = s[0];
				int day = Integer.parseInt(s[1]);

				shop.rental(Video.of(videoName), customer, day);

			} catch (Exception e) {
				System.out.println("잘못입력하셨습니다. " + e.toString());
			}
		}
	}


	/**
	 * 종료 키워드
	 *
	 * @param q 종료문자열
	 * @return 종료문자열이 맞는지 여부
	 */
	private static boolean isQ(String q) {
		return q.equals("ㅂ") || q.toLowerCase().equals("q");
	}



}
