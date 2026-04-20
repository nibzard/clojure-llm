(defn count-set-bits
  "Return a map of bit positions to 1 for every set bit in n.
  Bit positions are zero-based from the least-significant bit.

  Examples:
  (count-set-bits 10) => {1 1, 3 1}
  (count-set-bits 0)  => {}"
  [n]
  (into {}
        (keep (fn [i]
                (when (bit-test n i)
                  [i 1])))
        (range 64)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {1 1, 3 1} (count-set-bits 10))))

(run-test test-variation)
