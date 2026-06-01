/**
 * Son: second Person of the Trinity.
 *
 * <p>This class models filial identity in relation to the Father and Spirit while preserving full
 * divine equality. The Son is eternally begotten (not made), consubstantial with the Father, and
 * active in the Spirit's mission in salvation history.
 *
 * <p>References: John 1:1, 1:14, 1:18; Heb 1:3; Matt 28:19; Nicene Creed; CCC 242-248.
 */
public final class Son extends DivinePerson {
    private static final Son INSTANCE = new Son(DivineNature.getInstance());

    /**
     * Internally constructs the Son singleton with full possession of the shared divine nature.
     *
     * <p>The constructor encodes, at object-graph level, that the Son shares one divine essence
     * with the Father and the Holy Spirit.
     *
     * <p>Callers should use {@link #getInstance()} rather than this constructor.
     *
     * @param divineNature the one divine nature possessed fully by the Son
     */
    private Son(DivineNature divineNature) {
        super("Son", divineNature);
    }

    /**
     * Returns the one Son instance in this model.
     *
     * @return the singleton Son instance
     */
    public static Son getInstance() {
        return INSTANCE;
    }

    /**
     * Western articulation of eternal procession: the Son, with the Father, spirates the Holy
     * Spirit.
     *
     * <p>This method mirrors the project's selected doctrinal vocabulary for procession language.
     * It returns explanatory output and is meant for didactic traceability in demos and docs.
     *
     * <p>References: John 16:13-15; CCC 246-248; Florence DS 1300-1302.
     *
     * @param holySpirit the Holy Spirit who eternally proceeds
     * @return a human-readable sentence describing eternal spiration
     */
    public String eternallySpirates(HolySpirit holySpirit) {
        return getName() + " eternally spirates " + holySpirit.getName() + ".";
    }

    /**
     * Temporal mission: the Son sends the Spirit from the Father.
     *
     * <p>This mission language is scriptural and economic (that is, about salvation history).
     * The method is intentionally separated from eternal-origin methods so learners can distinguish
     * eternal Trinitarian processions from temporal sending.
     *
     * <p>References: John 15:26; John 20:22; CCC 244.
     *
     * @param holySpirit the Holy Spirit sent in the temporal mission
     * @return a human-readable sentence describing the temporal mission
     */
    public String sendsInTime(HolySpirit holySpirit) {
        return getName() + " sends " + holySpirit.getName() + " in time.";
    }

    /**
     * Provides the Son's relation-of-origin descriptor in this model.
     *
     * <p>"Eternally begotten" names filial relation without implying creaturely origin in time.
     * It is the key relation that distinguishes the Son personally from Father and Spirit.
     *
     * @return the Son's relation-of-origin phrase
     */
    @Override
    public String relationOfOriginDescription() {
        return "eternally begotten of the Father.";
    }
}
