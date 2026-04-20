(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 2 4] (strip-first-last [1 2 3 2 4] 2)))
  (is (= ["b" "a" "c"] (strip-first-last ["a" "b" "a" "c"] "a"))))

(run-test test-variation)
