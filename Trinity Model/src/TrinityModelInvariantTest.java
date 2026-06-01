/**
 * TrinityModelInvariantTest
 *
 * <p>Executable invariant checks for the Trinity pedagogical model.
 *
 * <p>These checks validate software-level constraints introduced by the strict singleton
 * refactor:
 * - exactly one DivineNature instance in accessible API usage,
 * - exactly one instance per Person (Father, Son, HolySpirit),
 * - one shared DivineNature possessed by all Persons.
 *
 * <p>These are pedagogical software invariants, not metaphysical proofs.
 */
public final class TrinityModelInvariantTest {
    private TrinityModelInvariantTest() {
    }

    /**
     * Runs executable invariant checks for singleton identity and shared divine nature.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        DivineNature natureA = DivineNature.getInstance();
        DivineNature natureB = DivineNature.getInstance();

        Father fatherA = Father.getInstance();
        Father fatherB = Father.getInstance();

        Son sonA = Son.getInstance();
        Son sonB = Son.getInstance();

        HolySpirit spiritA = HolySpirit.getInstance();
        HolySpirit spiritB = HolySpirit.getInstance();

        assertSame(natureA, natureB, "DivineNature should be singleton");
        assertSame(fatherA, fatherB, "Father should be singleton");
        assertSame(sonA, sonB, "Son should be singleton");
        assertSame(spiritA, spiritB, "HolySpirit should be singleton");

        assertSame(fatherA.possessesFully(), sonA.possessesFully(),
                "Father and Son should possess the same DivineNature instance");
        assertSame(sonA.possessesFully(), spiritA.possessesFully(),
                "Son and HolySpirit should possess the same DivineNature instance");

        System.out.println("All Trinity model invariants passed.");
    }

    private static void assertSame(Object left, Object right, String message) {
        if (left != right) {
            throw new IllegalStateException(message);
        }
    }
}
