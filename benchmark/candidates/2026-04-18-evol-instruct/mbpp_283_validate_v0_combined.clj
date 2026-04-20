(defn valid-frequencies?
  "Return true if every non-nil value in the collection appears no more times than its hash-code mod 10.
  Nil values are ignored.

  Examples:
  (valid-frequencies? [1 2 2 3])     ;=> true
  (valid-frequencies? [11 11 11])    ;=> false
  (valid-frequencies? [nil 5 5 nil])  ;=> true"
  [coll]
  (let [freqs (frequencies (remove nil? coll))]
    (every? (fn [[v cnt]]
              (<= cnt (mod (Math/abs (hash v)) 10)))
            freqs)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (valid-frequencies? [1 2 2 3])))
  (is (= false (valid-frequencies? [11 11 11])))
  (is (= true (valid-frequencies? [nil 5 5 nil]))))

(run-test test-variation)
