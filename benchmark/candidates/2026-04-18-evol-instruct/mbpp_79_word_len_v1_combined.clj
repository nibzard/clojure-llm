(defn odd-length-word?
  "Return true if `s` is a string whose length is odd, false otherwise.

Examples:
(odd-length-word? \"cat\") => true
(odd-length-word? \"four\") => false
(odd-length-word? nil) => false"
  [s]
  (and (string? s)
       (odd? (count s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (odd-length-word? "cat")))
  (is (= false (odd-length-word? "four")))
  (is (= false (odd-length-word? nil))))

(run-test test-variation)
