package bonjugi.model;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@ToString
public class WareHouse {

	/**
	 * 비디오 창고
	 */
	Set<Video> videos = new LinkedHashSet<>();


	/**
	 * @param videos 비디오들을 넣을수 있음
	 */
	public void adds(Video... videos) {

		for (Video video : videos) {
			this.videos.add(video);
		}
	}

	/**
	 * @return 창고에 남아있는 비디오 목록을 반환함 (stream)
	 */
	public Set<Video> remainVideos() {
		return this.videos.stream().collect(Collectors.toSet());
	}


	/**
	 * 창고에 비디오가 있으면 반환함. (찾으면 꺼냈으니 remove함)
	 *
	 * @param video 찾을 비디오
	 * @return 찾아진 비디오
	 */
	public Video get(Video video) {

		Optional<Video> first = videos.stream().filter(v -> v.equals(video)).findFirst();

		if (first.isPresent()) {
			Video find = first.get();
			videos.remove(find);
			return first.get();
		}

		return null;
	}
}
