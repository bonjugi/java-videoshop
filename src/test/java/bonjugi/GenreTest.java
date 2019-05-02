package bonjugi;

import bonjugi.model.Genre;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreTest {

	@Test
	public void 한글이름으로_장르찾기() {

		Genre movie = Genre.valueOf("영화");
		assertThat(movie).isEqualTo(Genre.영화);

		Genre documentary = Genre.valueOf("다큐멘터리");
		assertThat(documentary).isEqualTo(Genre.다큐멘터리);

		Genre sports = Genre.valueOf("스포츠");
		assertThat(sports).isEqualTo(Genre.스포츠);


	}

	@Test(expected = IllegalArgumentException.class)
	public void 한글이름으로_장르찾기_없는이름으로는_찾을수없음(){
		Genre.valueOf("도큐멘타링");
	}


}
