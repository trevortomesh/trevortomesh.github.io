/**
 * TrinityModelApp
 *
 * <p>Purpose: Demonstrates, in object-oriented form, a classical Christian Trinitarian model
 * as represented in the provided UML diagram.
 *
 * <p>Publication note: This is a pedagogical analogy. In classical Christian theology, God is
 * not a composition of parts or objects. The model visualizes distinctions and relations without
 * claiming to capture the divine mystery exhaustively.
 *
 * <p>Core references used throughout this file:
 * - Scripture: Deut 6:4; Matt 28:19; John 1:1, 1:14, 1:18; John 14:26; John 15:26; John 16:13-15;
 *   John 20:22; Acts 5:3-4; 1 Cor 8:6; 2 Cor 13:14; Heb 1:3.
 * - Nicene-Constantinopolitan Creed (381): one God; Son "begotten, not made"; Spirit "Lord and
 *   giver of life".
 * - Athanasian Creed (Quicumque vult): distinction of Persons, unity of essence, co-equality.
 * - Fourth Lateran Council (1215): divine simplicity (DS 800-806).
 * - Catechism of the Catholic Church: 232-267 (esp. 237, 242-248, 253-255).
 * - Council of Florence (1439), Decree for the Greeks (Laetentur Caeli), on Spirit's procession
 *   from Father and Son (DS 1300-1302).
 * - Catholic precision on origins: Father as "principle without principle," Spirit proceeding
 *   from Father and Son as from one principle (CCC 248).
 */
public class TrinityModelApp {
    /**
     * Utility class; not intended for instantiation.
     */
    private TrinityModelApp() {
    }

    /**
     * Builds one shared DivineNature and three distinct DivinePerson instances.
     *
     * <p>The single shared DivineNature instance in this simulation expresses the claim
     * "one divine nature, not three gods" (Deut 6:4; 1 Cor 8:6; CCC 253).
        *
        * <p>The sequence of printed sections is intentionally catechetical:
        * - unity of divine essence,
        * - personal distinction,
        * - eternal relations of origin,
        * - closure of distinctions by relation (aitia closure),
        * - temporal missions in salvation history.
        *
        * <p>As a software demonstration, this method uses strings and booleans to represent
        * doctrine-facing claims. It should be read as explanatory scaffolding, not as a claim that
        * divine life is reducible to computation.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        // One divine essence shared fully by Father, Son, and Holy Spirit.
        // References: Deut 6:4; Nicene Creed ("I believe in one God"); CCC 253-255.
        DivineNature oneDivineNature = new DivineNature();

        // Three distinct Persons, each possessing the one divine essence fully.
        // References: Matt 28:19; 2 Cor 13:14; Athanasian Creed; CCC 254-255.
        Father father = new Father(oneDivineNature);
        Son son = new Son(oneDivineNature);
        HolySpirit holySpirit = new HolySpirit(oneDivineNature);

        System.out.println("UML Analogy of the Holy Trinity");
        System.out.println("--------------------------------");
        System.out.println();

        // Claim: one divine nature (ousia), not three gods.
        // References: Deut 6:4; 1 Cor 8:6; Nicene Creed; CCC 253.
        System.out.println("One divine nature, not three gods:");
        System.out.println("- Father possesses the same DivineNature instance as Son: "
                + (father.possessesFully() == son.possessesFully()));
        System.out.println("- Son possesses the same DivineNature instance as Holy Spirit: "
                + (son.possessesFully() == holySpirit.possessesFully()));
        System.out.println();

        printPersonSummary(father);
        printPersonSummary(son);
        printPersonSummary(holySpirit);
        System.out.println();

        // Eternal relations of origin.
        // Father begets the Son: John 1:14, 1:18; Nicene Creed ("begotten, not made"); CCC 242-244.
        // Spirit procession from Father and Son (Western formulation): John 15:26; John 16:13-15;
        // Council of Florence DS 1300-1302; CCC 246-248.
        System.out.println("Eternal relations:");
        System.out.println("- " + father.eternallyBegets(son));
        System.out.println("- " + father.eternallySpirates(holySpirit));
        System.out.println("- " + son.eternallySpirates(holySpirit));
        System.out.println();

        // Aitia closure (closure of personal distinctions by relations of origin).
        // Catholic articulation: Father is principle without principle; Son is eternally begotten
        // of the Father; Holy Spirit proceeds from Father and Son as from one principle.
        // References: CCC 254-255, esp. CCC 248; Florence DS 1300-1302.
        System.out.println("Aitia closure (relations of origin):");
        printAitiaClosure(father);
        printAitiaClosure(son);
        printAitiaClosure(holySpirit);
        System.out.println();

        // Temporal missions (sending in salvation history).
        // Father sends the Spirit in the Son's name: John 14:26.
        // Son sends the Spirit from the Father: John 15:26; John 20:22; CCC 244.
        System.out.println("Missions in time:");
        System.out.println("- " + father.sendsInTime(holySpirit));
        System.out.println("- " + son.sendsInTime(holySpirit));
    }

    /**
     * Prints a concise summary of personhood and full divinity claims for one Person.
     *
     * <p>Each Person is fully God and truly distinct in relation.
     * References: Matt 28:19; 2 Cor 13:14; Athanasian Creed; CCC 253-255.
        *
        * <p>This output intentionally places "fullyGod" and "distinctPerson" side by side so readers
        * can see the model's non-negotiable pairing: one essence with real personal distinction.
     *
     * @param person the divine Person whose summary should be printed
     */
    private static void printPersonSummary(DivinePerson person) {
        System.out.println(person.getName() + ":");
        System.out.println("  fullyGod = " + person.isFullyGod());
        System.out.println("  distinctPerson = " + person.isDistinctPerson());
    }

    /**
     * Prints each Person's relation of origin to make personal distinctions explicit.
     *
     * <p>This implements "aitia closure" in the model: the three Persons are distinguished by
     * relations of origin, not by nature.
     * References: CCC 254-255; Florence DS 1300-1302.
        *
        * <p>Pedagogically, this method acts as the interpretive key for the model. Without this
        * section, distinctions could be misread as essential difference rather than relational
        * distinction.
     *
     * @param person the divine Person whose relation-of-origin descriptor is printed
     */
    private static void printAitiaClosure(DivinePerson person) {
        System.out.println("- " + person.getName() + ": " + person.relationOfOriginDescription());
    }
}
