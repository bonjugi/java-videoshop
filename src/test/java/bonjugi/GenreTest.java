package bonjugi;

import bonjugi.model.Genre;
import org.junit.Test;

public class GenreTest {

	@Test
	public void 한글이름으로_장르찾기() {

		Genre movie = Genre.valueOf("영화");
		System.out.println(movie);

	}
}
