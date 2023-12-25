import java.io.IOException;
import java.util.ArrayList;

public class WebPage
{
	public String url;
	public String name;
	public KeywordCounter counter;
	public double score;

	public WebPage(String url, String name)
	{
		this.url = url;
		this.name = name;
		this.counter = new KeywordCounter(url);
	}

	public void setScore(ArrayList<Keyword> keywords) throws IOException
	{
		score = 0;
		// YOUR TURN
//		1. calculate the score of this webPage
		for(int i = 0; i < keywords.size(); i++) {
			score += counter.countKeyword(keywords.get(i).name) * keywords.get(i).weight;
		}

	}
}
