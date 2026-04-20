(defn palindromic-prefix?
  "Return true if the vector is identical to its reverse prefix of the same length.
  Ignore nil values by treating them as absent, and handle empty vectors.

  Examples:
  (palindromic-prefix? [1 2 1]) => true
  (palindromic-prefix? [1 nil 1]) => true
  (palindromic-prefix? []) => true
  (palindromic-prefix? [1 2 3]) => false"
  [v]
  (let [cleaned (vec (remove nil? v))]
    (= cleaned (vec (reverse cleaned)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (palindromic-prefix? [1 2 1])))
  (is (= true (palindromic-prefix? [1 nil 1])))
  (is (= true (palindromic-prefix? [])))
  (is (= false (palindromic-prefix? [1 2 3]))))

(run-test test-variation)
