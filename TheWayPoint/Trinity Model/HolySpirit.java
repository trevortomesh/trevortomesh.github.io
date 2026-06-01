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
public class HolySpirit extends DivinePerson {
    /**
     * Constructs the Holy Spirit Person with full possession of the shared divine nature.
     *
     * <p>Using the shared {@link DivineNature} instance maintains the model's monotheistic
     * structure while preserving personal distinction by relation.
     *
     * @param divineNature the one divine nature possessed fully by the Holy Spirit
     */
    public HolySpirit(DivineNature divineNature) {
        super("Holy Spirit", divineNature);
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
