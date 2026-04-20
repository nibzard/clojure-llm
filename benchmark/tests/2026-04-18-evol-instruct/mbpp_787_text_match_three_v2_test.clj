(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (text_match_three-or-more "xxabbbzz")))
  (is (= true (text_match_three-or-more "abbbbb")))
  (is (= false (text_match_three-or-more "xxabbz")))
  (is (= false (text_match_three-or-more nil))))

(run-test test-variation)
