(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (left-insertion-once [1 2 2 4 5] 2)))
  (is (= 2 (left-insertion-once [1 3 5] 4)))
  (is (= 100 (left-insertion-once (range) 100))))

(run-test test-variation)
