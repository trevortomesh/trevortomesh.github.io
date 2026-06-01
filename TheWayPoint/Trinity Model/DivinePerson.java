/**
 * DivinePerson models one hypostasis (Person) in Trinitarian theology.
 *
 * <p>This abstraction is intentionally theological and pedagogical rather than metaphysical in a
 * strict technical sense. It is designed to help students reason about the Church's claims that:
 *
 * <p>1) the Father, Son, and Holy Spirit are each fully and truly God,
 * 2) the three are really distinct as Persons,
 * 3) those distinctions are grounded in relations of origin and not in three separate essences.
 *
 * <p>Primary references:
 * - Scripture: Matt 28:19; John 1:1; John 14-17; 2 Cor 13:14; Heb 1:3; Acts 5:3-4.
 * - Nicene-Constantinopolitan Creed (381): one God, consubstantial Son, divine Holy Spirit.
 * - Athanasian Creed: co-equality/co-eternity and non-confusion of Persons.
 * - Catechism of the Catholic Church: 232-267, especially 253-255.
 *
 * <p>Important modeling caveat: booleans in this class are not reductions of mystery to binary
 * logic; they are didactic assertions for classroom and documentation clarity.
 */
public abstract class DivinePerson {
    // Possession of the one undivided divine nature.
    // References: John 10:30; Nicene Creed; CCC 253.
    private final DivineNature divineNature;

    // Each Person is fully divine.
    // References: John 1:1; Heb 1:3; Acts 5:3-4; Athanasian Creed.
    private final boolean fullyGod = true;

    // Each Person is distinct (Father is not Son, Son is not Spirit, etc.).
    // References: Matt 28:19; John 14-16; CCC 254.
    private final boolean distinctPerson = true;

    // Human-readable person name used in output.
    private final String name;

    /**
     * Creates a divine Person with a theological name and full possession of the one divine
     * nature.
     *
     * <p>In the logic of this model, every concrete Person receives the same {@link DivineNature}
     * instance. This is the software analog for consubstantiality ("of one substance") and is
     * used to prevent accidental drift into tritheism in code.
     *
     * @param name theological person designation (Father, Son, Holy Spirit)
     * @param divineNature the one divine nature fully possessed by this Person
     */
    protected DivinePerson(String name, DivineNature divineNature) {
        this.name = name;
        this.divineNature = divineNature;
    }

    /**
     * Returns the one divine nature possessed fully by this Person.
     *
     * <p>The doctrinal point represented here is inseparable unity of essence: the Father, Son,
     * and Holy Spirit do not possess three different natures, nor fractions of one nature.
     * Rather, each Person is wholly and fully the one God.
     *
     * <p>Reference anchors: John 10:30; Nicene creed's consubstantial language; CCC 253-255.
     *
     * @return the shared DivineNature instance possessed by this Person
     */
    public DivineNature possessesFully() {
        return divineNature;
    }

    /**
     * Indicates whether this Person is fully divine in the model.
     *
     * <p>This method is intentionally simple in implementation and rich in meaning. Returning
     * {@code true} is the model's way of affirming the central Christian claim that each Person
     * is not merely "godlike" or "divine by participation," but truly God in the fullest sense.
     *
     * <p>Theological lesson represented by this boolean:
     * - It rejects subordinationism (that Son/Spirit are lesser divinity).
     * - It rejects adoptionism and Arian reduction of the Son.
     * - It supports worship language directed to Father, Son, and Holy Spirit.
     * - It harmonizes with the baptismal and doxological pattern of Scripture.
     *
     * <p>Key references:
     * - Son's full divinity: John 1:1; John 20:28; Heb 1:3.
     * - Spirit's full divinity: Acts 5:3-4; 2 Cor 3:17.
     * - Triune liturgical form: Matt 28:19; 2 Cor 13:14.
     * - Catechetical synthesis: CCC 253, 255.
     *
     * <p>Pedagogical note: the return type is {@code boolean} because software APIs require
     * compact, testable outputs. The doctrinal claim itself is far richer than the type system.
     *
     * @return {@code true}, expressing full and equal divinity of this Person
     */
    public boolean isFullyGod() {
        return fullyGod;
    }

    /**
     * Indicates whether this Person is distinct from the other Persons.
     *
     * <p>The distinction asserted here is personal, not essential. In classical Trinitarian
     * language: distinction is "by relation" and not "by substance." Thus, this method should be
     * read together with {@link #possessesFully()}, not in isolation.
     *
     * <p>Practical theological takeaway:
     * - "Father is not Son"
     * - "Son is not Spirit"
     * - "Spirit is not Father"
     * while preserving one divine essence.
     *
     * <p>References: Matt 28:19; John 14-16; CCC 254-255; Athanasian Creed.
     *
     * @return {@code true}, expressing real personal distinction in the model
     */
    public boolean isDistinctPerson() {
        return distinctPerson;
    }

    /**
     * Returns the display name of this Person.
     *
     * <p>The returned value is catechetical and relational (Father, Son, Holy Spirit), not a
     * claim that divine being can be exhausted by human language. It serves readable output,
     * diagram labels, and explanatory narratives.
     *
     * @return the person name used in textual output (for example, Father)
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this Person's relation-of-origin descriptor.
     *
     * <p>Relations of origin are the core identity markers in this model. They explain how the
     * Persons are distinct without dividing the divine essence. Subclasses implement this with
     * class-specific phrases (for example, "eternally begotten" or "eternally proceeds").
     *
     * <p>Catholic doctrinal references: CCC 254-255 and, for Western procession language,
     * CCC 246-248 and Florence DS 1300-1302.
     *
     * @return a textual relation-of-origin description for this Person
     */
    public abstract String relationOfOriginDescription();
}
