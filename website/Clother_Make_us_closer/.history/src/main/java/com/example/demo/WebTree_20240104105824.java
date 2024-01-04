package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebTree {

	public WebNode root;
	private List<WebNode> allSortedChildren;

	public WebTree(WebPage rootPage) {
		this.root = new WebNode(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException {

		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException {

		for (WebNode n : startNode.children) {
			setPostOrderScore(n, keywords);
			n.setNodeScore(keywords);
		}

	}

	public void eularPrintTree() {
		allSortedChildren = new ArrayList<>();
		eularPrintTree(root, 3);
	}

	private void eularPrintTree(WebNode startNode, int maxDepth) {
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

		// Add the sorted children of this node to the global list
		allSortedChildren.addAll(sortedChildren);

		for (WebNode child : sortedChildren) {
			eularPrintTree(child, maxDepth - nodeDepth);
		}

		System.out.print(")");

		if (startNode.isTheLastChild()) {
			System.out.print(repeat("\t", nodeDepth - 2));
		}
	}

	public List<WebPage> getSortedChildrenNames() {
		return allSortedChildren.stream()
				.map(node -> node.webPage)
				// .map(node -> node.webPage.url)
				.collect(Collectors.toList());
	}

	private String repeat(String str, int repeat) {
		String retVal = "";
		for (int i = 0; i < repeat; i++) {
			retVal += str;
		}
		return retVal;
	}
}