(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (text_match_three "abbb")))
  (is (= false (text_match_three "abbbb")))
  (is (= false (text_match_three "aabb")))
  (is (= false (text_match_three nil))))

(run-test test-variation)
