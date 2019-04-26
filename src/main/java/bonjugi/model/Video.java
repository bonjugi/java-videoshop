package bonjugi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "title")
public class Video {

	private final String title;     // 제목
	private Genre genre;            // 장르
	private int price;              // 가격 (일일)

	public Video(String title) {
		this.title = title;
	}

	public Video(Genre genre, String title, int price) {
		this.genre = genre;
		this.title = title;
		this.price = price;
	}

	public static Video of(String title) {
		return new Video(title);
	}

	public static Video of(Genre genre, String title, int price) {
		return new Video(genre, title, price);
	}
}
