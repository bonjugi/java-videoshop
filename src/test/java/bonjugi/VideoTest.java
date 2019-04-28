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
	public void 종류_제목_대여요금_세가지필드_장르는세가지() {
		Video genre1 = Video.of(Genre.영화, "보헤미안", 100, 10);
		Video genre2 = Video.of(Genre.스포츠, "보헤미안", 200, 11);
		Video genre3 = Video.of(Genre.다큐멘터리, "보헤미안", 200, 12);

		assertThat(genre1.getGenre()).isEqualTo(Genre.영화);
		assertThat(genre2.getGenre()).isEqualTo(Genre.스포츠);
		assertThat(genre3.getGenre()).isEqualTo(Genre.다큐멘터리);

		// 그럼에도 동치성은 이름으로 한다
		assertThat(genre1).isEqualTo(genre2);
	}

}
