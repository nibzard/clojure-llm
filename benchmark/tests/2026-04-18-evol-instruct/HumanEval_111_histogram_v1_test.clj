(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"bb" 2, "dd" 2} (histogram-by-length ["a" "bb" "c" "dd"])))
  (is (= {"horse" 5} (histogram-by-length [nil "" "cat" "ox" "horse" "dog"])))
  (is (= {} (histogram-by-length []))))

(run-test test-variation)
