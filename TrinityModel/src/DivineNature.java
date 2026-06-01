/**
 * DivineNature models attributes traditionally predicated of the one divine essence.
 *
 * <p>This class intentionally gathers classic predicates (eternal, omnipotent, omniscient,
 * simple) into one immutable object so the rest of the model can demonstrate that the three
 * Persons share one undivided nature.
 *
 * <p>Theological rationale for this design:
 * - It supports monotheistic unity (Deut 6:4).
 * - It avoids coding patterns that imply three independent divine essences.
 * - It makes doctrinal predicates explicit and teachable in generated API docs.
 *
 * <p>Reference set:
 * - Eternal: Ps 90:2; Rev 1:8; CCC 212.
 * - Omnipotent: Gen 17:1; Rev 19:6; CCC 268-271.
 * - Omniscient: Ps 139:1-6; 1 John 3:20; CCC 216.
 * - Simple (divine simplicity): Fourth Lateran Council DS 800-806.
 */
public final class DivineNature {
    private static final DivineNature INSTANCE = new DivineNature();

    // Claim: God is eternal.
    // References: Ps 90:2; Rev 1:8; CCC 212.
    private final boolean eternal = true;

    // Claim: God is omnipotent.
    // References: Gen 17:1; Rev 19:6; CCC 268-271.
    private final boolean omnipotent = true;

    // Claim: God is omniscient.
    // References: Ps 139:1-6; 1 John 3:20; CCC 216.
    private final boolean omniscient = true;

    // Claim: God is simple (not composed of parts).
    // References: Fourth Lateran Council (DS 800-806); classical Christian metaphysics.
    private final boolean simple = true;

    /**
     * Creates an immutable representation of the one divine nature for this model.
     *
     * <p>All fields are fixed to {@code true} because this class is not performing speculative
     * theology by computation; it is expressing settled doctrinal predicates in a concise API.
     */
    private DivineNature() {
    }

    /**
     * Returns the one shared DivineNature instance for the entire model.
     *
     * <p>This enforces a strict singleton in code so object identity aligns with the
     * pedagogical claim of one undivided divine essence.
     *
     * @return the one DivineNature instance
     */
    public static DivineNature getInstance() {
        return INSTANCE;
    }

    /**
     * Indicates whether the modeled divine nature is eternal.
     *
     * <p>"Eternal" here means without beginning or end and not bound by created temporal
     * succession. In classical theology this supports divine aseity and uncreated being.
     *
     * <p>References: Ps 90:2; Rev 1:8; CCC 212.
     *
     * @return {@code true}, expressing the predicate of eternality
     */
    public boolean isEternal() {
        return eternal;
    }

    /**
     * Indicates whether the modeled divine nature is omnipotent.
     *
     * <p>"Omnipotent" denotes that all things possible to divine wisdom and goodness are within
     * God's power. This is not arbitrary force, but power coherent with truth and goodness.
     *
     * <p>References: Gen 17:1; Rev 19:6; CCC 268-271.
     *
     * @return {@code true}, expressing the predicate of omnipotence
     */
    public boolean isOmnipotent() {
        return omnipotent;
    }

    /**
     * Indicates whether the modeled divine nature is omniscient.
     *
     * <p>"Omniscient" indicates perfect, complete, and non-discursive knowledge. In doctrinal
     * terms, nothing is hidden from God, and divine knowledge does not grow by acquisition.
     *
     * <p>References: Ps 139:1-6; 1 John 3:20; CCC 216.
     *
     * @return {@code true}, expressing the predicate of omniscience
     */
    public boolean isOmniscient() {
        return omniscient;
    }

    /**
     * Indicates whether the modeled divine nature is simple (non-composite).
     *
     * <p>Divine simplicity means God is not assembled from parts, layers, or competing internal
     * components. This guards against projecting creaturely composition onto the Creator.
     *
     * <p>Reference: Fourth Lateran Council DS 800-806.
     *
     * @return {@code true}, expressing divine simplicity
     */
    public boolean isSimple() {
        return simple;
    }
}
