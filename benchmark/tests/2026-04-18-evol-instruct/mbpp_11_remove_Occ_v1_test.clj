(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "banaa" (remove-nth-occurrence "banana" \a 2))))

(run-test test-variation)
