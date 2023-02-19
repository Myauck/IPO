public class Tabs {

	public void affTab(final int[] tab) {
		for(int i = 0; i < tab.length; i++) {
			System.out.println(
				String.format("tab[%d] = %d", i, tab[i]);
			);
		}
	}

	public void affTabInv(final int[] tab) {
		int[] tabInv = new int[tab.length];
		for(int i = 0; i < tab.length; i++) {
			tabInv[tab.length-i-1] = tab[i];
		}
		affTab(tabInv);
	}

	public void initTab(final int[] tab) {
		for(int i = 0; i < tab.length; i++) {
			tab[i] = i*i;
		}
	}

	public int somme(final int[] tab) {
		int s = 0;
		for(int i : tab) {
			s+=i;
		}
		return s;
	}

}
