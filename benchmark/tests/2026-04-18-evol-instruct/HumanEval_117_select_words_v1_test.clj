(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["had" "little"] (select_words ["Mary" "had" "a" "little" "lamb"] 2))))

(run-test test-variation)
