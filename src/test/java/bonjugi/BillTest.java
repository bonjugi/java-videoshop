package bonjugi;

import bonjugi.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BillTest {



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
    public void 빌_텍스트_테스트() {

        RentedVideo rented1 = shop.rental(video1, bonjugi, 1);
        RentedVideo rented2 = shop.rental(video2, bonjugi, 3);

        Set<RentedVideo> set = new HashSet<>();
        set.add(rented1);
        set.add(rented2);

        Bill bill = new DefaultBill();
        String billText = bill.getBill(set);
        assertThat(billText).contains("장르 : 영화, 제목 : 보헤미안, 일일 대여요금 : 100, 대여기간 : 1 | 가격 : 100");
        assertThat(billText).contains("장르 : 스포츠, 제목 : 국가대표, 일일 대여요금 : 200, 대여기간 : 3 | 가격 : 600");
        assertThat(billText).contains("총 2 개  700원");
    }

}
