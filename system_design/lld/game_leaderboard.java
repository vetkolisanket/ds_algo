/*
Design a game leader board

Design a Leaderboard class, which has the following features:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.

Initially, the leaderboard is empty.
*/

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Leaderboard {

	private Map<Integer, Integer> playerToScoreMap;
	private TreeMap<Integer, Integer> scoreToFrequencyMap;

	public Leaderboard() {
		playerToScoreMap = new HashMap<>();
		scoreToFrequencyMap = new TreeMap<>(Collections.reverseOrder());
	}

	public void addScore(int playerId, int score) {
		if (playerToScoreMap.containsKey(playerId)) {
			int scoreKey = playerToScoreMap.get(playerId);
			scoreToFrequencyMap.put(scoreKey, scoreToFrequencyMap.get(scoreKey) - 1);
		}
		playerToScoreMap.put(playerId, score);
		scoreToFrequencyMap.put(score, scoreToFrequencyMap.getOrDefault(score, 0) + 1);
	}

	public int top(int k) {
		int ans = 0;
		for (Map.Entry<Integer, Integer> entry : scoreToFrequencyMap.entrySet()) {
			if (k > entry.getValue()) {
				ans += entry.getKey() * entry.getValue();
				k -= entry.getValue();
			} else {
				ans += entry.getKey() * k;
				break;
			}
		}
		return ans;
	}

	public void reset(int playerId) {
		int scoreKey = playerToScoreMap.get(playerId);
		scoreToFrequencyMap.put(scoreKey, scoreToFrequencyMap.get(scoreKey) - 1);
		playerToScoreMap.remove(playerId);
	}

	public static void main(String[] args) {
		Leaderboard leaderboard = new Leaderboard();
		leaderboard.addScore(1, 200);
		leaderboard.addScore(2, 300);
		leaderboard.addScore(3, 100);
		System.out.println(leaderboard.top(2));
		leaderboard.reset(1);
		System.out.println(leaderboard.top(2));
		leaderboard.addScore(4, 1000);
		leaderboard.addScore(2, 200);
		leaderboard.addScore(1, 100);
		System.out.println(leaderboard.top(2));
	}

}
