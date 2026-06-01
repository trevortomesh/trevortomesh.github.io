/**
 * HolySpirit: third Person of the Trinity, fully God and distinct Person.
 *
 * <p>This class centers on pneumatological identity and relation-of-origin description. The Holy
 * Spirit is modeled as consubstantial with Father and Son, personally distinct, and confessed as
 * "Lord and giver of life" in the creed.
 *
 * <p>References:
 * - Full divinity: Acts 5:3-4; 2 Cor 3:17.
 * - Distinct personhood: John 14:26; John 16:13-15; Matt 28:19.
 * - Creedal confession: Nicene-Constantinopolitan Creed.
 * - Catechism: CCC 243-248.
 */
public final class HolySpirit extends DivinePerson {
    private static final HolySpirit INSTANCE = new HolySpirit(DivineNature.getInstance());

    /**
     * Internally constructs the Holy Spirit singleton with full possession of the shared divine nature.
     *
     * <p>Using the shared {@link DivineNature} instance maintains the model's monotheistic
     * structure while preserving personal distinction by relation.
     *
     * <p>Callers should use {@link #getInstance()} rather than this constructor.
     *
     * @param divineNature the one divine nature possessed fully by the Holy Spirit
     */
    private HolySpirit(DivineNature divineNature) {
        super("Holy Spirit", divineNature);
    }

    /**
     * Returns the one Holy Spirit instance in this model.
     *
     * @return the singleton Holy Spirit instance
     */
    public static HolySpirit getInstance() {
        return INSTANCE;
    }

    /**
     * Provides the Holy Spirit's relation-of-origin descriptor in this model.
     *
     * <p>The returned phrase follows Western Catholic formula: procession from the Father and the
     * Son "as from one principle." The wording is chosen for doctrinal precision in this
     * project's teaching scope.
     *
     * @return the Holy Spirit relation-of-origin phrase
     */
    @Override
    public String relationOfOriginDescription() {
        return "eternally proceeds from the Father and the Son as from one principle.";
    }
}
