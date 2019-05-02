package bonjugi;

import bonjugi.model.Genre;
import bonjugi.model.Video;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoTest {

	@Test
	public void 비디오_동치성() {
		Video video1 = Video.of("보헤미안");
		Video video2 = Video.of("보헤미안");
		assertThat(video1).isEqualTo(video2);

		Video video3 = Video.of("다른이름");
		assertThat(video1).isNotEqualTo(video3);
	}

	@Test
	public void 장르_제목_대여요금_대여기간_필드검증() {
		Video genre1 = Video.of(Genre.영화, "보헤미안", 100, 10);

		assertThat(genre1.getGenre()).isEqualTo(Genre.영화);
		assertThat(genre1.getTitle()).isEqualTo("보헤미안");
		assertThat(genre1.getPrice()).isEqualTo(100);
		assertThat(genre1.getMaxRentalDay()).isEqualTo(10);
	}

}
