(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (count-nonempty-subsequences [1 2 3])))
  (is (= 3 (count-nonempty-subsequences '(:a :b))))
  (is (= 0 (count-nonempty-subsequences nil))))

(run-test test-variation)
