/**
 * Father: first Person in classical Trinitarian naming.
 *
 * <p>This class represents paternity and principle-of-origin language within the model. The
 * Father is not modeled as "more divine" than the Son or Spirit, but as personally distinct by
 * relation: unbegotten and source without source ("principle without principle").
 *
 * <p>Key relational claims represented here:
 * - eternally begets the Son (John 1:14, 1:18; Nicene Creed; CCC 242-244),
 * - with the Son is named in Western theology as principle of the Spirit's procession
 *   (John 15:26; John 16:13-15; CCC 246-248; Florence DS 1300-1302),
 * - sends the Spirit in temporal mission (John 14:26; CCC 244).
 */
public final class Father extends DivinePerson {
    private static final Father INSTANCE = new Father(DivineNature.getInstance());

    /**
     * Internally constructs the Father singleton with full possession of the shared divine nature.
     *
     * <p>By passing the same {@link DivineNature} object used by other Persons, the model
     * preserves unity of essence while permitting personal distinction by relation.
     *
     * <p>Callers should use {@link #getInstance()} rather than this constructor.
     *
     * @param divineNature the one divine nature possessed fully by the Father
     */
    private Father(DivineNature divineNature) {
        super("Father", divineNature);
    }

    /**
     * Returns the one Father instance in this model.
     *
     * @return the singleton Father instance
     */
    public static Father getInstance() {
        return INSTANCE;
    }

    /**
     * Expresses eternal generation: the Father eternally begets the Son.
     *
     * <p>"Begetting" here is theological and eternal, not biological and temporal. The method
     * returns narrative text for the demo output, but its conceptual purpose is to encode a
     * central creedal identity marker for the Son.
     *
     * <p>References: John 1:14, 1:18; Nicene Creed ("begotten, not made"); CCC 242-244.
     *
     * @param son the Son who is eternally begotten
     * @return a human-readable sentence describing eternal generation
     */
    public String eternallyBegets(Son son) {
        return getName() + " eternally begets " + son.getName() + ".";
    }

    /**
     * Expresses eternal procession of the Spirit from the Father and the Son in Western
     * (Filioque) doctrinal formulation.
     *
     * <p>"Spiration" is used analogically to indicate relation-of-origin language for the Spirit.
     * This model does not adjudicate historical East-West debates; it documents the specific
     * Western articulation named in the current project scope.
     *
     * <p>References: John 15:26; John 16:13-15; CCC 246-248; Florence DS 1300-1302.
     *
     * @param holySpirit the Holy Spirit who eternally proceeds
     * @return a human-readable sentence describing eternal spiration
     */
    public String eternallySpirates(HolySpirit holySpirit) {
        return getName() + " eternally spirates " + holySpirit.getName() + ".";
    }

    /**
     * Temporal mission: the Father sends the Spirit in salvation history.
     *
     * <p>Unlike eternal procession, this method represents mission in time. The distinction
     * between eternal origin and temporal mission helps students avoid conflating who God is
     * eternally with how God acts in history.
     *
     * <p>References: John 14:26; CCC 244.
     *
     * @param holySpirit the Holy Spirit sent in the temporal mission
     * @return a human-readable sentence describing the temporal mission
     */
    public String sendsInTime(HolySpirit holySpirit) {
        return getName() + " sends " + holySpirit.getName() + " in time.";
    }

    /**
     * Provides the Father's relation-of-origin descriptor in this model.
     *
     * <p>The phrase "principle without principle" summarizes that the Father's personal identity
     * is unoriginate paternity, not superiority of essence.
     *
     * @return the Father's relation-of-origin phrase
     */
    @Override
    public String relationOfOriginDescription() {
        return "unbegotten; principle without principle.";
    }
}
