package bonjugi;

import bonjugi.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RentedVideoTest {


    Video video1;
    Video video2;
    Customer bonjugi;


    @Before
    public void FIXTURE() {

        // 비디오
        video1 = Video.of(Genre.영화, "보헤미안", 100, 10);
        video2 = Video.of(Genre.스포츠, "국가대표", 200, 11);

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


        // 비디오정보, 고객, 날짜 셋 다 같으면 일치
        assertThat(rental).isEqualTo(otherRental);

        // 셋중 하나라도 다르면 불일치
        assertThat(rental).isNotEqualTo(differentVideo);
        assertThat(rental).isNotEqualTo(differentDay);
        assertThat(rental).isNotEqualTo(differentCustomer);
    }

    @Test
    public void 대여일자별_비디오_요금_확인(){
        RentedVideo rental10 = new RentedVideo(video1, bonjugi, 10);
        assertThat(rental10.getAmount()).isEqualTo(video1.getPrice() * 10);

        RentedVideo rental20 = new RentedVideo(video1, bonjugi, 20);
        assertThat(rental20.getAmount()).isEqualTo(video1.getPrice() * 20);
    }

}
