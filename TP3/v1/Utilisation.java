public class Utilisation {

	public static void essai() {
		Calc calc = new Calc();
		// Appeler les procédures et fonctions de la classe Calc

		int[] tab = new int[] {23, 3, 4354, 23, 121, 1, 908, 67};
		Tabs tabs = new Tabs();
		tabs.initTab(tab);
		affTab(tab);
		affTabInv(tab);
		somme(tab);
	}

}
