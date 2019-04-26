package bonjugi;

import bonjugi.model.Genre;
import bonjugi.model.Video;
import bonjugi.model.WareHouse;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WarehouseTest {

	Video video1;
	Video video2;
	Video video3;

	@Before
	public void FIXTURE_비디오() {
		video1 = Video.of(Genre.영화, "보헤미안", 100);
		video2 = Video.of(Genre.스포츠, "국가대표", 200);
		video3 = Video.of(Genre.다큐멘터리, "남극의눈물", 300);

	}

	@Test
	public void 비디오_넣고_빼기() {
		WareHouse house = new WareHouse();

		// 비디오 3개를 넣으면
		house.adds(video1, video2, video3);

		// 등록된 3개가 조회된다
		Set<Video> videoList1 = house.remainVideos();
		assertThat(videoList1).hasSize(3);
		assertThat(videoList1).contains(video1, video2, video3);


		// 한개를 빼면
		Video get = house.get(video1);
		assertThat(get).isEqualTo(video1);

		// 2개만 조회된다.
		Set<Video> videoList2 = house.remainVideos();
		assertThat(videoList2).hasSize(2);
		assertThat(videoList2).contains(video2, video3);
	}

	@Test
	public void 없는비디오_빌리면_null() {
		WareHouse house = new WareHouse();

		// 비디오 1,2만 넣고
		house.adds(video1, video2);


		// 3번 뺴면 null
		assertThat(house.get(video3)).isNull();

	}


}
