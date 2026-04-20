(defn check-element
  "Return true if every value in the collection is equal to target.
  Treat nil as a valid target and return true for an empty collection.

  Examples:
  (check-element [1 1 1] 1) ;=> true
  (check-element [nil nil] nil) ;=> true
  (check-element [] :anything) ;=> true
  (check-element [1 2 1] 1) ;=> false"
  [coll target]
  (every? #(= % target) coll))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (check-element [1 1 1] 1))))

(run-test test-variation)
