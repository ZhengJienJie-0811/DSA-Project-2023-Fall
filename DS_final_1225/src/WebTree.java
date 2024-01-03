import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebTree
{
	public WebNode root;

	public WebTree(WebPage rootPage)
	{
		this.root = new WebNode(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException
	{
		
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException
	{
		// YOUR TURN
		// 3. compute the score of children nodes via post-order, then setNodeScore for
		// startNode
//		if(!startNode.children.isEmpty())
//			for(int i = 0; i < startNode.children.size(); i++) {
//				startNode.children.get(i).setNodeScore(keywords);
//			}
//		startNode.setNodeScore(keywords);
		for(WebNode n: startNode.children) {
			setPostOrderScore(n,keywords);
			n.setNodeScore(keywords);
		}
		//startNode.setNodeScore(keywords);
		

	}

	public void eularPrintTree()
	{
		eularPrintTree(root,3);
	}

	private void eularPrintTree(WebNode startNode,int maxDepth) {
		 int nodeDepth = startNode.getDepth();

	        if (nodeDepth > maxDepth) {
	            return;
	        }

	        if (nodeDepth > 1) {
	            System.out.print("\n" + repeat("\t", nodeDepth - 1));
	        }

	        System.out.print("(");
	        System.out.print(startNode.webPage.name + "," + startNode.nodeScore);
	        
	        List<WebNode> sortedChildren = new ArrayList<>(startNode.children);
	        sortedChildren.sort((node1, node2) -> Double.compare(node2.getNodeScore(), node1.getNodeScore()));

	        for (WebNode child : sortedChildren) {
	            eularPrintTree(child, maxDepth - nodeDepth);
	        }

	        System.out.print(")");

	        if (startNode.isTheLastChild()) {
	            System.out.print(repeat("\t", nodeDepth - 2));
	        }
	}
	
	private String repeat(String str, int repeat)
	{
		String retVal = "";
		for (int i = 0; i < repeat; i++)
		{
			retVal += str;
		}
		return retVal;
	}
}