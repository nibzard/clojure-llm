(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_dirty_chars)

(deftest test-humaneval

  (is (= (candidate "probasscurve" "pros") "bacuve"))
  (is (= (candidate "digitalindia" "talent") "digiidi"))
  (is (= (candidate "exoticmiles" "toxic") "emles"))
)

(run-test test-humaneval)