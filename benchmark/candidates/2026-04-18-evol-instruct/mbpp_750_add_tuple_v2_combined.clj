(defn add-if-present
  "Return a new vector with the value appended only when x is non-nil.

  Examples:
  (add-if-present [1 2] 3)    ;=> [1 2 3]
  (add-if-present [1 2] nil)  ;=> [1 2]"
  [v x]
  (if (nil? x)
    v
    (conj (vec v) x)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3] (add-if-present [1 2] 3)))
  (is (= [1 2] (add-if-present [1 2] nil))))

(run-test test-variation)
